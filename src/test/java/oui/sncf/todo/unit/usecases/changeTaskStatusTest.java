package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.repositories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatus;
import oui.sncf.todo.core.domain.port.repositories.TaskRepository;
import oui.sncf.todo.core.usecases.changeTaskStatue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class changeTaskStatusTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final changeTaskStatue changeTaskStatue = new changeTaskStatue(taskRepository);


    @Test
    void should_return_a_task_that_has_changed_to_done(){
        Task taskInProgress = new Task("ouigo");
        taskRepository.save(taskInProgress);
        changeTaskStatue.of(taskInProgress, TaskStatus.DONE);
        Task taskIsDone = new Task("ouigo", TaskStatus.DONE);

        assertEquals(taskIsDone, taskIsDone);
    }
}
