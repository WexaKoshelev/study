package com.springstudy.study.online_movie_theater.mapper;

import com.springstudy.study.online_movie_theater.dto.OrdersDTO;
import com.springstudy.study.online_movie_theater.model.Orders;
import com.springstudy.study.online_movie_theater.repository.FilmsRepository;
import com.springstudy.study.online_movie_theater.repository.UserRepository;
import com.springstudy.study.online_movie_theater.service.FilmsService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

@Component
public class OrdersMapper extends GenericMapper<Orders, OrdersDTO> {
    private final FilmsRepository filmsRepository;
    private final UserRepository userRepository;
    private final FilmsService filmsService;

    public OrdersMapper(ModelMapper modelMapper, FilmsRepository filmsRepository, UserRepository userRepository, FilmsService filmsService) {
        super(Orders.class, OrdersDTO.class, modelMapper);
        this.filmsRepository = filmsRepository;
        this.userRepository = userRepository;
        this.filmsService = filmsService;
    }
    @PostConstruct
    protected void setupMapper() {
        super.modelMapper.createTypeMap(Orders.class, OrdersDTO.class)
                .addMappings(m -> m.skip(OrdersDTO :: setUserId))
                .addMappings(m -> m.skip(OrdersDTO :: setFilmId))
                .setPostConverter(toDTOConverter());
        super.modelMapper.createTypeMap(OrdersDTO.class, Orders.class)
                .addMappings(m -> m.skip(Orders :: setUsers))
                .addMappings(m -> m.skip(Orders :: setFilms))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(OrdersDTO source, Orders destination) {
        destination.setFilms(filmsRepository.findById(source.getFilmId()).orElseThrow(() -> new NotFoundException("Фильм не найден")));
        destination.setUsers(userRepository.findById(source.getUserId()).orElseThrow(() -> new NotFoundException("Пользователь не найден")));
    }
    @Override
    protected void mapSpecificFields(Orders source, OrdersDTO destination) {
        destination.setUserId(source.getUsers().getId());
        destination.setFilmId(source.getFilms().getId());
        destination.setFilmDTO(filmsService.getOne(source.getFilms().getId()));
    }
    @Override
    protected List<Long> getIds(Orders entity) {
        throw new UnsupportedOperationException("Метод не доступен");
    }
}
