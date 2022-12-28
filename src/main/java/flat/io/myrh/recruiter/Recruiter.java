package flat.io.myrh.recruiter;

import flat.io.myrh.user.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Recruiter extends AppUser {
    @Column(length = 55)
    private String companyName;
}
