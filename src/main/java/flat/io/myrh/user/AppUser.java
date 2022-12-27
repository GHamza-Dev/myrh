package flat.io.myrh.user;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 55)
    private String email;

    @Column(length = 55)
    private String password;

    @Column(length = 55)
    private String phone;

    @Column(length = 155)
    private String image;


    @ManyToMany
    private Collection<Role> roles;
}
