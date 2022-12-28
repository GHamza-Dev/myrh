package flat.io.myrh.response;

import java.util.*;

public class ErrorResponse extends Response{
    private Object errors = null;

    public ErrorResponse(String message, Integer status, Object errors) {
        super(message, status);
        this.errors = errors;
    }

    public ErrorResponse(String message, Object errors) {
        super(message);
        this.errors = errors;
    }

    public ErrorResponse() {
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        if(errors instanceof List<?>){
            this.errors = new ArrayList();
            ((ArrayList) this.errors).add(errors);
        }else if (errors instanceof Map<?,?>){
            this.errors = errors;
        }
    }
}
