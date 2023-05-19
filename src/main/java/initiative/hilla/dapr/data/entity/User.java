package initiative.hilla.dapr.data.entity;

import initiative.hilla.dapr.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.hilla.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
@Entity(name = User.ENTITY_NAME)
@Table(name = User.TABLE_NAME)
public class User extends SuperclassEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7185965496313317908L;

    public static final String ENTITY_NAME = "User";
    public static final String TABLE_NAME = "application_user";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME+"_seq")
    @SequenceGenerator(name = TABLE_NAME+"_gen", allocationSize = 1)
    @Column(name = SuperclassEntity.COLUMN_ID_NAME, nullable = false)
    private Long id;

    @Nonnull
    private String username;

    @Nonnull
    private String name;

    @JsonIgnore
    private String hashedPassword;

    @Nonnull
    @ElementCollection(fetch = FetchType.EAGER)
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
        return id() != null && Objects.equals(id, user.id) &&
            Objects.equals(version, user.version) &&
            Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name);
    }
}
