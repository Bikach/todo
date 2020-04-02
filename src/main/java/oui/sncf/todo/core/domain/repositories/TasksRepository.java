package oui.sncf.todo.core.domain.repositories;

import oui.sncf.todo.core.domain.models.Task;

import java.util.Set;

public interface TasksRepository {
    boolean save(Task task);
    Set<Task> all();
}
