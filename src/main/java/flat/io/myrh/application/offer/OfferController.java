package flat.io.myrh.application.offer;

import flat.io.myrh.exception.CustomRunTimeException;
import flat.io.myrh.response.DataResponse;
import flat.io.myrh.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/create")
    public ResponseEntity<Response> createOffer(@RequestBody @Valid OfferRequest offerRequest, Principal principal){
        String email = principal.getName();
        Offer offer = offerService.createOffer(offerRequest,email);

        if (offer == null) {
            throw new CustomRunTimeException("Ops something went wrong while creating a new offer!");
        }

        return ResponseEntity.ok(new DataResponse("Offer created successfully", 200, offer));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getOffers(){
        return ResponseEntity.ok().body(new DataResponse("Offers list",200,offerService.getALlOffers()));
    }
}
