package oui.sncf.todo.core.usecases;

import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatue;
import oui.sncf.todo.core.domain.port.repositories.TasksRepository;

public class changeTaskStatue {

    private final TasksRepository tasksRepository;

    public changeTaskStatue(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Task of(Task task, TaskStatue newStatue){
        Task updatedTask = new Task(task.getName(), newStatue);
        tasksRepository.save(updatedTask);
        return updatedTask;
    }
}
