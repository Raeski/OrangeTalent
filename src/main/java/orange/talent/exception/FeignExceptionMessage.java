package orange.talent.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeignExceptionMessage {

    private int status;
    private String error;
}
