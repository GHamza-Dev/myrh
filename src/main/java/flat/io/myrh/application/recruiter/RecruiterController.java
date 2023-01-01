package flat.io.myrh.application.recruiter;

import flat.io.myrh.application.offer.Offer;
import flat.io.myrh.application.offer.OfferRequest;
import flat.io.myrh.response.DataResponse;
import flat.io.myrh.response.ErrorResponse;
import flat.io.myrh.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/recruiters")
@RequiredArgsConstructor
public class RecruiterController {

    private final RecruiterService recruiterService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody @Valid RecruiterRequest recruiterRequest){

        Recruiter recruiter = recruiterService.createRecruiter(recruiterRequest);

        if(recruiter != null){
            return ResponseEntity.status(201).body(new DataResponse("Recruiter registered successfully",201,recruiter));
        }

        return ResponseEntity.status(500).body(new Response("Ops something went wrong!",500));
    }

    @PostMapping("/profile/update-image")
    public ResponseEntity<Response> createOffer(@RequestParam(value = "image",defaultValue = "") MultipartFile image, Principal principal) {
        String email = principal.getName();

        if(recruiterService.updateProfileImage(image,email)){
            return ResponseEntity.ok(new Response("Image updated successfully",200));
        }

        return ResponseEntity.status(500).body(new ErrorResponse("Ops something went wrong while updating the image",500));
    }
}
