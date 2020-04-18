package oui.sncf.todo.core.usecases.tasks;

import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.repositories.TaskRepository;

public class ChangeTaskStatue {

    private final TaskRepository taskRepository;

    public ChangeTaskStatue(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task of(Task task, TaskStatus newStatue){
        Task updatedTask = new Task(task.getName(), newStatue);
        taskRepository.update(updatedTask);
        return updatedTask;
    }
}
