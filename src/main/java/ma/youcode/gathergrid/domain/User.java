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

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @PrePersist
    public void prePersist(){
        PasswordHashUtil passwordHashUtil = new PasswordHashUtil();
        role = new Role("USER");
        password = passwordHashUtil.generate(password.toCharArray());
    }

    @PreUpdate
    public void preUpdate(){
        PasswordHashUtil passwordHashUtil = new PasswordHashUtil();
        role = new Role("USER");
        password = passwordHashUtil.generate(password.toCharArray());
    }

}
