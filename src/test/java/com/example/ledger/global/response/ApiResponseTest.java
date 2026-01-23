package com.example.ledger.global.response;


import com.example.ledger.global.exception.ErrorItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ApiResponseTest {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @DisplayName("ApiResponse 직렬화")
    void success_response_serializes() throws Exception {
        ApiResponse<String> response = ApiResponse.ok("hello", "d");
        String json = objectMapper.writeValueAsString(response);

        assertThat(json).contains("\"success\":true");
        assertThat(json).contains("\"data\":\"d\"");
    }

    @Test
    @DisplayName("예상 포맷과 다를 경우 예외")
    void fail_response() throws Exception {
        ApiResponse<ErrorItem> response = ApiResponse.fail("ERROR", "400", null);
        String json = objectMapper.writeValueAsString(response);

        assertThat(json).contains("\"success\":false");

        // assertThat(json).contains("\"data\":\"400\"");
        assertThat(json).contains("ERROR");
    }
}