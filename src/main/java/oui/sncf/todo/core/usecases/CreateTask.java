package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.repositories.TasksRepository;

public class CreateTask {

    private final TasksRepository tasksRepository;

    public CreateTask(final TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Task byName(final String name, final String description) {
        Task task = new Task(name, description);
        tasksRepository.add(task);
        return task;
    }
}
