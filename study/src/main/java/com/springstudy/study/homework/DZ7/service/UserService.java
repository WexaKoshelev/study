package com.springstudy.study.homework.DZ7.service;

import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.dto.RoleDTO;
import com.springstudy.study.homework.DZ7.dto.UserDTO;
import com.springstudy.study.homework.DZ7.mapper.UserMapper;
import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.repository.OrdersRepository;
import com.springstudy.study.homework.DZ7.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class UserService extends GenericService<Users, UserDTO> {
    private  final OrdersRepository ordersRepository;
    public UserService(UserRepository repository, UserMapper mapper, OrdersRepository ordersRepository) {
        super(repository, mapper);
        this.ordersRepository = ordersRepository;
    }
//    public List<OrdersDTO> allOrders () {
//        UserDTO user = new UserDTO();
//        user.getUserOrdersId();
//    }
    public UserDTO addOrder (Long orderId, Long userId) {
        UserDTO user = getOne(userId);
        Orders orders = ordersRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Заказа не найден"));
        update(user);
        return  user;
    }
    @Override
    public UserDTO create(UserDTO newObject) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        newObject.setRole(roleDTO);
        return mapper.toDTO(repository.save(mapper.toEntity(newObject)));
    }
}
