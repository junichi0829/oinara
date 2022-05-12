package com.oinara.entity;

import com.oinara.constant.Role;
import com.oinara.dto.UserFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String account;

    private String password;

    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {

        User user = new User();
        user.setName(userFormDto.getName());
        user.setAccount(userFormDto.getAccount());
        user.setEmail(userFormDto.getEmail());
        user.setPhoneNumber(userFormDto.getPhoneNumber());
        String password = passwordEncoder.encode(userFormDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.ADMIN);
        return user;
    }
}
