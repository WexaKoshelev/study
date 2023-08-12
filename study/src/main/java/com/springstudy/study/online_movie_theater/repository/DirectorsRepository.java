package com.springstudy.study.online_movie_theater.repository;

import com.springstudy.study.online_movie_theater.model.Directors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorsRepository
        extends GenericRepository <Directors> {
    Page<Directors> findAllByDirectorFIOContainsIgnoreCaseAndIsDeletedFalse(String fio, Pageable pageable);

    @Query(value = """
select case when count(a) > 0 then false else true end
          from Directors a join a.films b
                        join Orders bri on b.id = bri.films.id
          where a.id = :directorId
          and bri.purchase = false
          """)
boolean checkDirectorForDeletion( Long directorId);
}
