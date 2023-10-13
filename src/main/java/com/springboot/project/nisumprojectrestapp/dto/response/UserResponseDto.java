package com.springboot.project.nisumprojectrestapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(
        value = "UserResponseDto",
        description = "Represents the data needed to users information"
)
public class UserResponseDto {

    private Long idUser;
    private String uuidUser;
    private String name;
    private String correo;
    private String createdDate;
    private String modifiedDate;
    private String lastLogin;
    private String token;
    private Boolean isActive;
    private List<UserPhoneResponseDto> userPhoneResponseDtoList;

}
