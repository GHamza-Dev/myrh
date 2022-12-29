package flat.io.myrh.application.recruiter;

import flat.io.myrh.application.user.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Recruiter extends AppUser {
    @Column(length = 55)
    private String companyName;

    public Recruiter() {
    }
}
