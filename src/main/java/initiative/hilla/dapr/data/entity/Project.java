package initiative.hilla.dapr.data.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
@Entity(name = Project.ENTITY_NAME)
@Table(name = Project.TABLE_NAME)
public class Project implements Serializable {

    @Serial
    private static final long serialVersionUID = -6698973189679063419L;

    public static final String ENTITY_NAME = "Project";
    public static final String TABLE_NAME = "project";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME+"_gen")
    @SequenceGenerator(name = TABLE_NAME+"_gen", sequenceName = TABLE_NAME+"_seq", allocationSize = 1)
    @Column(name = SuperclassEntity.COLUMN_ID_NAME, nullable = false)
    private Long id;
}
