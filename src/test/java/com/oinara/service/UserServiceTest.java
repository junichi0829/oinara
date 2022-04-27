package com.oinara.service;

import com.oinara.dto.UserFormDto;
import com.oinara.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser() {
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setAccount("oinaratest");
        userFormDto.setName("홍길동");
        userFormDto.setEmail("test@email.com");
        userFormDto.setPhoneNumber("010-1234-5678");
        userFormDto.setPassword("1234");
        return User.createUser(userFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveUserTest() {
        User user = createUser();
        User savedUser = userService.saveUser(user);

        assertEquals(user.getAccount(), savedUser.getAccount());
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPhoneNumber(), savedUser.getPhoneNumber());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateUserTest() {
        User user1 = createUser();
        User user2 = createUser();
        userService.saveUser(user1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            userService.saveUser(user2);
        });

        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }

}
