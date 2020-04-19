package oui.sncf.todo.core.domain.tasks.port;

import oui.sncf.todo.core.domain.tasks.models.Task;

import java.util.Set;

public interface TaskLoader {
    Set<Task> fetch();
}
