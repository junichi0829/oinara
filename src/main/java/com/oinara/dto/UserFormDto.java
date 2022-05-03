package com.oinara.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserFormDto {

    @NotBlank(message = "이름은 필수로 입력해야합니다.")
    private String name;

    @NotEmpty(message = "아이디는 필수로 입력해야합니다.")
    private String account;

    @NotEmpty(message = "비밀번호는 필수로 입력해야합니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해 주세요.")
    private String password;

    @NotEmpty(message = "이메일은 필수로 입력해야합니다.")
    @Email(message = "이메일 형식에 맞춰서 입력해 주세요.")
    private String email;

    @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
    private String phoneNumber;

}
