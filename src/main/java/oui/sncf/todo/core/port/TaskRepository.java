package oui.sncf.todo.core.port;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.Optional;
import java.util.Set;


public interface TaskRepository {
    void save(Task task);
    void remove(Task task);
    Optional<TaskDto> getByName(String name);
    Set<TaskDto> fetch(Optional<TaskStatus> status);
}
