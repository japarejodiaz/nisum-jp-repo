package com.springboot.project.nisumprojectrestapp.mapper;

import com.springboot.project.nisumprojectrestapp.dto.request.UserPhoneRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserPhoneResponseDto;
import com.springboot.project.nisumprojectrestapp.entity.UserEntity;
import com.springboot.project.nisumprojectrestapp.entity.UserPhonesEntity;

import java.util.List;

public interface IUserPhoneMapper {

    UserPhonesEntity dtoToEntity (UserPhoneRequestDto userPhoneRequestDto, UserEntity userEntity);

    UserPhoneRequestDto entityToDtoRequest (UserPhonesEntity userPhonesEntity);


    UserPhoneResponseDto entityToDtoResponse(UserPhonesEntity userPhonesEntity);

    List<UserPhoneResponseDto> entityToDtoResponseList(List<UserPhonesEntity> userPhonesEntity);
}
