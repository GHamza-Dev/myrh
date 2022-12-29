package flat.io.myrh.application.authentication;

import flat.io.myrh.jwt.JWT;
import flat.io.myrh.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWT jwt;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JWT jwt) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwt = jwt;
    }

    public Response authenticate(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String generatedToken = jwt.generateToken(userDetails);

        return new AuthResponse("Authentication succeeded!", generatedToken);
    }
}
