package oui.sncf.todo.usecases;

import oui.sncf.todo.adapters.mongodb.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;

public class RetrieveTask {

    private final TaskRepository taskRepository;

    public RetrieveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto retrieve(final String nameTask){
        return taskRepository.getByName(nameTask);
    }
}
