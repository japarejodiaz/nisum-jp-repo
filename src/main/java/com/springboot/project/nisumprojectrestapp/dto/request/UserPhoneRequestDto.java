package com.springboot.project.nisumprojectrestapp.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "UserPhoneRequestDto",
        description = "Represents the data needed to created user"
)
public class UserPhoneRequestDto {


    private String numberPhone;


    private String cityCode;


    private String countryCode;

}
