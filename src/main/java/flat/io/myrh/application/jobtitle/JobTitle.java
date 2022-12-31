package flat.io.myrh.application.jobtitle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import flat.io.myrh.application.offer.Offer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "job_title")
@Data
public class JobTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @OneToMany(mappedBy = "jobTitle")
    @JsonBackReference
    Collection<Offer> offers;

    public JobTitle(Long id) {
        this.id = id;
    }

    public JobTitle() {
    }
}
