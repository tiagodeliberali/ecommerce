package br.com.tiagodeliberali.ecommerce.application.adapter.in.web;

import br.com.tiagodeliberali.ecommerce.core.application.port.out.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    Problem handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldValidationError> fields = new ArrayList<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            fields.add(new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        String detailFields =
                fields.stream().map(FieldValidationError::toString).collect(Collectors.joining("; "));

        return Problem.create()
                .withType(getTypeUri("invalid-input-fields"))
                .withTitle("Input data is invalid")
                .withDetail(String.format("Please fix the field values: %s", detailFields))
                .withProperties(map -> map.put("fields", fields));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    Problem handleProductNotFoundException(ProductNotFoundException e) {
        return Problem.create()
                .withType(getTypeUri("product-not-found"))
                .withTitle("Product id supplied was not found")
                .withDetail(e.getMessage());
    }

    private URI getTypeUri(String type) {
        URI typeUri = null;
        try {
            typeUri = new URI("https://tiagodeliberali.com.br/ecommerce/store/" + type);
        } catch (URISyntaxException uriSyntaxException) {
            return null;
        }
        return typeUri;
    }

    @Getter
    @AllArgsConstructor
    class FieldValidationError {
        private String field;
        private String message;

        @Override
        public String toString() {
            return field + ": " + message;
        }
    }
}
