package oui.sncf.todo.core.domain.port.repositories;

import oui.sncf.todo.core.domain.models.Task;

import java.util.Set;

public interface TasksRepository {
    boolean save(Task task);
    boolean remove(Task task);
    Set<Task> all();
}
