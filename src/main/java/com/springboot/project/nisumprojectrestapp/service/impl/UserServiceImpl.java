package com.springboot.project.nisumprojectrestapp.service.impl;

import com.springboot.project.nisumprojectrestapp.dto.request.UserPhoneRequestActDto;
import com.springboot.project.nisumprojectrestapp.dto.request.UserPhoneRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestActDto;
import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserPhoneResponseDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDtos;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseFullDto;
import com.springboot.project.nisumprojectrestapp.entity.UserEntity;
import com.springboot.project.nisumprojectrestapp.entity.UserPhonesEntity;
import com.springboot.project.nisumprojectrestapp.exceptions.UserBadRequestException;
import com.springboot.project.nisumprojectrestapp.mapper.IUserMapper;
import com.springboot.project.nisumprojectrestapp.mapper.IUserPhoneMapper;
import com.springboot.project.nisumprojectrestapp.repository.IUserPhoneRepository;
import com.springboot.project.nisumprojectrestapp.repository.IUserRepository;
import com.springboot.project.nisumprojectrestapp.service.IUserService;
import com.springboot.project.nisumprojectrestapp.utils.InterceptorUtils;
import com.springboot.project.nisumprojectrestapp.utils.UserValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserValidationUtil userValidationUtil;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserPhoneRepository userPhoneRepository;

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IUserPhoneMapper userPhoneMapper;

    /**
     * Servicio de creacion de usuarios
     * @param request
     * @return
     */
    @Override
    public UserResponseDto addUser(UserRequestDto request) {

        UserResponseDto userResponseDto;

        List<UserPhoneResponseDto> userPhoneResponseDtoList = new ArrayList<>();
        /* Valido los datos que ingresan */
        userValidationUtil.validateRequestDto(request);
        /* Valido los datos contra la base de datos el correo y uuid de usuario */
        userValidationUtil.validateUserEntity(request);
        /* Hago los mapeos de los datos para el guardado de la informacion */
        UserEntity userEntity = userMapper.dtoToEntity(request);
        /* Se guarda la informacion de la data de user */
        userEntity = userRepository.save(userEntity);

        for (UserPhoneRequestDto listPhone: request.getUserPhones()) {
            UserPhonesEntity userPhonesEntity = userPhoneMapper.dtoToEntity(listPhone, userEntity);
            userPhonesEntity = userPhoneRepository.save(userPhonesEntity);
            /* Se genera mapeo de las caracteristicas del detalle de telefono */
            UserPhoneResponseDto userPhoneResponseDto = new UserPhoneResponseDto();
            userPhoneResponseDto = userPhoneMapper.entityToDtoResponse(userPhonesEntity);
            userPhoneResponseDtoList.add(userPhoneResponseDto);
        }

        userResponseDto = userMapper.entityToDtoResponse(userEntity);
        userResponseDto.setUserPhoneResponseDtoList(userPhoneResponseDtoList);
        return userResponseDto;
    }

    /**
     * Servicio de Consulta de usuario por Id
     * @param idUser
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto getUserById(Long idUser) {

        UserResponseDto userResponseDto;
        List<UserPhoneResponseDto> userPhoneResponseDtoList = new ArrayList<>();

        if (idUser == 0L) {
            throw new UserBadRequestException("id de usuario no puede ser cero, es requerido " + idUser);
        }

        Optional<UserEntity> userEntity = userRepository.findById(idUser);

        if (!userEntity.isPresent()) {
            throw new UserBadRequestException("id de usuario no existe " + idUser);
        }

        for (UserPhonesEntity listPhone: userEntity.get().getUserPhonesEntities()) {
            UserPhoneResponseDto userPhoneResponseDto = userPhoneMapper.entityToDtoResponse(listPhone);
            userPhoneResponseDtoList.add(userPhoneResponseDto);
        }

        userResponseDto = userMapper.entityToDtoResponse(userEntity.get());
        userResponseDto.setUserPhoneResponseDtoList(userPhoneResponseDtoList);
        return userResponseDto;

    }

    /**
     * Servicio de implementacion de consulta de User by Id
     * @param uuid
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto getUserByUuid(String uuid) {
        UserResponseDto userResponseDto;
        List<UserPhoneResponseDto> userPhoneResponseDtoList = new ArrayList<>();

        if (uuid == null ||
                uuid.isBlank() ||
                uuid.isEmpty() ) {
            throw new UserBadRequestException("Codigo de UUID de usuario es requerido " + uuid);
        }

        Optional<UserEntity> userEntity = userRepository.getUserEntityByUuid(uuid);

        if (!userEntity.isPresent()) {
            throw new UserBadRequestException("uuid de usuario no existe " + uuid);
        }

        for (UserPhonesEntity listPhone: userEntity.get().getUserPhonesEntities()) {
            UserPhoneResponseDto userPhoneResponseDto = userPhoneMapper.entityToDtoResponse(listPhone);
            userPhoneResponseDtoList.add(userPhoneResponseDto);
        }

        userResponseDto = userMapper.entityToDtoResponse(userEntity.get());
        userResponseDto.setUserPhoneResponseDtoList(userPhoneResponseDtoList);
        return userResponseDto;
    }

    /**
     * Servicio para consultas de usuarios por paginas
     *
     * @param page
     * @param size
     * @return UserResponseFullDto
     */
    @Override
    public UserResponseFullDto getUsersAllForPage(Integer page, Integer size) {

        UserResponseFullDto userResponseFullDto = null;

        Pageable pageable = PageRequest.of(page, size);

        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);

        if (userEntityPage != null && !userEntityPage.isEmpty())  {
            userResponseFullDto = userMapper.listUserDTOs(userEntityPage.getContent());
            userResponseFullDto.setSize(userEntityPage.getSize());
            userResponseFullDto.setCurrentPage(userEntityPage.getNumber() + 1);
            userResponseFullDto.setTotalPages(userEntityPage.getTotalPages());
            userResponseFullDto.setTotalElements((int) userEntityPage.getTotalElements());
            return userResponseFullDto;
        } else {
            throw new UserBadRequestException("No existe registros registrados");
        }
    }

    /**
     * Servicio de eliminacion de usuario por id
     *
     * @param uuid
     */
    @Override
    public void deleteUserById(String uuid) {

        if (uuid == null ||
                uuid.isBlank() ||
                uuid.isEmpty() ) {
            throw new UserBadRequestException("Codigo de UUID de usuario es requerido " + uuid);
        }
        Optional<UserEntity> userEntity = userRepository.getUserEntityByUuid(uuid);

        if (!userEntity.isPresent()) {
            throw new UserBadRequestException("uuid de usuario no existe " + uuid);
        }
        userRepository.deleteById(userEntity.get().getIdUser());

    }

    /**
     * Servicio para consultas de usuarios
     *
     * @return UserResponseFullDto
     */
    @Override
    public UserResponseDtos getUsersAll() {

        UserResponseDtos userResponseDtos = null;

        List<UserEntity> userEntityPage = userRepository.findAll();

        if (userEntityPage != null && !userEntityPage.isEmpty())  {
            userResponseDtos = userMapper.listUserDTOsb(userEntityPage);
            return userResponseDtos;
        } else {
            throw new UserBadRequestException("No existe registros registrados");
        }
    }

    /**
     * Servicio de creacion de usuario
     *
     * @param uuidUser
     * @param request
     * @return
     */
    @Override
    public UserResponseDto updateUser(String uuidUser, UserRequestActDto request) {

        UserResponseDto userResponseDto;
        List<UserPhoneResponseDto> userPhoneResponseDtoList = new ArrayList<>();

        if (uuidUser == null ||
                uuidUser.isBlank() ||
                uuidUser.isEmpty() ) {
            throw new UserBadRequestException("Codigo de UUID de usuario es requerido " + uuidUser);
        }

        /* Valido los datos que ingresan */
        userValidationUtil.validateRequestActDto(request);

        Optional<UserEntity> userEntity = userRepository.getUserEntityByUuid(uuidUser);

        if (!userEntity.isPresent()) {
            throw new UserBadRequestException("uuid de usuario no existe " + uuidUser);
        }

        /* Hago los mapeos de los datos para el guardado de la informacion */
        UserEntity userEntityUpd = userMapper.dtoToEntity(request);
        /* Se guarda la informacion de la data de user */
        userEntityUpd.setUuid(userEntity.get().getUuid());
        userEntityUpd.setIdUser(userEntity.get().getIdUser());
        userEntityUpd = userRepository.save(userEntityUpd);


        for (UserPhoneRequestActDto listPhone: request.getUserPhones()) {
            UserPhonesEntity userPhonesEntity = userPhoneMapper.dtoToEntity(listPhone, userEntityUpd);
            userPhonesEntity = userPhoneRepository.save(userPhonesEntity);
            /* Se genera mapeo de las caracteristicas del detalle de telefono */
            UserPhoneResponseDto userPhoneResponseDto = new UserPhoneResponseDto();
            userPhoneResponseDto = userPhoneMapper.entityToDtoResponse(userPhonesEntity);
            userPhoneResponseDtoList.add(userPhoneResponseDto);
        }

        userResponseDto = userMapper.entityToDtoResponse(userEntityUpd);
        userResponseDto.setUserPhoneResponseDtoList(userPhoneResponseDtoList);

        return userResponseDto;
    }
}
