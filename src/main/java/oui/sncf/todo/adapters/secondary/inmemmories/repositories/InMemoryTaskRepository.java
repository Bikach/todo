package oui.sncf.todo.adapters.secondary.inmemmories.repositories;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.port.repositories.TaskRepository;

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
}
