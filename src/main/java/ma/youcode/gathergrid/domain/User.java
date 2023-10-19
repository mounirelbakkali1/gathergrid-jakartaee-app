package ma.youcode.gathergrid.domain;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    @Column(name = "name",nullable = false)
    String name ;
    @Column(name = "username",unique = true)
    String username;
    String email;
    @Column(name = "password",nullable = false)
            @Length(min = 8)
            @NotNull
    String password;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.EAGER)
    private List<Organization> organizations;


    public void addOrganization(Organization organization){
        if(organizations == null)
            organizations = new ArrayList<>();
        organizations.add(organization);
        organization.setUser(this);
    }
}
