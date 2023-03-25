package com.bms.fakestoreapp.core.exceptions.detais;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InternalServerExceptionDetail extends ProblemDetail {
    public InternalServerExceptionDetail(String detail) {
        setTitle("Internal Server Exception");
        setDetail(detail);
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        setType(URI.create("https://example.com/internal-server-exception"));
        setProperty("timestamp", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
    }
}
