package com.lukasdev.lanchonete.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
public class ErrorResponse {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String erro;
    private String message;


}
