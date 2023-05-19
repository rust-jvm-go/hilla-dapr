package initiative.hilla.dapr.data.entity;

import initiative.hilla.dapr.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.hilla.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application_user")
public class User extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7185965496313317908L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Nonnull
    private String username;

    @Nonnull
    private String name;

    @JsonIgnore
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Nonnull
    private Set<Role> roles;

    @Lob
    @Column(length = 1000000)
    private byte @Nonnull []profilePicture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(id, user.id) &&
            Objects.equals(version, user.version) &&
            Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name);
    }
}
