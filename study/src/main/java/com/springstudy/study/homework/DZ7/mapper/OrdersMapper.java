package com.springstudy.study.homework.DZ7.mapper;

import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.repository.FilmsRepository;
import com.springstudy.study.homework.DZ7.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;
@Component
public class OrdersMapper extends GenericMapper<Orders, OrdersDTO> {
    private final FilmsRepository filmsRepository;
    private final UserRepository userRepository;

    public OrdersMapper(ModelMapper modelMapper, FilmsRepository filmsRepository, UserRepository userRepository) {
        super(Orders.class, OrdersDTO.class, modelMapper);
        this.filmsRepository = filmsRepository;
        this.userRepository = userRepository;
    }
    @PostConstruct
    protected void setupMapper() {
        super.modelMapper.createTypeMap(Orders.class, OrdersDTO.class)
                .addMappings(m -> m.skip(OrdersDTO :: setUsersId))
                .addMappings(m -> m.skip(OrdersDTO :: setFilmsId))
                .setPostConverter(toDTOConverter());
        super.modelMapper.createTypeMap(OrdersDTO.class, Orders.class)
                .addMappings(m -> m.skip(Orders :: setUsers))
                .addMappings(m -> m.skip(Orders :: setFilms))
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(OrdersDTO source, Orders destination) {
        destination.setFilms(filmsRepository.findById(source.getFilmsId())
                .orElseThrow(() -> new NotFoundException("Фильмы не найдены")));
        destination.setUsers(userRepository.findById(source.getUsersId())
                .orElseThrow(() -> new NotFoundException("Пользователь не найден")));
    }
    @Override
    protected void mapSpecificFields(Orders source, OrdersDTO destination) {
        destination.setUsersId(source.getUsers().getId());
        destination.setFilmsId(source.getFilms().getId());
    }
    @Override
    protected List<Long> getIds(Orders entity) {
        throw new UnsupportedOperationException("Метод не доступен");
    }
}
