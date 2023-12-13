package com.mysite.hosung.service.userService;

import com.mysite.hosung.web.dto.UserRequestDTO.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{

    @Override
    public boolean checkPasswordEquals(UserCreateDTO userCreateDTO) {
        return userCreateDTO.getPassword1().equals(userCreateDTO.getPassword2());
    }
}
