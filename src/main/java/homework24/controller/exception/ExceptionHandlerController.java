package homework24.controller.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

  public static final String PATH = "path";
  public static final String STATUS = "status";

  @Override
  @NonNull
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatus status,
      WebRequest request
  ) {
    Map<String, String> errors = new LinkedHashMap<>();

    String path = request.getDescription(false);

    errors.put(PATH, path);
    errors.put(STATUS, status.getReasonPhrase());

    ex.getBindingResult().getAllErrors()
        .forEach(error -> {
          String fieldName = ((FieldError) error).getField();
          String errorMessage = error.getDefaultMessage();
          errors.put(fieldName, errorMessage);
        });

    return ResponseEntity.status(status).body(errors);
  }
}
