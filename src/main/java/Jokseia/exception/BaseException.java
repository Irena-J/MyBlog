package Jokseia.exception;

public class BaseException extends RuntimeException {

    protected String code;

    //protected String message;

    public BaseException(String code,String message) {
        this(code,message,null);
    }

    public BaseException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
