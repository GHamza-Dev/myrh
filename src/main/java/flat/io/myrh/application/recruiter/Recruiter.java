package flat.io.myrh.application.recruiter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import flat.io.myrh.application.offer.Offer;
import flat.io.myrh.application.user.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Recruiter extends AppUser {
    @Column(length = 55)
    private String companyName;

    @OneToMany(mappedBy = "recruiter")
    @JsonBackReference
    private Collection<Offer> offers;

    public Recruiter() {
    }

    public Recruiter(Long id) {
        super(id);
    }
}
