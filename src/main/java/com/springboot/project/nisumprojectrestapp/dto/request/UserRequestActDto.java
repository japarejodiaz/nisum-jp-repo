package com.springboot.project.nisumprojectrestapp.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "UserRequestActDto",
        description = "Represents the data needed to created user"
)
public class UserRequestActDto {

    private String name;

    private String email;

    private String password;

    private List<UserPhoneRequestActDto> userPhones;

}
