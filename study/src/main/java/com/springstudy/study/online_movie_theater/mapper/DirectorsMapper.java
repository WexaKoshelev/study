package com.springstudy.study.online_movie_theater.mapper;

import com.springstudy.study.online_movie_theater.dto.DirectorDTO;
import com.springstudy.study.online_movie_theater.model.Directors;
import com.springstudy.study.online_movie_theater.model.GenericModel;
import com.springstudy.study.online_movie_theater.repository.FilmsRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DirectorsMapper extends GenericMapper<Directors, DirectorDTO>{
    private final FilmsRepository filmsRepository;

    public DirectorsMapper(ModelMapper modelMapper,FilmsRepository filmsRepository){
            super(Directors.class, DirectorDTO.class,modelMapper);
        this.filmsRepository = filmsRepository;
    }

    @PostConstruct
    protected void setupMapper() {
        modelMapper.createTypeMap(Directors.class,DirectorDTO.class)
                .addMappings(mapping -> mapping.skip(DirectorDTO :: setFilmsId)).setPostConverter(toDTOConverter());

        modelMapper.createTypeMap(DirectorDTO.class, Directors.class)
                .addMappings(mapping -> mapping.skip(Directors :: setFilms)).setPostConverter(toEntityConverter());
    }
    @Override
    protected void mapSpecificFields(DirectorDTO source, Directors destination) {
        if (!Objects.isNull(source.getFilmsId())) {
            destination.setFilms(filmsRepository.findAllById(source.getFilmsId()));
        } else {
            destination.setFilms(Collections.emptyList());
        }
    }
    @Override
    protected void mapSpecificFields(Directors source, DirectorDTO destination) {
        destination.setFilmsId(getIds(source));
    }
    @Override
    protected List<Long> getIds(Directors source) {
        return Objects.isNull(source) || Objects.isNull(source.getFilms())
                ? Collections.emptyList()
                : source.getFilms().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }


}
