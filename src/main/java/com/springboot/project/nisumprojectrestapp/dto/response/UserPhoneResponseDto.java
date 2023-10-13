package com.springboot.project.nisumprojectrestapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        value = "UserPhoneResponseDto",
        description = "Represents the data needed to created users phones"
)
public class UserPhoneResponseDto {

    private Long idPhone;

    private Long idUser;

    private String numberPhone;

    private String cityCode;

    private String countryCode;

}
