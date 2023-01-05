package flat.io.myrh.application.education;

import flat.io.myrh.response.DataResponse;
import flat.io.myrh.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educations")
public class EducationController {
     private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }


    @GetMapping("/all")
    public ResponseEntity<Response> getAllEducations(){
        return ResponseEntity.ok(new DataResponse("Educations list",200, educationService.getEducations()));
    }
}
