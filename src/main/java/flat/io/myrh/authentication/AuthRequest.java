package flat.io.myrh.authentication;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
