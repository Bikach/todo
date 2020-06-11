package oui.sncf.todo.domain.port;

import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.util.Set;

public interface DisplayTasks {
    Set<Task> retrieve(TaskStatus status);
}
