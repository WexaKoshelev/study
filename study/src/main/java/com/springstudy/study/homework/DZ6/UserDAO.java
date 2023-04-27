package com.springstudy.study.homework.DZ6;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import lombok.Setter;
import org.apache.tomcat.util.bcel.Const;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Setter
@Component
@Scope(scopeName =  "prototype")
public class UserDAO   {
    private final Connection connection ;
    private final String USER_SELECT_BY_MAIL_QUERY = "select * from client where mail = ?";
    private  final Map<String, User> user = new HashMap<>();


    public UserDAO(Connection connection){
        this.connection = connection;
    }

    public User findUserByMail (String mail) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement(USER_SELECT_BY_MAIL_QUERY);
        selectQuery.setString(1,mail);
        ResultSet resultSet = selectQuery.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            user.setLastName(resultSet.getString("last_name"));
            user.setName(resultSet.getString("namu"));
            user.setDateOfBirth(resultSet.getString("date_of_birth"));
            user.setPhone(resultSet.getString("phone"));
            user.setMail(resultSet.getString("mail"));
            System.out.println(user);
        }
        return user;
    }
    public void signUpUser (String lastName, String name, String dateOfBirth, String phone, String mail) throws SQLException {
        String insert = "INSERT INTO client (LAST_NAME, NAMU, DATE_OF_BIRTH, PHONE, MAIL) VALUES (?,?,?,?,?)";
        PreparedStatement pre = connection.prepareStatement(insert);
        pre.setString(1,lastName);
        pre.setString(2,name);
        pre.setString(3,dateOfBirth);
        pre.setString(4,phone);
        pre.setString(5,mail);
        pre.executeUpdate();
        }
    }

