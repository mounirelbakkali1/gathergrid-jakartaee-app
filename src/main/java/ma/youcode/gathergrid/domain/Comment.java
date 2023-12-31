package ma.youcode.gathergrid.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private int rating;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", user=" + user.username +
                '}';
    }
}
