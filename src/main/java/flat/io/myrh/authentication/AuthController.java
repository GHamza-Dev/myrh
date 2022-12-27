package flat.io.myrh.authentication;


import flat.io.myrh.exception.ResourceNotFoundException;
import flat.io.myrh.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody AuthRequest request){
        Response response = authService.authenticate(request.getEmail(),request.getPassword());
        return ResponseEntity.ok(response);
    }
}
