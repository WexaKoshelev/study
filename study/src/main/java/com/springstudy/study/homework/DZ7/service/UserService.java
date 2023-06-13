package com.springstudy.study.homework.DZ7.service;

import com.springstudy.study.homework.DZ7.dto.OrdersDTO;
import com.springstudy.study.homework.DZ7.dto.RoleDTO;
import com.springstudy.study.homework.DZ7.dto.UserDTO;
import com.springstudy.study.homework.DZ7.mapper.GenericMapper;
import com.springstudy.study.homework.DZ7.mapper.UserMapper;
import com.springstudy.study.homework.DZ7.model.Orders;
import com.springstudy.study.homework.DZ7.model.Users;
import com.springstudy.study.homework.DZ7.repository.GenericRepository;
import com.springstudy.study.homework.DZ7.repository.OrdersRepository;
import com.springstudy.study.homework.DZ7.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
@Slf4j
@Service
public class UserService extends GenericService<Users, UserDTO> {
    private  final OrdersRepository ordersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;

    public UserService(GenericRepository<Users> repository, GenericMapper<Users, UserDTO> mapper,
                       OrdersRepository ordersRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       JavaMailSender javaMailSender) {
        super(repository, mapper);
        this.ordersRepository = ordersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.javaMailSender = javaMailSender;
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
    public UserDTO getUserByLogin(final String login) {
        return mapper.toDTO(((UserRepository) repository).findUserByLogin(login));
    }

    public UserDTO getUserByEmail(final String email) {
        return mapper.toDTO(((UserRepository) repository).findUserByEmail(email));
    }
    public boolean checkPassword (String password, UserDetails foundUser) {
        return bCryptPasswordEncoder.matches(password, foundUser.getPassword());
    }
}


