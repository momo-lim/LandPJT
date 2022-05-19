package io.ssafy.ssafyland.db.repository;

import io.ssafy.ssafyland.api.dto.UserDto;

import java.util.List;

public interface UserCustomRepository {
    List<UserDto.UserRes> findUserTreasureList();
    List<UserDto.UserRes> findUserForestListByBest();
    void updateTreasureCountReset();
}
