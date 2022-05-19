package io.ssafy.ssafyland.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),

    NOT_FOUND_USER(NOT_FOUND,  "사용자 정보가 없습니다."),
    NOT_FOUND_FILE(NOT_FOUND,  "파일이 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
