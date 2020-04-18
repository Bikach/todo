package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.InMemoryTaskLoader;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatus;
import oui.sncf.todo.core.domain.port.loaders.TaskLoader;
import oui.sncf.todo.core.usecases.SortTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTasksTest {

    TaskLoader taskLoader = new InMemoryTaskLoader();
    SortTasks tasksByStatusDone = new SortTasks(taskLoader);

    @Test
    void shouldReturnTasksSortedByStatusDone(){
        Set<Task> tasks = tasksByStatusDone.ByStatusDone();
        List<Task> actualTasks = new ArrayList<>(tasks);

        assertAll(
                () -> assertEquals(TaskStatus.DONE, actualTasks.get(0).getStatue()),
                () -> assertEquals(TaskStatus.DONE, actualTasks.get(1).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, actualTasks.get(2).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, actualTasks.get(3).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, actualTasks.get(4).getStatue())
        );
    }

    @Test
    void shouldReturnTasksSortedByStatusInProgress(){
        Set<Task> tasks = tasksByStatusDone.byStatusInProgress();
        List<Task> actualTasks = new ArrayList<>(tasks);

        assertAll(
                () -> assertEquals(TaskStatus.IN_PROGRESS, actualTasks.get(0).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, actualTasks.get(1).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, actualTasks.get(2).getStatue()),
                () -> assertEquals(TaskStatus.DONE, actualTasks.get(3).getStatue()),
                () -> assertEquals(TaskStatus.DONE, actualTasks.get(4).getStatue())
        );
    }
}
