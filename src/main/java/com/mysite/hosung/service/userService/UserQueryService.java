package com.mysite.hosung.service.userService;

import com.mysite.hosung.domain.User;
import com.mysite.hosung.web.dto.UserRequestDTO.UserCreateDTO;
import java.util.Optional;

public interface UserQueryService {
    boolean checkPasswordEquals(UserCreateDTO userCreateDTO);

    User getUser(String name);
}
