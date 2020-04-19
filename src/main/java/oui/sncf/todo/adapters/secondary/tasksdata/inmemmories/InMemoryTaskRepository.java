package oui.sncf.todo.adapters.secondary.tasksdata.inmemmories;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.port.TaskRepository;

import java.util.*;

public class InMemoryTaskRepository implements TaskRepository {

    private Set<Task> tasks = new LinkedHashSet<>();

    @Override
    public boolean save(Task task) {
        return tasks.add(task);
    }

    @Override
    public boolean remove(Task task) {
        return tasks.remove(task);
    }

    @Override
    public boolean update(Task task) {
        return tasks.add(task);
    }

    @Override
    public Optional<Task> getByName(String name) {
        return tasks.stream()
                .filter(task -> task.getName().equals(name))
                .findFirst();
    }
}
