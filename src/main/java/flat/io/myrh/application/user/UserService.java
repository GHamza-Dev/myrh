package flat.io.myrh.application.user;


import flat.io.myrh.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public AppUser getUserById(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user found with the id: "+id));
    }

}
