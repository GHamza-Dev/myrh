package flat.io.myrh.application.education;

import com.fasterxml.jackson.annotation.JsonBackReference;
import flat.io.myrh.application.offer.Offer;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @OneToMany(mappedBy = "education")
    @JsonBackReference
    Collection<Offer> offers;

    public Education(Long id) {
        this.id = id;
    }

    public Education() {
    }
}
