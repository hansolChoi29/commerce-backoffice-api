package com.example.ledger.global.response;

public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
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
//  "success": false,
//  "message": "입력값이 올바르지 않습니다.",
//  "data": {
//    "errors": [
//      { "field": "name", "reason": "상품명은 필수입니다." },
//      { "field": "initialStock", "reason": "0 이상이어야 합니다." }
//    ]
//  }
//}
//