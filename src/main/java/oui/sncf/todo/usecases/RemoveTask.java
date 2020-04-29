package oui.sncf.todo.usecases;

import oui.sncf.todo.adapters.mongodb.TaskDto;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskStatus;

public class RemoveTask {

    private final TaskRepository taskRepository;

    public RemoveTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String taskName) {
        TaskDto taskDto = taskRepository.getByName(taskName);
        if(taskDto.getStatus() == TaskStatus.DONE)
            taskRepository.remove(new Task(taskDto.getName(), taskDto.getStatus()));
    }
}
