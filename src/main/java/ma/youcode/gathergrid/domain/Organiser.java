package ma.youcode.gathergrid.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "organisers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organiser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;

    private String name;
    private String description;

}
