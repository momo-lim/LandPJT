package io.ssafy.ssafyland.api.controller;

import io.ssafy.ssafyland.api.dto.UserDto;
import io.ssafy.ssafyland.api.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "사용자 api", tags = {"User"})
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * create
     */

    @PostMapping()
    @ApiOperation(value = "사용자 등록", notes = "사용자 정보를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<?> register(
            @RequestBody @ApiParam(value="회원 정보", required = true) UserDto.UserCreateReq userCreateReq) {
        log.info("user register", "INFO");

        userService.createUser(userCreateReq);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/treasure")
    @ApiOperation(value = "사용자 보물 발견", notes = "사용자가 보물을 발견하면 찾은 개수를 올린다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<UserDto.UserPatchRes> increaseTreasureCount(
            @RequestBody @ApiParam(value="회원 정보", required = true) UserDto.UserPatchReq userPatchReq) {
        log.info("user treasure", "INFO");
        UserDto.UserPatchRes userPatchRes = userService.increaseTreasureCount(userPatchReq);
        return ResponseEntity.ok().body(userPatchRes);
    }

    @PostMapping("/forest")
    @ApiOperation(value = "인내의 숲 기록 등록", notes = "인내의 숲 기록을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<UserDto.UserPatchRes> recordForest(
            @RequestBody @ApiParam(value="인내의 숲 시간", required = true) UserDto.UserForestReq userForestReq) {
        log.info("user forest", "INFO");
        UserDto.UserPatchRes userPatchRes = userService.recordForest(userForestReq);
        return ResponseEntity.ok().body(userPatchRes);
    }

    /**
     * read
     */

    @GetMapping("/treasure")
    @ApiOperation(value = "보물 찾기 기록 조회", notes = "보물 찾기 기록 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<Map<String, Object>> getTreasureList() {
        log.info("get user treasure count list", "INFO");
        List<UserDto.UserRes> userRes = userService.getTreasureList();

        Map<String, Object> result = new HashMap<>();
        result.put("result", userRes);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/forest")
    @ApiOperation(value = "인내의 숲 기록 조회", notes = "인내의 숲 기록 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<Map<String, Object>> getForestList() {
        log.info("get user forest list", "INFO");
        List<UserDto.UserRes> userForestRes = userService.getForestList();

        Map<String, Object> result = new HashMap<>();
        result.put("result", userForestRes);
        return ResponseEntity.ok().body(result);
    }

    /**
     * update
     */

    @GetMapping("/treasure/reset")
    @ApiOperation(value = "보물 찾기 기록 리셋", notes = "보물 찾기 기록을 리셋한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<?> resetTreasureCount() {
        log.info("reset user treasure count", "INFO");
        userService.resetTreasureCount();
        return ResponseEntity.ok().build();
    }
}
