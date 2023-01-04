package flat.io.myrh.application.recruiter;

import flat.io.myrh.application.role.RoleRepository;
import flat.io.myrh.application.role.RoleService;
import flat.io.myrh.application.user.UserService;
import flat.io.myrh.exception.ResourceNotFoundException;
import flat.io.myrh.files.FileStorageException;
import flat.io.myrh.files.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final RoleService roleService;

    private final FileStorageService fileStorageService;

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
        recruiter.setImage(null);
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setPrimaryRole(roleService.getRoleById(1L));

        recruiterRepository.save(recruiter);

        return recruiter.getId() != null ? recruiter : null;
    }

    public boolean updateProfileImage(MultipartFile multipartFile, String email){

        Recruiter recruiter = recruiterRepository.findRecruiterByEmail(email).orElseThrow(() -> new ResourceNotFoundException("No recruiter found with the email: "+email));

        String imageName = fileStorageService.storeFile(multipartFile);

        if(imageName != null){
            recruiter.setImage(imageName);
            recruiterRepository.save(recruiter);
            return true;
        }

        return false;
    }
}
