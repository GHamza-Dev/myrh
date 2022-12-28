package flat.io.myrh.recruiter;

import flat.io.myrh.user.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
public class Recruiter extends AppUser {
    @Column(length = 55)
    private String companyName;

    public Recruiter() {
    }
}
