package flat.io.myrh.user;

import flat.io.myrh.role.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findAppUserByEmail(email);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role: appUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        User user = new User(appUser.getEmail(), appUser.getPassword(), authorities);

        return user;
    }
}
