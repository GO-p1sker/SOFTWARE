package com.AngButter.dialysisLounge;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
//데이터베이스에서 엔티티 or 데이터 찾을 수 없을때 발생시키는 예외클래스 404오류 반환
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DataNotFoundException(String message) {
        super(message);
    }
}
