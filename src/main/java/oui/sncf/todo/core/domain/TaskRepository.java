package oui.sncf.todo.core.domain;

import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.TaskStatus;

import java.util.Optional;
import java.util.Set;


public interface TaskRepository {
    void save(Task task);
    void remove(Task task);
    Optional<Task> getByName(String name);
    Set<Task> fetch(TaskStatus status);
}
