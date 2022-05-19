package io.ssafy.ssafyland.api.service;

import io.ssafy.ssafyland.api.dto.UserDto;
import io.ssafy.ssafyland.common.exception.BusinessException;
import io.ssafy.ssafyland.common.exception.ErrorCode;
import io.ssafy.ssafyland.db.entity.User;
import io.ssafy.ssafyland.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(UserDto.UserCreateReq userCreateReq) {
        User user = userRepository.findByUserName(userCreateReq.getUserName())
                .orElseGet(() -> User.builder()
                        .userName(userCreateReq.getUserName())
                        .build());

        userRepository.save(user);
    }

    @Override
    public UserDto.UserPatchRes increaseTreasureCount(UserDto.UserPatchReq userPatchReq) {
        User user = getUser(userPatchReq.getUserName());

        user.increaseTreasureCount();
        userRepository.save(user);

        return UserDto.UserPatchRes.of(user);
    }

    @Override
    public UserDto.UserPatchRes recordForest(UserDto.UserForestReq userForestReq) {
        User user = getUser(userForestReq.getUserName());

        user.recordForest(userForestReq.getForestRecord());
        userRepository.save(user);
        return UserDto.UserPatchRes.of(user);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public List<UserDto.UserRes> getTreasureList() {
        return userRepository.findUserTreasureList();
    }

    @Override
    public List<UserDto.UserRes> getForestList() {
        return userRepository.findUserForestListByBest();
    }

    @Override
    public void resetTreasureCount() {
        userRepository.updateTreasureCountReset();
    }
}
