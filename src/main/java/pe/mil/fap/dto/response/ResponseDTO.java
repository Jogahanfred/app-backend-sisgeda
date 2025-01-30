package pe.mil.fap.dto.response;

import pe.mil.fap.utils.constants.MessageConstants;
import pe.mil.fap.utils.enums.MessageTypeEnum;
import pe.mil.fap.utils.enums.StateEnum;

public class ResponseDTO {
    private String type;
    private String message;
    private Object data;
    private Boolean success;
    private String error;
    private String exception;
 
    ResponseDTO(Builder builder) {
        this.type = builder.type;
        this.message = builder.message;
        this.data = builder.data;
        this.success = builder.success;
        this.error = builder.error;
        this.exception = builder.exception;
    }

    // Getters y setters
    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public String getException() {
        return exception;
    }

    // Pattern Builder 
    public static class Builder {
        private String type;
        private String message;
        private Object data;
        private Boolean success;
        private String error;
        private String exception;
 
        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public Builder setSuccess(Boolean success) {
            this.success = success;
            return this;
        }

        public Builder setError(String error) {
            this.error = error;
            return this;
        }

        public Builder setException(String exception) {
            this.exception = exception;
            return this;
        }
 
        public ResponseDTO build() {
            return new ResponseDTO(this);
        }
    }
 
    public static ResponseDTO createSuccess(String message, Object data) {
        return new Builder()
                .setType(MessageTypeEnum.SUCCESS.getDescription())
                .setMessage(message)
                .setData(data)
                .setSuccess(StateEnum.ACTIVE.getCondition())
                .build();
    }

    public static ResponseDTO createError(MessageTypeEnum type, Exception exception, Integer codeError) {
        return new Builder()
                .setType(type.getDescription())
                .setMessage(exception.getMessage())
                .setSuccess(StateEnum.INACTIVE.getCondition())
                .setError("Error: " + codeError)
                .setException(exception.getClass().getSimpleName())
                .build();
    }
    
    public static ResponseDTO createError(MessageTypeEnum type, Object exception, Integer codeError) {
        return new Builder()
                .setType(type.getDescription())
                .setMessage(MessageConstants.INFO_MESSAGE_DATA_ENTERED_INVALID)
                .setData(exception)
                .setSuccess(StateEnum.INACTIVE.getCondition())
                .setError("Error: " + codeError)
                .setException(exception.getClass().getSimpleName())
                .build();
    }
}
