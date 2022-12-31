package flat.io.myrh.exception;

import flat.io.myrh.response.ErrorResponse;
import flat.io.myrh.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response> handleBadCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage(),400));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(e.getMessage(),404));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationErrors(MethodArgumentNotValidException e) {

        Map<String,Object> errors = new HashMap<>();

        for (FieldError fieldError: e.getBindingResult().getFieldErrors()){
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(400).body(new ErrorResponse("Please fallout your fields carefully",400,errors));
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Response> handleRuntimeExceptions(RuntimeException e) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error","Ops something went wrong!");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Ops something went wrong!",500,errors));
    }

    @ExceptionHandler(CustomRunTimeException.class)
    public final ResponseEntity<Response> handleCustomRunTimeException(CustomRunTimeException e) {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("error", e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage(),500,errors));
    }

}
