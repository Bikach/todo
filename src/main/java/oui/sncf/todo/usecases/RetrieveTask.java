package oui.sncf.todo.usecases;

import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskDoesNotExistException;

public class RetrieveTask {

    private final TaskRepository taskRepository;

    public RetrieveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task byName(final String nameTask){
        return taskRepository.getByName(nameTask)
                .orElseThrow(
                        () -> new TaskDoesNotExistException("The Task doesn't exist.")
                );
    }
}
