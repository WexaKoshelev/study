package com.springstudy.study.homework.DZ6;


import lombok.*;
import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
    public class Book {
        private Integer id;
        private String title;
        private String author;
        private Date dateAdded;
    }


