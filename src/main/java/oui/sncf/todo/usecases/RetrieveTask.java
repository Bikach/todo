package oui.sncf.todo.usecases;

import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.port.TaskRepository;

public class RetrieveTask {

    private final TaskRepository taskRepository;

    public RetrieveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task retrieve(final String nameTask){
        return taskRepository.getByName(nameTask);
    }
}
