package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.repositories.InMemoryTaskRepository;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatue;
import oui.sncf.todo.core.domain.port.repositories.TasksRepository;
import oui.sncf.todo.core.usecases.changeTaskStatue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class changeTaskStatueTest {

    private final TasksRepository tasksRepository = new InMemoryTaskRepository();;
    private final changeTaskStatue changeTaskStatue = new changeTaskStatue(tasksRepository);


    @Test
    void should_return_a_task_that_has_changed_to_done(){
        Task task = new Task("ouigo");
        task = changeTaskStatue.of(task, TaskStatue.DONE);
        assertEquals(TaskStatue.DONE,task.getStatue());
    }
}
