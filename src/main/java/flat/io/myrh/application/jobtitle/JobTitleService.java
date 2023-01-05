package flat.io.myrh.application.jobtitle;

import flat.io.myrh.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTitleService {
    private final JobTitleRepository jobTitleRepository;

    public JobTitle getJobTitleById(Long id){
        return jobTitleRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No job title found with the id: "+id));
    }

    public List<JobTitle> getJobTitles(){
        return jobTitleRepository.findAll();
    }
}
