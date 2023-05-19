package initiative.hilla.dapr.data.entity;

import dev.hilla.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2757138126491869483L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    @SequenceGenerator(name = "task_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Nonnull
    private String description;

    private boolean isDone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
            Objects.equals(version, task.version) &&
            Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, description);
    }
}
