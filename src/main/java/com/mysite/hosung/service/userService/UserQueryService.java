package com.mysite.hosung.service.userService;

import com.mysite.hosung.web.dto.UserRequestDTO.UserCreateDTO;

public interface UserQueryService {
    boolean checkPasswordEquals(UserCreateDTO userCreateDTO);
}
