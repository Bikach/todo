package oui.sncf.todo.unit.usecases;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.inmemmories.laoders.InMemoryTaskLoader;
import oui.sncf.todo.core.domain.models.Task;
import oui.sncf.todo.core.domain.models.TaskStatus;
import oui.sncf.todo.core.domain.port.loaders.TaskLoader;
import oui.sncf.todo.core.usecases.SortTasksByStatusDone;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTasksByStatusDoneTest {


    TaskLoader taskLoader = new InMemoryTaskLoader();
    SortTasksByStatusDone tasksByStatusDone = new SortTasksByStatusDone(taskLoader);

    @Test
    void shouldReturnTasksSortedByStatusDone(){
        Set<Task> tasks = tasksByStatusDone.sort();
        List<Task> taskList = new ArrayList<>(tasks);

        assertAll(
                () -> assertEquals(TaskStatus.DONE, taskList.get(0).getStatue()),
                () -> assertEquals(TaskStatus.DONE, taskList.get(1).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, taskList.get(2).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, taskList.get(3).getStatue()),
                () -> assertEquals(TaskStatus.IN_PROGRESS, taskList.get(4).getStatue())
        );
    }
}
