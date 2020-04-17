package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.laoders.InMemoryTaskLoader;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatus;
import oui.sncf.todo.core.domain.port.loaders.TaskLoader;
import oui.sncf.todo.core.usecases.SortTasksByStatusInProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTasksByStatusInProgressTest {

    TaskLoader taskLoader = new InMemoryTaskLoader();
    SortTasksByStatusInProgress tasksByStatusInProgress = new SortTasksByStatusInProgress(taskLoader);

    @Test
    void shouldReturnTasksSortedByStatusInProgress(){
        Set<Task> tasks = tasksByStatusInProgress.sort();
        List<Task> taskList = new ArrayList<>(tasks);

        assertAll(
                () -> assertEquals(TaskStatus.IN_PROGRESS, taskList.get(0).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, taskList.get(1).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, taskList.get(2).getStatue()),
                () -> assertEquals(TaskStatus.DONE, taskList.get(3).getStatue()),
                () -> assertEquals(TaskStatus.DONE, taskList.get(4).getStatue())
        );
    }
}
