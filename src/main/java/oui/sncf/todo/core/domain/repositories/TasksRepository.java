package oui.sncf.todo.core.domain.repositories;

import oui.sncf.todo.core.domain.models.Task;

public interface TasksRepository {
    void add(Task task);
}
