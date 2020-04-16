package oui.sncf.todo.core.domain.port.loaders;

import oui.sncf.todo.core.domain.models.Task;

import java.util.Set;

public interface TaskLoader {
    Set<Task> fetch();
}
