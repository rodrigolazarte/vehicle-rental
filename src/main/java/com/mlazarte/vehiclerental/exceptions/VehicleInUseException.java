package com.mlazarte.vehiclerental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VehicleInUseException extends ResponseStatusException {
    public VehicleInUseException(HttpStatus status) {
        super(status);
    }

    public VehicleInUseException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public VehicleInUseException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public VehicleInUseException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}
