package flat.io.myrh.application.authentication;


import flat.io.myrh.application.user.UserRepository;
import flat.io.myrh.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AuthRequest request){
        Response response = authService.authenticate(request.getEmail(),request.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/p")
    public ResponseEntity<Principal> p(Principal principal){
        return ResponseEntity.ok(principal);
    }
}
