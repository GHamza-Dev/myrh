package flat.io.myrh.recruiter;

import flat.io.myrh.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final RoleRepository roleRepository;

    public Recruiter createRecruiter(RecruiterRequest request){

        Recruiter recruiter = new Recruiter();
        recruiter.setEmail(request.getEmail());
        recruiter.setPhone(request.getPhone());
        recruiter.setPassword(request.getPassword());
        recruiter.setImage(request.getImage());
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setPrimaryRole(roleRepository.findById(request.getRoleId()).orElseGet(null));

        recruiterRepository.save(recruiter);

        return recruiter.getId() != null ? recruiter : null;
    }
}
