package com.task.springshop.util;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String login;
    private String password;
}
