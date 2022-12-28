package flat.io.myrh.user;

import flat.io.myrh.role.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "users")
@Data
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


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}
