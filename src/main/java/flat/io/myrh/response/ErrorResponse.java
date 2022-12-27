package flat.io.myrh.response;

import java.util.ArrayList;
import java.util.Collection;

public class ErrorResponse extends Response{
    private Collection errors = new ArrayList();

    public ErrorResponse(String message, Integer status, Collection errors) {
        super(message, status);
        this.errors = errors;
    }

    public ErrorResponse(String message, Collection errors) {
        super(message);
        this.errors = errors;
    }

    public ErrorResponse() {
    }

    public Collection getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        if(errors instanceof Collection<?>){
            this.errors = (Collection) errors;
        }else {
            this.errors.add(errors);
        }
    }
}
