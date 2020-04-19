package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.port.TaskRepository;
import oui.sncf.todo.core.domain.tasks.models.TaskDoesNotExistException;

public class RetrieveByName {

    private final TaskRepository taskRepository;

    public RetrieveByName(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task get(String nameTask){
        return taskRepository.getByName(nameTask)
                .orElseThrow(
                        () -> new TaskDoesNotExistException(nameTask + " doesn't exist")
                );
    }

}
