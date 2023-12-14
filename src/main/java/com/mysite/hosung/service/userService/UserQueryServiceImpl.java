package com.mysite.hosung.service.userService;

import com.mysite.hosung.apiPayload.DataNotFoundException;
import com.mysite.hosung.domain.User;
import com.mysite.hosung.repository.UserRepository;
import com.mysite.hosung.web.dto.UserRequestDTO.UserCreateDTO;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{
    private final UserRepository userRepository;

    @Override
    public boolean checkPasswordEquals(UserCreateDTO userCreateDTO) {
        return userCreateDTO.getPassword1().equals(userCreateDTO.getPassword2());
    }

    @Override
    public User getUser(String name) {
        Optional<User> user = userRepository.findByName(name);
        if (user.isPresent()){
            return user.get();
        }else {
            throw new DataNotFoundException("user not found");
        }
    }
}
