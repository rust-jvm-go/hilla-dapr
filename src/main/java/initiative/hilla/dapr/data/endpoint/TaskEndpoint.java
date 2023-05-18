package initiative.hilla.dapr.data.endpoint;

import dev.hilla.Endpoint;
import initiative.hilla.dapr.data.entity.Task;
import initiative.hilla.dapr.data.repository.TaskRepository;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Endpoint
@RolesAllowed({"ADMIN", "USER"})
@RequiredArgsConstructor
public class TaskEndpoint {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task add(String description) {

        Task todo = Task.builder()
            .description(description)
            .build();

        return taskRepository.save(todo);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }
}
