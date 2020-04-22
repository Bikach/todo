package oui.sncf.todo.usecases;

import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.port.TaskRepository;

public class RetrieveTask {

    private final TaskRepository taskRepository;

    public RetrieveTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task get(String nameTask){
        return taskRepository.getByName(nameTask);
    }

}
