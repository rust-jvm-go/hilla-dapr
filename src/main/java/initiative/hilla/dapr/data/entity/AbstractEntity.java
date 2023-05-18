package initiative.hilla.dapr.data.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity {

    @Version
    protected int version;

    public int getVersion() {
        return version;
    }
}
