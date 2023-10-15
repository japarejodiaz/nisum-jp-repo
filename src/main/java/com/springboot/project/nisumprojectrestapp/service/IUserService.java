package com.springboot.project.nisumprojectrestapp.service;

import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestActDto;
import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDtos;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseFullDto;

public interface IUserService {

    /**
     * Servicio de creacion de usuario
     * @param request
     * @return
     */
    UserResponseDto addUser (UserRequestDto request);

    /**
     * Servicio de usuario de consulta por ID
     * @param idUser
     * @return
     */
    UserResponseDto getUserById (Long idUser);

    /**
     * Servicio dde consulta de usuario por UUID
     * @param uuid
     * @return
     */
    UserResponseDto getUserByUuid (String uuid);

    /**
     * Servicio para consultas de usuarios por paginas
     * @param page
     * @param size
     * @return
     */
    UserResponseFullDto getUsersAllForPage(Integer page, Integer size);

    /**
     * Servicio de eliminacion de usuario por id
     * @param uuid
     */
    void deleteUserById(String uuid);

    /**
     * Servicio para consultas de usuarios sin  pagineo
     * @param page
     * @param size
     * @return
     */
    UserResponseDtos getUsersAll();

    /**
     * Servicio de creacion de usuario
     * @param request
     * @return
     */
    UserResponseDto updateUser (String uuidUser, UserRequestActDto request);
}
