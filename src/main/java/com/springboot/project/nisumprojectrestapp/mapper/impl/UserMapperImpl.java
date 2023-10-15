package com.springboot.project.nisumprojectrestapp.mapper.impl;

import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestActDto;
import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDtos;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseFullDto;
import com.springboot.project.nisumprojectrestapp.entity.UserEntity;
import com.springboot.project.nisumprojectrestapp.mapper.IUserMapper;
import com.springboot.project.nisumprojectrestapp.mapper.IUserPhoneMapper;
import com.springboot.project.nisumprojectrestapp.utils.InterceptorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class UserMapperImpl implements IUserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    private InterceptorUtils interceptorUtils;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUserPhoneMapper userPhoneMapper;

    @Override
    public UserEntity dtoToEntity(UserRequestDto userRequestDto) {

        UserEntity userEntity = new UserEntity();
        String tokenString;

        tokenString= interceptorUtils.getBearerTokenHeader();
        tokenString = tokenString.replace("Bearer","");
        tokenString = tokenString.trim();

        userEntity.setUuid(java.util.UUID.randomUUID().toString());
        userEntity.setName(userRequestDto.getName());
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userEntity.setIsActive(Boolean.TRUE);
        userEntity.setDateCreated(LocalDateTime.now());
        userEntity.setDateModified(LocalDateTime.now());
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setToken(tokenString);

        return userEntity;
    }

    @Override
    public UserResponseDto entityToDtoResponse(UserEntity userEntity) {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setIdUser(userEntity.getIdUser());
        userResponseDto.setUuidUser(userEntity.getUuid());
        userResponseDto.setCorreo(userEntity.getEmail());
        userResponseDto.setIsActive(userEntity.getIsActive());
        userResponseDto.setLastLogin(userEntity.getLastLogin().toString());
        userResponseDto.setCreatedDate(userEntity.getDateCreated().toString());
        if (userEntity.getDateModified().toString() != null) {
            userResponseDto.setModifiedDate(userEntity.getDateModified().toString());
        }
        userResponseDto.setToken(userEntity.getToken());
        userResponseDto.setName(userEntity.getName());
        return userResponseDto;
    }

    @Override
    public UserResponseFullDto listUserDTOs(List<UserEntity> listUserEntities) {

        UserResponseFullDto userResponseFullDto = new UserResponseFullDto();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        listUserEntities.forEach((UserEntity userEntity) -> {
            UserResponseDto userResponseDto =
                    UserResponseDto.builder()
                            .idUser(userEntity.getIdUser())
                            .token(userEntity.getToken())
                            .name(userEntity.getName())
                            .uuidUser(userEntity.getUuid())
                            .correo(userEntity.getEmail())
                            .createdDate(userEntity.getDateCreated().toString())
                            .modifiedDate(userEntity.getDateModified().toString() != null ? userEntity.getDateModified().toString(): null)
                            .lastLogin(userEntity.getLastLogin().toString())
                            .isActive(userEntity.getIsActive())
                            .userPhoneResponseDtoList(userPhoneMapper.entityToDtoResponseList(userEntity.getUserPhonesEntities()))
                            .build();
            userResponseDtoList.add(userResponseDto);

        });
        userResponseFullDto.setUserResponseDtos(userResponseDtoList);
        return userResponseFullDto;
    }

    @Override
    public UserResponseDtos listUserDTOsb(List<UserEntity> listUserEntities) {

        UserResponseDtos userResponseFullDto = new UserResponseDtos();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        listUserEntities.forEach((UserEntity userEntity) -> {
            UserResponseDto userResponseDto =
                    UserResponseDto.builder()
                            .idUser(userEntity.getIdUser())
                            .token(userEntity.getToken())
                            .name(userEntity.getName())
                            .uuidUser(userEntity.getUuid())
                            .correo(userEntity.getEmail())
                            .createdDate(userEntity.getDateCreated().toString())
                            .modifiedDate(userEntity.getDateModified().toString() != null ? userEntity.getDateModified().toString(): null)
                            .lastLogin(userEntity.getLastLogin().toString())
                            .isActive(userEntity.getIsActive())
                            .userPhoneResponseDtoList(userPhoneMapper.entityToDtoResponseList(userEntity.getUserPhonesEntities()))
                            .build();
            userResponseDtoList.add(userResponseDto);

        });
        userResponseFullDto.setUserResponseDtos(userResponseDtoList);
        return userResponseFullDto;
    }

    @Override
    public UserEntity dtoToEntity(UserRequestActDto userRequestDto) {

        UserEntity userEntity = new UserEntity();
        String tokenString;

        tokenString= interceptorUtils.getBearerTokenHeader();
        tokenString = tokenString.replace("Bearer","");
        tokenString = tokenString.trim();

        userEntity.setName(userRequestDto.getName());
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userEntity.setIsActive(Boolean.TRUE);
        userEntity.setDateCreated(LocalDateTime.now());
        userEntity.setDateModified(LocalDateTime.now());
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setToken(tokenString);

        return userEntity;
    }
}
