package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.tasksdata.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.repositories.TaskRepository;
import oui.sncf.todo.core.usecases.tasks.ChangeTaskStatue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class changeTaskStatusTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final ChangeTaskStatue changeTaskStatue = new ChangeTaskStatue(taskRepository);


    @Test
    void should_return_a_task_that_has_changed_to_done(){
        Task task = new Task("ouigo");
        taskRepository.save(task);
        changeTaskStatue.of(task, TaskStatus.DONE);

        Task actualTask = taskRepository.getByName(task.getName()).get();
        Task expectedTask = new Task("ouigo", TaskStatus.DONE);

        assertEquals(expectedTask, actualTask);
    }

    @Test
    void should_return_a_task_that_has_changed_to_in_progress(){
        Task task = new Task("ouigo", TaskStatus.DONE);
        taskRepository.save(task);
        changeTaskStatue.of(task, TaskStatus.IN_PROGRESS);

        Task actualTask = taskRepository.getByName(task.getName()).get();
        Task expectedTask = new Task("ouigo", TaskStatus.IN_PROGRESS);

        assertEquals(expectedTask, actualTask);
    }
}
