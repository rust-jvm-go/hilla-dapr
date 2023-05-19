package initiative.hilla.dapr.data.entity;

import dev.hilla.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
@Entity(name = Task.ENTITY_NAME)
@Table(name = Task.TABLE_NAME)
public class Task extends SuperclassEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2757138126491869483L;

    public static final String ENTITY_NAME = "Task";
    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME+"_seq")
    @SequenceGenerator(name = TABLE_NAME+"_gen", allocationSize = 1)
    @Column(name = SuperclassEntity.COLUMN_ID_NAME, nullable = false)
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
