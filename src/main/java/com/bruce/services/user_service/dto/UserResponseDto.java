package com.bruce.services.user_service.dto;

import com.bruce.services.user_service.dao.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserResponseDto {

    private int code;
    private String message;
    @JsonProperty("data")
    private List<User> data;

}
