package flat.io.myrh.application.jobtitle;

import flat.io.myrh.response.DataResponse;
import flat.io.myrh.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job-titles")
public class JobTitleController {

    private final JobTitleService jobTitleService;

    public JobTitleController(JobTitleService jobTitleService) {
        this.jobTitleService = jobTitleService;
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllJobTitles(){
        return ResponseEntity.ok(new DataResponse("Job titles list", 200,jobTitleService.getJobTitles()));
    }
}