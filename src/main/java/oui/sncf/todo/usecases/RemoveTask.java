package oui.sncf.todo.usecases;

import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskStatus;

public class RemoveTask {

    private final TaskRepository taskRepository;

    public RemoveTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String taskName) {
        Task task = taskRepository.getByName(taskName);
        if(task.getStatus() == TaskStatus.DONE)
            taskRepository.remove(task);
    }
}
