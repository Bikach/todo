package oui.sncf.todo.adapters.inmemmories;

import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.core.port.TaskRepository;

import java.util.*;

public class InMemoryTaskRepository implements TaskRepository {

    private Set<Task> tasks = new LinkedHashSet<>();

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public boolean remove(Task task) {
        return tasks.remove(task);
    }

    @Override
    public Task getByName(String name) {
        return tasks.stream()
                .filter(task -> task.getName().equals(name))
                .findFirst()
                .orElseThrow(
                        () -> new TaskDoesNotExistException("The task "+ name + " doesn't exist")
                );
    }

    @Override
    public Set<Task> fetch() {
        return tasks;
    }
}
