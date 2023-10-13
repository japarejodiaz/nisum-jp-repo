package com.springboot.project.nisumprojectrestapp.controller;

import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDtos;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseFullDto;
import com.springboot.project.nisumprojectrestapp.service.IUserService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Api(value = "User Api", tags = {"Users Service"})
@RestController
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@SecurityRequirement(name = "basicAuth")
@Slf4j
public class UsersController {


    @Autowired
    private IUserService userService;

    @PostMapping(
            value = "/addUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "New user by user id", method = "POST",
            security = {@SecurityRequirement(name = "bearer-key")})
    @ApiOperation(
            value = "Associates a new User",
            httpMethod = "POST",
            response = UserResponseDto.class,
            authorizations = { @Authorization(value="basicAuth") }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "If successfully added, it will add the Location HTTP header with the URI for this new resource",
                    responseHeaders = {
                            @ResponseHeader(name = "Location", description = "URI access path to the new resource added")
                    }),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")}
    )
    public ResponseEntity<UserResponseDto> addUser(
            @ApiParam(name = "UserRequestDto", value = "Payload data to create the new user")
            @Valid @RequestBody UserRequestDto userRequest) {

        log.info("Request de datos de User Creacion " + userRequest.toString());

        UserResponseDto userResponseDto = userService.addUser(userRequest);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/userById",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})

    @ApiOperation(value = "Get a contribution by param", httpMethod = "GET", notes = "",
            response = UserResponseDto.class)
    @Operation(summary = "Search user by user id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "If successfully get, it will add the Location HTTP header with the URI for this user information",
                    responseHeaders = {
                            @ResponseHeader(name = "Location", description = "URI access path to the new resource added")
                    }),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")}
    )
    public ResponseEntity<UserResponseDto> getUserById(
            @RequestParam(required = true) Long idUser) {

        log.info("User id" + idUser);
        UserResponseDto userResponseDto = userService.getUserById(idUser);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @GetMapping(value = "/userByUuid",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})

    @ApiOperation(value = "Get a contribution by param", httpMethod = "GET", notes = "",
            response = UserResponseDto.class)
    @Operation(summary = "Search User by UUID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "If successfully get, it will add the Location HTTP header with the URI for this user information",
                    responseHeaders = {
                            @ResponseHeader(name = "Location", description = "URI access path to the new resource added")
                    }),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")}
    )
    public ResponseEntity<UserResponseDto> getUserByUuid(
            @RequestParam(required = true) String uuidUser) {

        log.info("User id" + uuidUser);
        UserResponseDto userResponseDto = userService.getUserByUuid(uuidUser);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @GetMapping(value = "/getAllUsers/{page}/{size}",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Retrieves all Lists User",
            httpMethod = "GET",
            response = UserResponseFullDto[].class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about persons",
                    response = UserResponseFullDto[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<UserResponseFullDto> getAllPersonsForPage(
            @ApiParam(value = "page to display", required = true, example = "1")
            @PathVariable(name = "page") Integer page,
            @ApiParam(value = "number of items per request", required = true, example = "1")
            @PathVariable(name = "size") Integer size) {

        UserResponseFullDto personResponseFullDTOs = userService.getUsersAllForPage(page, size);

        return new ResponseEntity<>(personResponseFullDTOs, HttpStatus.OK);


    }

    @DeleteMapping(value = "/deleteUserById")
    @ApiOperation(value = "Delete a User by Id",
            httpMethod = "DELETE",
            response = Void.class)
    @Operation(summary = "Delete User by Id User", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 204,
                    message = "Body content with basic information about persons",
                    response = UserResponseFullDto[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity deleteUserById(@ApiParam(name = "idUser", value = "Value user")
                                 @RequestParam(value = "idUser", required = false) Long idUser) {

        log.debug("Deleting user [request]: {}", idUser);
        userService.deleteUserById(idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/getAll",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Retrieves all Lists User",
            httpMethod = "GET",
            response = UserResponseDtos.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about persons",
                    response = UserResponseDtos.class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<UserResponseDtos> getAll() {

        log.info("entre a consultar");

        UserResponseDtos personResponseFullDTOs = userService.getUsersAll();

        return new ResponseEntity<>(personResponseFullDTOs, HttpStatus.OK);


    }




}
