package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.Task;

import java.util.Optional;


public class CreateTask {

    private final TaskRepository taskRepository;

    public CreateTask(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void by(final String name)  {
        Optional<Task> task = taskRepository.getByName(name);
        task.ifPresent(Task::alreadyExist);
        taskRepository.save(new Task(name));
    }
}
