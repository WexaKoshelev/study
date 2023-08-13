package com.springstudy.study.online_movie_theater.mapper;

import com.springstudy.study.online_movie_theater.dto.FilmDTO;
import com.springstudy.study.online_movie_theater.model.Films;
import com.springstudy.study.online_movie_theater.model.GenericModel;
import com.springstudy.study.online_movie_theater.repository.DirectorsRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class FilmsMapper extends GenericMapper <Films, FilmDTO> {
    private final DirectorsRepository directorsRepository;
    private final DirectorsMapper directorsMapper;
    public FilmsMapper(ModelMapper modelMapper, DirectorsRepository directorsRepository, DirectorsMapper directorsMapper) {
        super(Films.class, FilmDTO.class, modelMapper);
        this.directorsRepository = directorsRepository;
        this.directorsMapper = directorsMapper;
    }
    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Films.class, FilmDTO.class)
                .addMappings(m -> {
                    m.skip(FilmDTO :: setDirectorsIds);
                    m.skip(FilmDTO :: setDirectorInfo);
                }).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(FilmDTO.class, Films.class)
                .addMappings(m -> m.skip(Films :: setDirectors)).setPostConverter(toEntityConverter());

    }
    @Override
    protected void mapSpecificFields(FilmDTO source, Films destination) {
        if (!Objects.isNull(source.getDirectorsIds())) {
            destination.setDirectors(directorsRepository.findAllById(source.getDirectorsIds()));
        } else {
            destination.setDirectors(Collections.emptyList());
        }
    }

    @Override
    protected void mapSpecificFields(Films source, FilmDTO destination) {
        destination.setDirectorsIds(getIds(source));
        destination.setDirectorInfo(directorsMapper.toDTOs(source.getDirectors()));

    }
    @Override
    protected List<Long> getIds(Films films) {
        return Objects.isNull(films) || Objects.isNull(films.getDirectors())
                ? null
                : films.getDirectors().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toList());
    }
}

