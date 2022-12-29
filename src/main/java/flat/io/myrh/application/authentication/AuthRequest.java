package flat.io.myrh.application.authentication;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
