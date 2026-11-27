package com.javabuilder.backendservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Course, Chapter & Lesson
    COURSE_NOT_FOUND("COURSE_NOT_FOUND", "Course not found", HttpStatus.NOT_FOUND),
    CHAPTER_NOT_FOUND("CHAPTER_NOT_FOUND", "Chapter not found", HttpStatus.NOT_FOUND),
    LESSON_NOT_FOUND("LESSON_NOT_FOUND", "Lesson not found", HttpStatus.NOT_FOUND),

    // User & Account
    EMAIL_NOT_FOUND("EMAIL_NOT_FOUND", "Email not found", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "Email already exists", HttpStatus.CONFLICT),
    ROLE_NOT_FOUND("ROLE_NOT_FOUND", "Role not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND),

    // Authentication
    USER_NOT_AUTHENTICATED("USER_NOT_AUTHENTICATED", "User not authenticated", HttpStatus.UNAUTHORIZED),
    TOKEN_GENERATE_FAILED("TOKEN_GENERATE_FAILED", "Failed to generate token", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
