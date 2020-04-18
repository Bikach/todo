package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.tasksdata.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskDoesNotExistException;
import oui.sncf.todo.core.domain.tasks.port.repositories.TaskRepository;
import oui.sncf.todo.core.usecases.tasks.RemoveTask;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final RemoveTask removeTask = new RemoveTask(taskRepository);

    @Test
    void should_removed_task(){
        Task task = new Task("ouigo");
        taskRepository.save(task);
        assertTrue(removeTask.by(task));
    }
    @Test
    void should_not_be_able_to_remove_a_task_that_does_not_exist(){
        Task task = new Task("ouigo");
        assertThrows(TaskDoesNotExistException.class, () -> removeTask.by(task));
    }
}
