package oui.sncf.todo.core.domain.port.repositories;

import oui.sncf.todo.core.domain.models.Task;

import java.util.Optional;


public interface TaskRepository {
    boolean save(Task task);
    boolean remove(Task task);
    boolean update(Task task);
    Optional<Task> getByName(String taskName);
}
