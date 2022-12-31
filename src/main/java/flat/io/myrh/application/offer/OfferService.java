package flat.io.myrh.application.offer;

import flat.io.myrh.application.education.Education;
import flat.io.myrh.application.jobtitle.JobTitle;
import flat.io.myrh.application.recruiter.Recruiter;
import flat.io.myrh.application.user.AppUser;
import flat.io.myrh.application.user.UserRepository;
import flat.io.myrh.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;

    public Offer createOffer(OfferRequest request, String email){

        AppUser user = userRepository.findAppUserByEmail(email);
        Long recruiterId = null;

        if(user != null ) recruiterId = user.getId();
        else throw new ResourceNotFoundException("Recruiter not found");


        Offer offer = new Offer();

        offer.setTitle(request.title);
        offer.setDescription(request.description);
        offer.setCity(request.city);
        offer.setSalary(request.salary);
        offer.setEducation(new Education(request.educationId));
        offer.setJobTitle(new JobTitle(request.jobTitleId));
        offer.setRecruiter(new Recruiter(recruiterId));

        offerRepository.save(offer);

        return offer;
    }

    public List<Offer> getALlOffers() {
        return offerRepository.findAll();
    }
}
