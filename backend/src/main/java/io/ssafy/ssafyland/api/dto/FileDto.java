package io.ssafy.ssafyland.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("FileDto")
public class FileDto {
    @ApiModelProperty(name="이름", example="ssafy")
    String fileName;

}
