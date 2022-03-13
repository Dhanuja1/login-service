package com.techiebean.spring.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorModel {
	
	private String message;
	
	private HttpStatus httpStatus;

    private LocalDateTime timestamp;

    private String details;

    public ErrorModel(HttpStatus httpStatus, String message, String details) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
