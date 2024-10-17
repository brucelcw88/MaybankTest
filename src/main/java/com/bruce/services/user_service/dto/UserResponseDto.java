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
@Builder(toBuilder = true) // is to allow to modify existing data. E.g. u1 = UserDto.builder().name("abc").age(2).build() --> u2 = u1.toBuilder.age(1).build()   [abc,2 --> abc,1]
public class UserResponseDto {

    private int code;
    private String message;
    @JsonProperty("data")
    private List<User> data;

}
