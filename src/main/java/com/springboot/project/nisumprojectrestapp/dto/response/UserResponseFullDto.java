package com.springboot.project.nisumprojectrestapp.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(
        value = "UserResponseFullDto",
        description = "Represents the data needed to Users Information"
)
public class UserResponseFullDto implements Serializable{

    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    private List<UserResponseDto> userResponseDtos;
    @ApiModelProperty(position = 2, notes = "Total pages")
    private Integer totalPages;
    @ApiModelProperty(position = 3, notes = "Current pages")
    private Integer currentPage;
    @ApiModelProperty(position = 4, notes = "Size")
    private Integer size;
    @ApiModelProperty(position = 5, notes = "Size")
    private Integer totalElements;
}

