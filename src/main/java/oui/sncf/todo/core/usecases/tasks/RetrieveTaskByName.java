package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.port.repositories.TaskRepository;
import oui.sncf.todo.core.domain.tasks.models.TaskDoesNotExistException;

public class RetrieveTaskByName {

    private final TaskRepository taskRepository;

    public RetrieveTaskByName(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task get(String nameTask){
        return taskRepository.getByName(nameTask)
                .orElseThrow(
                        () -> new TaskDoesNotExistException(nameTask + " doesn't exist")
                );
    }

}
