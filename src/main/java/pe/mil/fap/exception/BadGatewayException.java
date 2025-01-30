package pe.mil.fap.exception;

public class BadGatewayException extends RuntimeException{ 
	private static final long serialVersionUID = 1L; 

    public BadGatewayException(String msg) {
        super(msg);
    }

}
