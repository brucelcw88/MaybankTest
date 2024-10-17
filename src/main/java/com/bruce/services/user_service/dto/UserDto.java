package com.bruce.services.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // is to allow to modify existing data. E.g. u1 = UserDto.builder().name("abc").age(2).build() --> u2 = u1.toBuilder.age(1).build()   [abc,2 --> abc,1]
public class UserDto {

    private String name;
    private String email;
    private int age;
    private String address;
}
