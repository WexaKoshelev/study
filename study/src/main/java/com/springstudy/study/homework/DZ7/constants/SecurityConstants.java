package com.springstudy.study.homework.DZ7.constants;

import java.util.List;

public interface SecurityConstants {

    List<String> RESOURCES_WHITE_LIST = List.of("/resources/**",
            "/static/**",
            "/templates/**",
            "/js/**",
            "/css/**",
            "/",
            "/swagger-ui/**",
            "/webjars/bootstrap/5.3.0/**",
            "/webjars/bootstrap/5.3.0/css/**",
            "/webjars/bootstrap/5.3.0/js/**",
            "/v3/api-docs/**");

    List<String> FILMS_WHITE_LIST = List.of("/films",
            "/films/search",
            "/films/{id}");

    List<String> DIRECTORS_WHITE_LIST = List.of("/director",
            "/director/search",
            "/films/search/filmsByDirectors",
            "/director/{id}");
    List<String> FILMS_PERMISSION_LIST = List.of("/films/add",
            "/films/update",
            "/films/delete",
            "/films/download/{filmId}");

    List<String> DIRECTORS_PERMISSION_LIST = List.of(
            "/director",
            "/director/add",
            "/director/update",
            "/director/delete");


    List<String> USERS_WHITE_LIST = List.of(
            "/users",
            "/login",
            "/users/registration",
            "/users/remember-password",
            "/users/change-password");

    List<String> USERS_PERMISSION_LIST = List.of("/rent/film/*");
    List<String> USERS_REST_WHITE_LIST = List.of("/users/auth");
}

