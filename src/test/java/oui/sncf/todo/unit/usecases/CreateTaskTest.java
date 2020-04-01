package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.repositories.TasksRepository;
import oui.sncf.todo.core.usecases.CreateTask;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CreateTaskTest {

    Set<Task> tasks = new HashSet<>();
    private TasksRepository tasksRepository = new InMemoryTaskRepository(tasks);

    @Test
    void should_return_a_new_task_called_ouigo(){
        Task expected = new Task("clean archi", "");
        assertNameAndDescriptionTask("clean archi", "", expected);
    }

    @Test
    void should_return_the_new_task_that_contains_a_description(){
        Task expected = new Task("clean archi", "faire ma prez");
        assertNameAndDescriptionTask("clean archi", "faire ma prez", expected);
    }

    @Test
    void should_save_a_new_task_when_created(){
        CreateTask createTask = new CreateTask(tasksRepository);
        Task task = createTask.byName("task", "save");

        assertTrue(tasks.contains(task));
    }


    private void assertNameAndDescriptionTask(String name, String description, Task expectedTask){
        CreateTask createTask = new CreateTask(tasksRepository);
        Task ouigo = createTask.byName(name, description);
        assertEquals(expectedTask, ouigo);
    }
}