package com.springboot.project.nisumprojectrestapp.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "UserPhoneRequestActDto",
        description = "Represents the data needed to created user"
)
public class UserPhoneRequestActDto {

    private Long idPhone;
    private Long idUser;
    private String numberPhone;
    private String cityCode;
    private String countryCode;

}
