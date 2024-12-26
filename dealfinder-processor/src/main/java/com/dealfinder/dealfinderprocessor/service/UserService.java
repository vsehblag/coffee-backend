package com.dealfinder.dealfinderprocessor.service;

import com.dealfinder.dealfindercommon.dto.DealFinderUserDto;
import com.dealfinder.dealfindercommon.model.DealFinderUser;
import com.dealfinder.dealfindercommon.repository.UserRepository;
import com.dealfinder.dealfinderprocessor.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper mapper;

    @Transactional
    public DealFinderUserDto getOrRegisterUserByPasswordAndLogin(String password, String login) {
        DealFinderUser user = userRepository.findByLoginAndPassword(login, password);
//        if (user == null){
//            throw new RuntimeException("User not found");
//        }
        //todo
        // if user == null create new
        return mapper.dealFinderUserToDealFinderUserDto(user);
    }
}
