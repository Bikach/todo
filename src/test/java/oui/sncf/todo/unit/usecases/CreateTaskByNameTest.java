package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.port.repositories.TaskRepository;
import oui.sncf.todo.core.domain.models.TaskAlReadyExistException;
import oui.sncf.todo.core.usecases.CreateTaskByName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateTaskByNameTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final CreateTaskByName createTaskByName = new CreateTaskByName(taskRepository);

    @Test
    void should_return_a_new_task_called_ouigo(){
        Task expectedTask = new Task("ouigo");
        Task actualTask = createTaskByName.of("ouigo");
        assertEquals(expectedTask, actualTask);
    }

    @Test
    void should_not_be_able_to_create_a_task_that_already_exists(){
        createTaskByName.of("ouigo");
        assertThrows(TaskAlReadyExistException.class,() -> createTaskByName.of("ouigo"));
    }
}