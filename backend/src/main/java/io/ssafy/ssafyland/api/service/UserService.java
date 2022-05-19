package io.ssafy.ssafyland.api.service;

import io.ssafy.ssafyland.api.dto.UserDto;
import io.ssafy.ssafyland.db.entity.User;

import java.util.List;

public interface UserService {
    void createUser(UserDto.UserCreateReq userCreateReq);

    UserDto.UserPatchRes increaseTreasureCount(UserDto.UserPatchReq userPatchReq);
    UserDto.UserPatchRes recordForest(UserDto.UserForestReq userForestReq);

    User getUser(Long userId);
    User getUser(String userName);

    List<UserDto.UserRes> getTreasureList();
    List<UserDto.UserRes> getForestList();

    void resetTreasureCount();
}
