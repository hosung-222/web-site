package com.mysite.hosung.service.userService;

import com.mysite.hosung.domain.User;
import com.mysite.hosung.repository.UserRepository;
import com.mysite.hosung.web.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandServiceImpl implements UserCommandService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserRequestDTO.UserCreateDTO createUserDTO) {

        User user = User.builder()
                .name(createUserDTO.getName())
                .password(passwordEncoder.encode(createUserDTO.getPassword1()))
                .email(createUserDTO.getEmail())
                .build();

        return userRepository.save(user);
    }


}
