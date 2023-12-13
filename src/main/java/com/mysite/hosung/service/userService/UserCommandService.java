package com.mysite.hosung.service.userService;

import com.mysite.hosung.domain.User;
import com.mysite.hosung.web.dto.UserRequestDTO;
import com.mysite.hosung.web.dto.UserRequestDTO.UserCreateDTO;

public interface UserCommandService {

    User create(UserRequestDTO.UserCreateDTO createUserDTO);


}
