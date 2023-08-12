package com.springstudy.study.online_movie_theater.repository;

import com.springstudy.study.online_movie_theater.model.Films;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmsRepository
        extends  GenericRepository <Films>{
//    @Query(nativeQuery = true,
//            value = """
//                       select distinct f.*
//                       from films f
//                                left join FilmDirectors ba on f.id = ba.film_id
//                                left join directors a on a.id = ba.director_id
//                       where f.title ilike '%' || coalesce(:title, '%')  || '%'
//                         and cast(f.genre as char) like coalesce(:genre, '%')
//                         and coalesce(a.fio, '%') ilike '%' ||  coalesce(:fio, '%')  || '%'
//                         and f.is_deleted = false
//                    """)
//    Page<Films> searchFilms(@Param(value = "title") String filmTitle,
//                            @Param(value = "genre") String genre,
//                            @Param(value = "fio") String directorFIO,
//                            Pageable pageRequest);
//
//    @Query("""
//          select case when count(f) > 0 then false else true end
//          from Films f join Orders bri on f.id = bri.film.id
//          where f.id = :id and bri.purchase = false
//          """)
//    boolean isFilmCanBeDeleted(final Long id);

}
