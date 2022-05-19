package io.ssafy.ssafyland.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ssafy.ssafyland.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalTime;

public class UserDto {

    @Getter
    @Setter
    @ApiModel("UserCreateReq")
    public static class UserCreateReq {
        @ApiModelProperty(name="이름", example="ssafy")
        String userName;
    }

    @Getter
    @Setter
    @ApiModel("UserPatchReq")
    public static class UserPatchReq {
        @ApiModelProperty(name="이름", example="ssafy")
        String userName;
    }

    @Getter
    @Setter
    @ApiModel("UserForestReq")
    public static class UserForestReq {
        @ApiModelProperty(name="이름", example="ssafy")
        String userName;
        @ApiModelProperty(name="인내의 숲 기록", example="00:00:00")
        @DateTimeFormat(pattern = "HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        LocalTime forestRecord;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("UserPatchRes")
    public static class UserPatchRes {
        @ApiModelProperty(name="이름", example="ssafy")
        String userName;
        @ApiModelProperty(name="보물 찾은 개수", example="0")
        int treasureCount;
        @ApiModelProperty(name="인내의 숲 기록", example="00:00:00")
        @DateTimeFormat(pattern = "HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        LocalTime forestRecord;

        public static UserPatchRes of(User user) {
            UserPatchRes userPatchRes = new UserPatchRes();

            userPatchRes.setUserName(user.getUserName());
            userPatchRes.setTreasureCount(user.getTreasureCount());
            userPatchRes.setForestRecord(user.getForestRecord().toLocalTime());

            return userPatchRes;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("UserPatchRes")
    public static class UserRes {
        @ApiModelProperty(name="이름", example="ssafy")
        String userName;
        @ApiModelProperty(name="보물 찾은 개수", example="0")
        int treasureCount;
        @ApiModelProperty(name="인내의 숲 기록", example="00:00:00")
        @DateTimeFormat(pattern = "HH:mm:ss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        LocalTime forestRecord;
        @ApiModelProperty(name="보물 및 인내의 숲 랭크 순위", example="0")
        int lank;

        public UserRes(String userName, int treasureCount, Time forestRecord, int lank) {
            this.userName = userName;
            this.treasureCount = treasureCount;
            this.forestRecord = forestRecord.toLocalTime();
            this.lank = lank;
        }

        public static UserRes of(User user) {
            UserRes userForestRes = new UserRes();

            userForestRes.setUserName(user.getUserName());
            userForestRes.setForestRecord(user.getForestRecord().toLocalTime());

            return  userForestRes;
        }
    }
}
