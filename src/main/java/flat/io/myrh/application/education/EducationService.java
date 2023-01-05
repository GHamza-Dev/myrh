package flat.io.myrh.application.education;

import flat.io.myrh.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;

    public Education getEducationById(Long id){
        return educationRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No education found with the id: "+id));
    }

    public List<Education> getEducations(){
        return educationRepository.findAll();
    }
}
