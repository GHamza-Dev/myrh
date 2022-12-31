package flat.io.myrh.application.recruiter;

import flat.io.myrh.application.role.RoleRepository;
import flat.io.myrh.application.role.RoleService;
import flat.io.myrh.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final RoleService roleService;

    public Recruiter getRecruiterById(Long id){
        return recruiterRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No recruiter found with the id: "+id));
    }

    public Recruiter createRecruiter(RecruiterRequest request){

        Recruiter recruiter = new Recruiter();
        recruiter.setEmail(request.getEmail());
        recruiter.setPhone(request.getPhone());
        recruiter.setPassword(request.getPassword());
        recruiter.setImage(request.getImage());
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setPrimaryRole(roleService.getRoleById(request.getRoleId()));

        recruiterRepository.save(recruiter);

        return recruiter.getId() != null ? recruiter : null;
    }
}
