package com.springstudy.study.homework.DZ7.mapper;

import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.model.Films;
import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.repository.FilmsRepository;
import com.springstudy.study.homework.DZ7.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
        if (!Objects.isNull(source.getFilmId())) {
            destination.setFilms((Films) filmsRepository.findAllById(source.getFilmId()));
        } else {
            destination.setFilms((Films) Collections.emptyList());
        }
        if (!Objects.isNull(source.getUserId())) {
            destination.setUsers((Users) filmsRepository.findAllById(source.getUserId()));
        } else {
            destination.setUsers((Users) Collections.emptyList());
        }
    }
    @Override
    protected void mapSpecificFields(Orders source, OrdersDTO destination) {
        destination.setUserId(Collections.singletonList(source.getUsers().getId()));
        destination.setFilmId(Collections.singletonList(source.getFilms().getId()));
    }
    @Override
    protected List<Long> getIds(Orders entity) {
        throw new UnsupportedOperationException("Метод не доступен");
    }
}
