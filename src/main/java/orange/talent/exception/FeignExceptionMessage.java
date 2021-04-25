package orange.talent.exception;


public class FeignExceptionMessage {

    private int status;
    private String error;


    FeignExceptionMessage(final int status, final String error) {
        this.status = status;
        this.error = error;
    }

    public static FeignExceptionMessage.FeignExceptionMessageBuilder builder() {
        return new FeignExceptionMessage.FeignExceptionMessageBuilder();
    }

    public int getStatus() {
        return this.status;
    }

    public String getError() {
        return this.error;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public void setError(final String error) {
        this.error = error;
    }


    public String toString() {
        int var10000 = this.getStatus();
        return "FeignExceptionMessage(status=" + var10000 + ", error=" + this.getError() + ")";
    }

    public static class FeignExceptionMessageBuilder {
        private int status;
        private String error;

        FeignExceptionMessageBuilder() {
        }

        public FeignExceptionMessage.FeignExceptionMessageBuilder status(final int status) {
            this.status = status;
            return this;
        }

        public FeignExceptionMessage.FeignExceptionMessageBuilder error(final String error) {
            this.error = error;
            return this;
        }

        public FeignExceptionMessage build() {
            return new FeignExceptionMessage(this.status, this.error);
        }

        public String toString() {
            return "FeignExceptionMessage.FeignExceptionMessageBuilder(status=" + this.status + ", error=" + this.error + ")";
        }
    }
}
