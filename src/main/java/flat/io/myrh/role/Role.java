package flat.io.myrh.role;

import flat.io.myrh.user.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(length = 55)
    private String name;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.MERGE)
    private Collection<AppUser> users;
}
