package com.springboot.project.nisumprojectrestapp.mapper.impl;

import com.springboot.project.nisumprojectrestapp.dto.request.UserPhoneRequestActDto;
import com.springboot.project.nisumprojectrestapp.dto.request.UserPhoneRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserPhoneResponseDto;
import com.springboot.project.nisumprojectrestapp.entity.UserEntity;
import com.springboot.project.nisumprojectrestapp.entity.UserPhonesEntity;
import com.springboot.project.nisumprojectrestapp.mapper.IUserPhoneMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserPhoneMapperImpl implements IUserPhoneMapper {


    private final ModelMapper modelMapper;

    @Override
    public UserPhonesEntity dtoToEntity(UserPhoneRequestDto userPhoneRequestDto, UserEntity userEntity) {

        UserPhonesEntity userPhonesEntity = new UserPhonesEntity();

        userPhonesEntity.setCityCode(userPhoneRequestDto.getCityCode());
        userPhonesEntity.setCountryCode(userPhoneRequestDto.getCountryCode());
        userPhonesEntity.setNumberPhone(userPhoneRequestDto.getNumberPhone());
        userPhonesEntity.setUserEntity(userEntity);
        return userPhonesEntity;
    }

    @Override
    public UserPhoneRequestDto entityToDtoRequest(UserPhonesEntity userPhonesEntity) {

        return null;
    }

    @Override
    public UserPhoneResponseDto entityToDtoResponse(UserPhonesEntity userPhonesEntity) {
        UserPhoneResponseDto userPhoneResponseDto = new UserPhoneResponseDto();

        userPhoneResponseDto.setIdPhone(userPhonesEntity.getIdPhone());
        userPhoneResponseDto.setIdUser(userPhonesEntity.getUserEntity().getIdUser());
        userPhoneResponseDto.setNumberPhone(userPhonesEntity.getNumberPhone());
        userPhoneResponseDto.setCityCode(userPhonesEntity.getCityCode());
        userPhoneResponseDto.setCountryCode(userPhonesEntity.getCountryCode());

        return userPhoneResponseDto;
    }

    @Override
    public List<UserPhoneResponseDto> entityToDtoResponseList(List<UserPhonesEntity> userPhonesEntity) {
        List<UserPhoneResponseDto> userPhoneResponseDtoList = new ArrayList<>();

        userPhonesEntity.forEach((UserPhonesEntity phonesEntity) -> {
            UserPhoneResponseDto userPhoneResponseDto =
                    UserPhoneResponseDto.builder()
                            .idPhone(phonesEntity.getIdPhone())
                            .idUser(phonesEntity.getUserEntity().getIdUser())
                            .numberPhone(phonesEntity.getNumberPhone())
                            .cityCode(phonesEntity.getCityCode())
                            .countryCode(phonesEntity.getCountryCode())
                            .build();
            userPhoneResponseDtoList.add(userPhoneResponseDto);

        });

        return userPhoneResponseDtoList;
    }

    @Override
    public UserPhonesEntity dtoToEntity(UserPhoneRequestActDto userPhoneRequestDto, UserEntity userEntity) {

        UserPhonesEntity userPhonesEntity = new UserPhonesEntity();

        userPhonesEntity.setIdPhone(userPhoneRequestDto.getIdPhone());
        userPhonesEntity.setCityCode(userPhoneRequestDto.getCityCode());
        userPhonesEntity.setCountryCode(userPhoneRequestDto.getCountryCode());
        userPhonesEntity.setNumberPhone(userPhoneRequestDto.getNumberPhone());
        userPhonesEntity.setUserEntity(userEntity);
        return userPhonesEntity;
    }
}
