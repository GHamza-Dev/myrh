package flat.io.myrh.offer;

import flat.io.myrh.education.Education;
import flat.io.myrh.jobtitle.JobTitle;
import jakarta.persistence.*;

@Entity
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

    @Column(length = 33)
    private String status;

    @Column(length = 65535,columnDefinition="Text") // to generate data type text
    private String reviewDescription;

    @ManyToOne
    @JoinColumn(name = "education_id")
    private Education education;

    @ManyToOne
    @JoinColumn(name = "job_title_id")
    private JobTitle jobTitle;

}
