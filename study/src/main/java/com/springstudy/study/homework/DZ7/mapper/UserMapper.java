package com.springstudy.study.homework.DZ7.mapper;

import com.springstudy.study.homework.DZ7.dto.UserDTO;
import com.springstudy.study.homework.DZ7.model.GenericModel;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.repository.OrdersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<Users, UserDTO> {
    private final OrdersRepository ordersRepository;
    public UserMapper(ModelMapper modelMapper, OrdersRepository ordersRepository) {
        super(Users.class, UserDTO.class, modelMapper);
        this.ordersRepository = ordersRepository;
    }
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Users.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO ::setUserOrders)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(UserDTO.class, Users.class)
                .addMappings(m -> m.skip(Users :: setOrders)).setPostConverter(toEntityConverter());
    }
    @Override
    protected void mapSpecificFields(UserDTO source, Users destination) {
        if (!Objects.isNull(source.getUserOrders())) {
            destination.setOrders(ordersRepository.findAllById(source.getUserOrders()));
        } else {
            destination.setOrders(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Users source, UserDTO destination) {
        destination.setUserOrders(getIds(source));
    }

    @Override
    protected List<Long> getIds(Users entity) {
        return Objects.isNull(entity) || Objects.isNull(entity.getOrders())
                ? null
                : entity.getOrders().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}
