package oui.sncf.todo.core.port;

import oui.sncf.todo.adapters.mongodb.TaskDto;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.Set;


public interface TaskRepository {
    void save(Task task);
    boolean remove(Task task);
    Task getByName(String taskName);
    Set<TaskDto> fetch(TaskStatus status);
}
