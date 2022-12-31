package flat.io.myrh.application.role;

import flat.io.myrh.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleById(Long id){
        return roleRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }
}
