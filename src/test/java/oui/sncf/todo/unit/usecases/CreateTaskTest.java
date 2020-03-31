package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.usecases.CreateTask;

import static org.junit.jupiter.api.Assertions.*;

class CreateTaskTest {

    @Test
    void should_return_a_new_taks_called_ouigo(){
        Task expected = new Task("ouigo");

        CreateTask createTask = new CreateTask();
        Task ouigo = createTask.byName("ouigo");

        assertEquals(expected, ouigo);
    }

}