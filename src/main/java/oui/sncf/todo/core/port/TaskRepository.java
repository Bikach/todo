package oui.sncf.todo.core.port;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.Set;


public interface TaskRepository {
    void save(Task task);
    void remove(Task task);
    TaskDto getByName(String name);
    TaskDto getByPrefix(String prefix);
    Set<TaskDto> fetch(TaskStatus status);
}
