package com.task.springshop.util;

import com.task.springshop.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private String login;
    private Role role;
    private String access_token;
    private String refresh_token;
}
