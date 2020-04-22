package oui.sncf.todo.core.port;

import oui.sncf.todo.core.task.Task;

import java.util.Set;


public interface TaskRepository {
    void save(Task task);
    boolean remove(Task task);
    Task getByName(String taskName);
    Set<Task> fetch();
}
