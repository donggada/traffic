package com.side.traffic.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //BAD_REQUEST 잘못된 요청
    INVALID_LOGIN_ID(401, "아이디 확인해주세요."),
    INVALID_PASSWORD(401, "비번 확인해주세요."),
    UNAUTHORIZED_ACCESS(401, "인증되지 않은 접근 입니다."),

    //NOT_FOUND 잘못된 리소스 접근
    USER_NOT_FOUND(404, "사용자 정보를 찾을 수 없습니다."),
    CHALLENGE_ORDER_NOT_FOUND(404, "챌린지 신청정보를 찾을 수 없습니다."),
    CHALLENGE_NOT_FOUND(404, "챌린지를 찾을 수 없습니다."),


    //409 CONFLICT 리소스 처리불가
    DUPLICATE_LOGIN_ID(409, "이미 존재하는 로그인 ID 입니다."),
    DUPLICATE_CHALLENGE(409, "이미 신청한 챌린지 입니다."),
    DEPOSIT_OUT_OF_RANGE(409, "보증금이 허용된 범위를 벗어났습니다."),
    CHALLENGE_ALREADY_ENDED(409, "이미 종료된 챌린지 입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다.");

    private final int status;
    private final String message;
}
