package io.ssafy.ssafyland.api.service;

import io.ssafy.ssafyland.api.dto.TreasureDto;
import io.ssafy.ssafyland.api.dto.UserDto;

import java.util.List;

public interface TreasureService {
    void createTreasure(TreasureDto.TreasureCreateReq treasureCreateReq);
    void registerUser(Long treasureId, UserDto.UserCreateReq userCreateReq);
    List<TreasureDto.TreasureListRes> getList();
}
