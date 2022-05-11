package com.oinara.controller;

import com.oinara.dto.UserFormDto;
import com.oinara.entity.User;
import com.oinara.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(String account, String password) {
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setAccount(account);
        userFormDto.setName("이가은");
        userFormDto.setPassword(password);
        userFormDto.setEmail("lee@email.com");
        userFormDto.setPhoneNumber("010-1234-5678");
        User user = User.createUser(userFormDto, passwordEncoder);
        return userService.saveUser(user);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception {
        String account = "lee";
        String password = "12345678";
        this.createUser(account, password);
        mockMvc.perform(formLogin().userParameter("account")
                        .loginProcessingUrl("/users/login")
                        .user(account).password(password))
                        .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
}
