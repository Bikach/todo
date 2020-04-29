package oui.sncf.todo.usecases;

import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;


public class CreateTask {

    private final TaskRepository taskRepository;

    public CreateTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String nameTask)  {
        Task task = new Task(nameTask);
        taskRepository.save(task);
    }
}
