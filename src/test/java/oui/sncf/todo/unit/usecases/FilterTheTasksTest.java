package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.laoders.InMemoryTaskLoader;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.port.loaders.TaskLoader;
import oui.sncf.todo.core.usecases.FilterTheTasks;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTheTasksTest {

    private final TaskLoader taskLoader = new InMemoryTaskLoader();;
    private final FilterTheTasks filterTheTasks = new FilterTheTasks(taskLoader);

    @Test
    void should_only_return_done_tasks(){
        Set<Task> doneStatusFilteredTasks = filterTheTasks.byDoneStatus();
        assertEquals(2, doneStatusFilteredTasks.size());
    }

    @Test
    void should_only_return_the_tasks_in_progress(){
        Set<Task> inProgressFilteredTasks = filterTheTasks.byInProgressStatus();
        assertEquals(3, inProgressFilteredTasks.size());
    }

}
