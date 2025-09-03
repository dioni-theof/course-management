package org.sonja.course_management.exeptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonja.course_management.models.SuccessResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<SuccessResponse> handleException(DataIntegrityViolationException ex) {
        logger.error("hi i am a constrainviolationException", ex);


        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolationException) {

            return ResponseEntity.badRequest().body(new SuccessResponse(constraintViolationException.getErrorMessage()));
        }

        return ResponseEntity.badRequest().body((new SuccessResponse(ex.getMessage())));
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<SuccessResponse> handleConstraintException(TransactionSystemException ex) {
        logger.error("Hi i am a constraint exceprion ", ex);

        if (ex.getCause().getCause() instanceof ConstraintViolationException constraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            String message = "";
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                message = message.concat(constraintViolation.getMessage());
            }
            return ResponseEntity.badRequest().body(new SuccessResponse(message));
        }

        return ResponseEntity.badRequest().body(new SuccessResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SuccessResponse> handleException(Exception ex) {
        logger.error("Exception:  ", ex);

        return ResponseEntity.badRequest().body(new SuccessResponse(ex.getMessage()));
    }
}
