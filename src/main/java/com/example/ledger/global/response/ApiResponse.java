package com.example.ledger.global.response;

import com.example.ledger.global.exception.ErrorItem;
import com.example.ledger.global.exception.Field;

import java.util.List;

public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new <T>ApiResponse<T>(true, message, data);
    }

    public static ApiResponse<ErrorItem> fail(String message, int code, List<Field> errors) {
        return new ApiResponse(false, message, new ErrorItem(code, errors));
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
//
//{
//  "success": true,
//  "message": "OK",
//  "data": {}
//}
//
// ErrorItem
// private final String code;
// private final Lsit<Field> errors;

// Field
// private final String name;
// private final String reason;
//{
//  "success": false,
//  "message": "입력값이 올바르지 않습니다.",
//  "data": {
//    "code": "VALIDATION_ERROR",
//    "errors": [
//      { "field": "name", "reason": "상품명은 필수입니다." },
//      { "field": "initialStock", "reason": "0 이상이어야 합니다." }
//    ]
//  }
//}
