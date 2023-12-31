package com.springstudy.study.online_movie_theater.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class UserDTO extends  GenericDTO{
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private Integer phone;
    private String address;
    private String email;
    private Integer createdWhen;
    private RoleDTO role;
    private List<Long> userOrdersId;
    private String changePasswordToken;
    private boolean isDeleted;
}
