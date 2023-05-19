package initiative.hilla.dapr.data.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public abstract class SuperclassEntity {

    public static final String COLUMN_ID_NAME = "id";

    @Version
    protected int version;

    public int getVersion() {
        return version;
    }
}
