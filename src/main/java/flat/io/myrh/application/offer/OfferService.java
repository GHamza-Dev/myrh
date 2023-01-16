package flat.io.myrh.application.offer;

import flat.io.myrh.application.education.Education;
import flat.io.myrh.application.jobtitle.JobTitle;
import flat.io.myrh.application.recruiter.Recruiter;
import flat.io.myrh.application.user.AppUser;
import flat.io.myrh.application.user.UserRepository;
import flat.io.myrh.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public boolean acceptOffer(Long id, String review){
        Offer offer = getOfferById(id);

        if (!offer.getStatus().equals("PENDING")){
            return false;
        }

        offer.setStatus("ACCEPTED");
        offer.setReviewDescription(review);
        offerRepository.save(offer);

        return offer.getStatus().equals("ACCEPTED");
    }

    public boolean rejectOffer(Long id, String review){
        Offer offer = getOfferById(id);

        if (!offer.getStatus().equals("PENDING")){
            return false;
        }

        offer.setStatus("REJECTED");
        offer.setReviewDescription(review);
        offerRepository.save(offer);

        return offer.getStatus().equals("REJECTED");
    }

    public Page<Offer> getAllOffers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return offerRepository.findAll(pageRequest);
    }

    public Page<Offer> searchOffers(int page, int size, String title, Long jobTitleId, String city){
        PageRequest pageRequest = PageRequest.of(page, size);
        title = !title.equals("*") ? title : null;
        jobTitleId = jobTitleId != 0L ? jobTitleId : null;
        city = !city.equals("*") ? city : null;
        return offerRepository.searchByFilter(title,jobTitleId,city,pageRequest);
    }

    public Offer getOfferById(Long id){
        return offerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No offer found with the id: "+id));
    }
}
