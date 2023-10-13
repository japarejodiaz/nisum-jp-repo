package com.springboot.project.nisumprojectrestapp.mapper;

import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDtos;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseFullDto;
import com.springboot.project.nisumprojectrestapp.entity.UserEntity;

import java.util.List;

public interface IUserMapper {

    /**
     * Servicio de mapeo de reques a dto
     * @param userRequestDto
     * @return
     */
    UserEntity dtoToEntity (UserRequestDto userRequestDto);

    /**
     * Servicio de mapeo de entidad a response
     * @param userEntity
     * @return
     */
    UserResponseDto entityToDtoResponse (UserEntity userEntity);

    /**
     * Servicio de mapeo de listado de entidades a paginas
     * @param listUserEntities
     * @return
     */
    UserResponseFullDto listUserDTOs(List<UserEntity> listUserEntities);

    /**
     * Servicio de mapeo de listado de entidades a paginas
     * @param listUserEntities
     * @return
     */
    UserResponseDtos listUserDTOsb(List<UserEntity> listUserEntities);
}
