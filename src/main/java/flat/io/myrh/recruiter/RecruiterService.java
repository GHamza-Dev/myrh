package flat.io.myrh.recruiter;

import flat.io.myrh.response.Response;
import flat.io.myrh.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public Recruiter createRecruiter(RecruiterRequest request){
        System.out.println(recruiterRepository);

        Recruiter recruiter = new Recruiter();
        recruiter.setEmail(request.getEmail());
        recruiter.setPhone(request.getPhone());
        recruiter.setPassword(request.getPassword());
        recruiter.setImage(request.getImage());
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setPrimaryRole(new Role(request.getRoleId()));

        recruiterRepository.save(recruiter);

        return recruiter.getId() != null ? recruiter : null;
    }
}
