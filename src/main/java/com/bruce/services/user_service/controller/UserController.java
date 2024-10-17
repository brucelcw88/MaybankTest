package com.bruce.services.user_service.controller;

import com.bruce.services.user_service.dao.User;
import com.bruce.services.user_service.dto.UserDto;
import com.bruce.services.user_service.dto.UserResponseDto;
import com.bruce.services.user_service.exception.UserNotFoundException;
import com.bruce.services.user_service.logging.ApiLogger;
import com.bruce.services.user_service.services.impl.UserServiceImpl;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/v1/user-crud")
@Slf4j
public class UserController {

    /*
     * TODO
     * 1. create java springboot project ✅
     * 2. CRUD methods ✅
     * 3. Add database - postgres/mssql ✅
     * 4. Use @Transactional for CRU ✅
     * 5. Pagination ✅
     * 6. Nested calling api from 3rd party
     * 7. Logs request and response to a log file ✅
     */

    private UserServiceImpl userService;
    private UserResponseDto userResponseDto = new UserResponseDto();

    private ApiLogger apiLogger;

    public UserController(UserServiceImpl userService, ApiLogger apiLogger) {
        this.userService = userService;
        this.apiLogger = apiLogger;
    }

    @GetMapping("/retrieve-users")
    public ResponseEntity<UserResponseDto> getUser() {
        log.info("In retrieve user method");

        try {
            userResponseDto = UserResponseDto.builder()
                    .code(HttpStatus.OK.value())
                    .message("User lists retrieved successfully!")
                    .data(userService.getUser())
                    .build();

            // Log api call
            String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            logApiRequestResponseToLogFile(url, "getUser", null);

            return ResponseEntity.ok(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userResponseDto);
        }
    }

    @GetMapping("/retrieve-users/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        log.info("In retrieve user by id method");

        try {
            userResponseDto = UserResponseDto.builder()
                    .code(HttpStatus.OK.value())
                    .message(String.format("User [id:%s] retrieved successfully!", id.toString()))
                    .data(Arrays.asList(userService.getUserById(id)))
                    .build();

            // Log api call
            String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            logApiRequestResponseToLogFile(url, "getUserById", null);

            return ResponseEntity.ok(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userResponseDto);
        }
    }

    @GetMapping("/retrieve-users-by-page")
    public ResponseEntity<UserResponseDto> getUserByPagination(@RequestParam int pageNumber,
            @RequestParam int pageSize) {
        log.info("In retrieve user by id method:{}|{}", pageNumber, pageSize);

        try {
            userResponseDto = UserResponseDto.builder()
                    .code(HttpStatus.OK.value())
                    .message(String.format("User lists retrieved by %d record(s) per page in page %d successfully!",
                            pageSize, pageNumber))
                    .data(userService.getUserByPagination(pageNumber, pageSize))
                    .build();

            // Log api call
            String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            logApiRequestResponseToLogFile(url, "getUserByPagination", null);

            return ResponseEntity.ok(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userResponseDto);
        }
    }

    @PostMapping("/create-users")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserDto userDto) {
        log.info("In create user method");

        try {
            userResponseDto = UserResponseDto.builder()
                    .code(HttpStatus.OK.value())
                    .message("New user created successfully!")
                    .data(Arrays.asList(userService.addUser(userDto)))
                    .build();

            // Log api call
            String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            logApiRequestResponseToLogFile(url, "createUser", null);

            return ResponseEntity.ok(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userResponseDto);
        }
    }

    @PutMapping("/update-users/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        log.info("In update user method");

        try {
            userResponseDto = UserResponseDto.builder()
                    .code(HttpStatus.OK.value())
                    .message(String.format("User [id:%s] updated successfully!", id.toString()))
                    .data(Arrays.asList(userService.updateUser(id, userDto)))
                    .build();

            // Log api call
            String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            logApiRequestResponseToLogFile(url, "updateUser", null);

            return ResponseEntity.ok(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userResponseDto);
        }
    }

    @DeleteMapping("/delete-users/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable UUID id) {
        log.info("In delete user method");

        try {
            userService.deleteUser(id);
            userResponseDto = UserResponseDto.builder()
                    .code(HttpStatus.OK.value())
                    .message(String.format("User [id:%s] deleted successfully!", id.toString()))
                    .data(new ArrayList<>())
                    .build();

            // Log api call
            String url = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            logApiRequestResponseToLogFile(url, "deleteUser", null);

            return ResponseEntity.ok().body(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userResponseDto);
        }
    }

    private void logApiRequestResponseToLogFile(String url, String methodName, String requestBody)
            throws NoSuchMethodException {
        apiLogger.logApiCall(url, getMappingType(this.getClass().getMethod(methodName)), requestBody);
    }

    private String getMappingType(Method method) {
        if (method.isAnnotationPresent(GetMapping.class)) {
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            System.out.println("getMapping = " + getMapping);
            return "GET";
        } else if (method.isAnnotationPresent(PostMapping.class)) {
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            return "POST";
        } else if (method.isAnnotationPresent(DeleteMapping.class)) {
            DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
            return "DELETE";
        } else if (method.isAnnotationPresent(PutMapping.class)) {
            PutMapping putMapping = method.getAnnotation(PutMapping.class);
            return "PUT";
        } else if (method.isAnnotationPresent(PatchMapping.class)) {
            PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
            return "PATCH";
        }
        return null; // No mapping found
    }
}
