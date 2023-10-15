package com.springboot.project.nisumprojectrestapp.controller;

import com.springboot.project.nisumprojectrestapp.dto.request.UserPhoneRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.request.UserRequestDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseDto;
import com.springboot.project.nisumprojectrestapp.dto.response.UserResponseFullDto;
import com.springboot.project.nisumprojectrestapp.service.IUserService;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class UsersControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    void testCreateUser() {
        String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTczODg4MDMsInVzZXJfbmFtZSI6ImphcGFyZWpvQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwianRpIjoiblF5SU5oOUd5M1B2TkszcVpGcTV4TGVMbXBRIiwiY2xpZW50X2lkIjoiYW5ndWxhcmFwcCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.QOWCMxah0JN6xXCoZmCXk9J14HBouvJLSlyZg5ZWSTY";
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
                .headers(headers -> headers.set("Authorization",token))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isUnauthorized();


    }

    @Test
    void voidGetAllUser() throws Exception {

        String token = "Bearer " +
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTczODkwNDIsInVzZXJfbmFtZSI6ImphcGFyZWpvQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwianRpIjoiNWROTkhVQ292b0dBT1Vqd3dGMWNudlFKUWxJIiwiY2xpZW50X2lkIjoiYW5ndWxhcmFwcCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.cEZgiqFQZyoK5e56v7bRL244MdU4JtgyABRK_sbKH_c";
        client.get().uri("http://localhost:8080/users/getAll")
                .headers(headers -> headers.set("Authorization", token))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();


    }


}