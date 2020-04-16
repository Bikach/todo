package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.repositories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.usecases.exceptions.TaskDoesNotExistException;
import oui.sncf.todo.core.domain.port.repositories.TaskRepository;
import oui.sncf.todo.core.usecases.RemoveTask;

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
