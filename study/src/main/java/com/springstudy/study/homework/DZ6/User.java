package com.springstudy.study.homework.DZ6;

import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User  {
    private String lastName;
    private String name;
    private String dateOfBirth;
    private String phone;
    private String mail;
}
