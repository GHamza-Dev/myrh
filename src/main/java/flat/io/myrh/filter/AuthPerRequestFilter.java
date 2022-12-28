package flat.io.myrh.filter;

import flat.io.myrh.jwt.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthPerRequestFilter extends OncePerRequestFilter {
    private final JWT jwt;
    @Lazy
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("AUTHORIZATION");
        final String email;
        final String role;
        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        jwtToken = authHeader.substring(7);
        email = jwt.extractUsername(jwtToken);

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = (User) userDetailsService.loadUserByUsername(email);

            final boolean isTokenValid = jwt.validateToken(jwtToken,user);

            if(isTokenValid){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
