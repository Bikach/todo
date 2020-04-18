package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.port.repositories.TaskRepository;
import oui.sncf.todo.core.usecases.RetrieveTaskByName;
import oui.sncf.todo.core.domain.models.TaskDoesNotExistException;

import static org.junit.jupiter.api.Assertions.*;

public class RetrieveTaskBNameTest {

    TaskRepository taskRepository = new InMemoryTaskRepository();
    RetrieveTaskByName retrieveTaskByName = new RetrieveTaskByName(taskRepository);

    @Test
    void should_return_a_task_by_name(){
        Task expectedTask = new Task("ouigo");
        taskRepository.save(expectedTask);
        Task actualTask = retrieveTaskByName.get(expectedTask.getName());
        assertEquals(expectedTask, actualTask);
    }

    @Test
    void should_not_return_the_task_when_it_does_not_exit(){
        Task expectedTask = new Task("ouigo");
        Task otherTask = new Task("nongo");
        taskRepository.save(otherTask);

        assertThrows(TaskDoesNotExistException.class, () -> {
            retrieveTaskByName.get(expectedTask.getName());
        });
    }
}
