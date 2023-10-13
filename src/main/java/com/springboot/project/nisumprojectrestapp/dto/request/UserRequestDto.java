package com.springboot.project.nisumprojectrestapp.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "UserRequestDto",
        description = "Represents the data needed to created user"
)
public class UserRequestDto {

    private String name;

    private String email;

    private String password;

    private List<UserPhoneRequestDto> userPhones;

}
