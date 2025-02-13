package com.ineos.management.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class IneosError implements Serializable {

    private HttpStatus status;

    private final Instant time;

    private final String message;

    private final String localizedMessage;

    private final List<IneosSubError> subErrorList;
}

