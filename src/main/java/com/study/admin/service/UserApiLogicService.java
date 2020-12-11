package com.study.admin.service;

import com.study.admin.ifs.CrudInterface;
import com.study.admin.model.entity.User;
import com.study.admin.model.network.Header;
import com.study.admin.model.network.request.UserApiRequest;
import com.study.admin.model.network.response.UserApiResponse;
import com.study.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> userApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // 1. id -> respository getOne, getById
        // user -> userApiResponse return
        return userRepository.findById(id)
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        // 1. data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            // 3. data -> update
            // id
            user.setAccount(userApiRequest.getAccount())
                .setPassword(userApiRequest.getPassword())
                .setStatus(userApiRequest.getStatus())
                .setPhoneNumber(userApiRequest.getPhoneNumber())
                .setEmail(userApiRequest.getEmail())
                .setRegisteredAt(userApiRequest.getRegisteredAt())
                .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            // 4. userApiResponse 만들기
            return user;
        })
        .map(user -> userRepository.save(user))     // update -> newUser
                .map(updateUser -> response(updateUser))  //userApi Response
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user
        Optional<User> optional = userRepository.findById(id);

        // 2. repository -> delete
        return optional.map(user ->{
            userRepository.delete(user);

            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    private Header<UserApiResponse> response(User user){
        // user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.OK(userApiResponse);
    }
}
