package flat.io.myrh.recruiter;

import flat.io.myrh.response.DataResponse;
import flat.io.myrh.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiters")
@RequiredArgsConstructor
public class RecruiterController {

    private final RecruiterService recruiterService;
    private final RecruiterRepository recruiterRepository;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody @Valid RecruiterRequest recruiterRequest){

        Recruiter recruiter = recruiterService.createRecruiter(recruiterRequest);

        if(recruiter != null){
            return ResponseEntity.status(201).body(new DataResponse("Recruiter registered successfully",201,recruiter));
        }

        return ResponseEntity.status(500).body(new Response("Ops something went wrong!",500));
    }
}
