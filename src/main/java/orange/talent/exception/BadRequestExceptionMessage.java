package orange.talent.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadRequestExceptionMessage {
    private int status;
    private String details;

}
