package ma.youcode.gathergrid.domain;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.gathergrid.utils.PasswordHashUtil;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "users")
@Data
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
}
