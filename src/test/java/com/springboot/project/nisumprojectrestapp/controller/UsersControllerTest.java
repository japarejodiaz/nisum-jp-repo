package com.springboot.project.nisumprojectrestapp.controller;

import com.springboot.project.nisumprojectrestapp.dto.request.UserPhoneRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseFullDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class UsersControllerTest {

    @Autowired
    private WebTestClient client;


    @Test
    void testCreateUser() {

        UserRequestDto request = new UserRequestDto();
        request.setName("Jose diaz");
        request.setPassword("Jose$perez01");
        request.setEmail("ja1234-diaz@gmail.com");
        UserPhoneRequestDto requestP = new UserPhoneRequestDto();
        requestP.setCountryCode("ARG");
        requestP.setCityCode("01");
        requestP.setNumberPhone("1234465");
        List<UserPhoneRequestDto> requestPList = new ArrayList<>();
        requestPList.add(requestP);
        request.setUserPhones(requestPList);

        client.post().uri("http://localhost:8080/users/addUser")
                .headers( headers -> headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTcyNTIxNDAsInVzZXJfbmFtZSI6ImphcGFyZWpvQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwianRpIjoiSUVRbklsalQzaU92UkNjOHZGbFVqSG5Ka1AwIiwiY2xpZW50X2lkIjoiYW5ndWxhcmFwcCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.E96pi8XgUvFphkM_v5TRP__w_hDQ78VfaeNdejcEsY0"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.uuidUser").isNotEmpty();

    }

}