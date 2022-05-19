package io.ssafy.ssafyland.api.service;

import io.ssafy.ssafyland.api.dto.TreasureDto;
import io.ssafy.ssafyland.api.dto.UserDto;
import io.ssafy.ssafyland.db.entity.Treasure;
import io.ssafy.ssafyland.db.entity.User;
import io.ssafy.ssafyland.db.repository.TreasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TreasureServiceImpl implements TreasureService {
    @Autowired
    TreasureRepository treasureRepository;

    @Autowired
    UserService userService;

    @Override
    public void createTreasure(TreasureDto.TreasureCreateReq treasureCreateReq) {
        Treasure treasure = Treasure.builder()
                .treasureName(treasureCreateReq.getTreasureName())
                .treasureCode(UUID.randomUUID().toString())
                .build();
        treasureRepository.save(treasure);
    }

    @Override
    public void registerUser(Long treasureId, UserDto.UserCreateReq userCreateReq) {
        Treasure treasure = getTreasure(treasureId);
        User user = userService.getUser(userCreateReq.getUserName());

        treasure.setUser(user);
        treasureRepository.save(treasure);
    }

    @Override
    public List<TreasureDto.TreasureListRes> getList() {
        List<Treasure> treasureList =  treasureRepository.findAll();
        List<TreasureDto.TreasureListRes> treasureListRes = new ArrayList<>();

        for (Treasure treasure : treasureList) {
            treasureListRes.add(TreasureDto.TreasureListRes.of(treasure));
        }

        return treasureListRes;
    }

    private Treasure getTreasure(Long treasureId) {
        return treasureRepository.findById(treasureId)
                .orElseThrow(() -> new IllegalArgumentException("no such data"));       //  찾는 사용자 정보가 없을 경우 error
    }
}
