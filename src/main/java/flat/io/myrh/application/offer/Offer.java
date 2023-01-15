package flat.io.myrh.application.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import flat.io.myrh.application.education.Education;
import flat.io.myrh.application.jobtitle.JobTitle;
import flat.io.myrh.application.recruiter.Recruiter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 65535,columnDefinition="Text") // to generate data type text
    private String description;

    @Column(length = 55)
    private String city;
    private Double salary;

    @Column(length = 33)
    private String status = "PENDING";

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(length = 65535,columnDefinition="Text") // to generate data type text
    private String reviewDescription;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    @JsonManagedReference
    private Recruiter recruiter;

    @ManyToOne
    @JoinColumn(name = "education_id")
    @JsonManagedReference
    private Education education;

    @ManyToOne
    @JoinColumn(name = "job_title_id")
    @JsonManagedReference
    private JobTitle jobTitle;

}
