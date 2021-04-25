package orange.talent.exception;

public class BadRequestExceptionMessage {
    private int status;
    private String details;


    BadRequestExceptionMessage(final int status, final String details) {
        this.status = status;
        this.details = details;
    }

    public static BadRequestExceptionMessage.BadRequestExceptionMessageBuilder builder() {
        return new BadRequestExceptionMessage.BadRequestExceptionMessageBuilder();
    }

    public int getStatus() {
        return this.status;
    }

    public String getDetails() {
        return this.details;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public void setDetails(final String details) {
        this.details = details;
    }


    public static class BadRequestExceptionMessageBuilder {
        private int status;
        private String details;

        BadRequestExceptionMessageBuilder() {
        }

        public BadRequestExceptionMessage.BadRequestExceptionMessageBuilder status(final int status) {
            this.status = status;
            return this;
        }

        public BadRequestExceptionMessage.BadRequestExceptionMessageBuilder details(final String details) {
            this.details = details;
            return this;
        }

        public BadRequestExceptionMessage build() {
            return new BadRequestExceptionMessage(this.status, this.details);
        }

        public String toString() {
            return "BadRequestExceptionMessage.BadRequestExceptionMessageBuilder(status=" + this.status + ", details=" + this.details + ")";
        }
    }

}
