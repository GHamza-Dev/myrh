package flat.io.myrh.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import flat.io.myrh.role.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 55, unique = true)
    private String email;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 55)
    private String password;

    @Column(length = 55)
    private String phone;

    @Column(length = 155)
    private String image;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Collection<Role> roles;

    public void setPrimaryRole(Role role){
        this.roles = new ArrayList<>();
        this.roles.add(role);
    }
}
