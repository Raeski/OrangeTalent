package orange.talent.handler;

import feign.FeignException;
import orange.talent.exception.BadRequestException;
import orange.talent.exception.BadRequestExceptionDetails;
import orange.talent.exception.BadRequestExceptionMessage;
import orange.talent.exception.FeignExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice//Estou dizendo para todos os controller para utilizar o que eu passar nessa classe
public class RestExceptionHandler {
//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
//        return new ResponseEntity<>(
//                BadRequestExceptionDetails.builder()
////                        .timestamp(LocalDateTime.now())
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .title("Bad Request Exception")
//                        .details(bre.getMessage())
////                        .developerMessage(bre.getClass().getName())
//                        .build(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionMessage> handlerBadRequestExceptionMessage(BadRequestException bre) {
        return new ResponseEntity<>(
                BadRequestExceptionMessage.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(bre.getMessage())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<FeignExceptionMessage> handleFeignStatusException(FeignException e, HttpServletResponse response) {

        return new ResponseEntity<>(
                FeignExceptionMessage.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("CEP inválido, favor verificar se está correto")
                        .build(), HttpStatus.BAD_REQUEST);
//        re
//        response.setStatus(e.status());
//        return "CPF inválido";
    }

}
