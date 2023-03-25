package com.bms.fakestoreapp.core.exceptions.detais;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestExceptionDetail extends ProblemDetail {
    public RequestExceptionDetail(String detail) {
        setTitle("Request Exception");
        setDetail(detail);
        setStatus(HttpStatus.TOO_MANY_REQUESTS);
        setType(URI.create("https://example.com/request-exception"));
        setProperty("timestamp", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }
}
