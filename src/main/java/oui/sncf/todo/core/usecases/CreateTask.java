package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;

public class CreateTask {



    public Task byName(String name) {
        return new Task(name);
    }
}
