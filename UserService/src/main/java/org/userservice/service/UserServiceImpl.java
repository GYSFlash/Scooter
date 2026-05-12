package org.userservice.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.userservice.dto.UserRequestDto;
import org.userservice.dto.UserResponseDto;
import org.userservice.entity.User;
import org.userservice.mapper.UserMapper;
import org.userservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public User addUser(UserRequestDto userRequestDto) {
            log.info("Добавление пользователя");
            if (userRequestDto == null) {
                log.error("Ошибка добавления пустого пользователя");
            }
            userRepository.create(userMapper.toUser(userRequestDto));
            return userMapper.toUser(userRequestDto);
    }
    @Override
    public void deleteUser(UUID id) {
            log.info("Удаление пользователя");
            if (id == null) {
                log.error("Ошибка, пустой id");
            }
            userRepository.deleteById(id);
    }
    @Override
    public void updateUser(UUID id, UserRequestDto userRequestDto) {
        try {
            if (userRequestDto == null || id == null) {
                log.error("Ошибка, пустой пользователь");
            }

            log.info("Обновление пользователя");
            userRepository.update(userMapper.toUser(userRequestDto));

        }catch (Exception e){
            log.error("Ошибка обновления пользователя");
        }
    }
    @Override
    public UserResponseDto findUserById(UUID id) {
            if (id == null) {
                log.error("Ошибка, пустой id");
            }
            log.info("Получение пользователя");
            User user = userRepository.findById(id).orElse(null);
            return userMapper.toUserResponseDto(user);
    }
    @Override
    public UserResponseDto findUserByEmail(String email) {
            if (email == null) {
                log.error("Ошибка, пустой email");
            }
            log.info("Получение пользователя по email");
            User user = userRepository.findByEmail(email).orElse(null);
            return userMapper.toUserResponseDto(user);
    }
    @Override
    public List<UserResponseDto> findAll() {
        log.info("Получение всех пользователей");
        return userMapper.toUserResponseDtos(userRepository.findAll());
    }
}
