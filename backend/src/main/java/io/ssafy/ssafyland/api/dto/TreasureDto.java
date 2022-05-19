package io.ssafy.ssafyland.api.dto;

import io.ssafy.ssafyland.db.entity.Treasure;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public class TreasureDto {

    @Getter
    @Setter
    @ApiModel("TreasureCreateReq")
    public static class TreasureCreateReq {
        @ApiModelProperty(name="보물 이름", example="ssafy")
        String treasureName;
    }

    @Getter
    @Setter
    @ApiModel("TreasureListRes")
    public static class TreasureListRes {
        @ApiModelProperty(name="보물 id", example="1")
        Long treasureId;
        @ApiModelProperty(name="보물 이름", example="ssafy")
        String treasureName;
        @ApiModelProperty(name="보물 code", example="UUID")
        String treasureCode;

        public static TreasureListRes of(Treasure treasure) {
            TreasureListRes treasureListRes = new TreasureListRes();

            treasureListRes.setTreasureId(treasure.getTreasureId());
            treasureListRes.setTreasureName(treasure.getTreasureName());
            treasureListRes.setTreasureCode(treasure.getTreasureCode());

            return treasureListRes;
        }
    }
}
