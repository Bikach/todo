package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskAlReadyExistException;
import oui.sncf.todo.core.domain.repositories.TasksRepository;

public class CreateTaskByName {

    private final TasksRepository tasksRepository;

    public CreateTaskByName(final TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Task of(final String name)  {
        Task task = new Task(name);
        boolean alreadyExist = !tasksRepository.save(task);
        if(alreadyExist)
            throw new TaskAlReadyExistException(task + " already exist");
        return task;
    }
}
