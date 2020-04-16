package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatus;
import oui.sncf.todo.core.domain.port.repositories.TaskRepository;

public class changeTaskStatue {

    private final TaskRepository taskRepository;

    public changeTaskStatue(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task of(Task task, TaskStatus newStatue){
        Task updatedTask = new Task(task.getName(), newStatue);
        taskRepository.update(updatedTask);
        return updatedTask;
    }
}
