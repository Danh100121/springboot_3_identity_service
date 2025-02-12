package com.danhpahm.identity_service.service;


import com.danhpahm.identity_service.dto.request.UserCreationRequest;
import com.danhpahm.identity_service.dto.response.UserResponse;
import com.danhpahm.identity_service.entity.User;
import com.danhpahm.identity_service.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void setUp() {
        dob = LocalDate.of(2001, 04, 10);

        request = UserCreationRequest.builder()
                .username("hoangnh2")
                .firstName("nguyen")
                .lastName("huy hoang")
                .password("11111111")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("cf0600f538b3")
                .username("hoangnh2")
                .firstName("nguyen")
                .lastName("huy hoang")
                .dob(dob)
                .build();

        user = User.builder()
                .id("cf0600f538b3")
                .username("hoangnh2")
                .firstName("nguyen")
                .lastName("huy hoang")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        //GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        //WHEN
        var response = userService.createUser(request);

        //THEN
        Assertions.assertThat(response.getId()).isEqualTo("cf0600f538b3");
        Assertions.assertThat(response.getUsername()).isEqualTo("hoangnh2");
    }
}
