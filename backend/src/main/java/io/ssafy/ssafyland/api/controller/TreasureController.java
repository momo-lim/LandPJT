package io.ssafy.ssafyland.api.controller;

import io.ssafy.ssafyland.api.dto.TreasureDto;
import io.ssafy.ssafyland.api.dto.UserDto;
import io.ssafy.ssafyland.api.service.TreasureService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(value = "보물 api", tags = {"Treasure"})
@RestController
@RequestMapping("/api/treasure")
public class TreasureController {
    @Autowired
    TreasureService treasureService;

    @PostMapping()
    @ApiOperation(value = "보물 등록", notes = "보물 정보를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<?> register(
            @RequestBody @ApiParam(value="보물 정보", required = true) TreasureDto.TreasureCreateReq treasureCreateReq) {
        log.info("treasure register", "INFO");

        treasureService.createTreasure(treasureCreateReq);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{treasureId}")
    @ApiOperation(value = "보물 찾기 등록", notes = "보물을 찾은 사람을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<?> registerUser(
            @ApiParam(value=" 보물 id 정보", required = true) @PathVariable Long treasureId,
            @RequestBody @ApiParam(value="보물 정보", required = true) UserDto.UserCreateReq userCreateReq) {
        log.info("treasure register user", "INFO");

        treasureService.registerUser(treasureId, userCreateReq);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @ApiOperation(value = "보물 리스트 조회", notes = "보물 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<List<TreasureDto.TreasureListRes>> getList() {
        log.info("treasure list", "INFO");

        List<TreasureDto.TreasureListRes> treasureListRes = treasureService.getList();
        return ResponseEntity.ok().body(treasureListRes);
    }
}
