package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.tasksdata.inmemmories.InMemoryTaskLoader;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.TaskLoader;
import oui.sncf.todo.core.usecases.tasks.FilterTasks;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterTasksTest {

    private final TaskLoader taskLoader = new InMemoryTaskLoader();;
    private final FilterTasks filterTasks = new FilterTasks(taskLoader);


    @Test
    void should_only_return_done_tasks(){
        Set<Task> doneStatusFilteredTasks = filterTasks.byStatus(TaskStatus.DONE);
        assertThat(doneStatusFilteredTasks.toArray())
                .containsExactly(
                        new Task("task 3", TaskStatus.DONE),
                        new Task("task 5", TaskStatus.DONE)
                );
    }

    @Test
    void should_only_return_in_progress_tasks(){
        Set<Task> inProgressFilteredTasks = filterTasks.byStatus(TaskStatus.IN_PROGRESS);
        assertThat(inProgressFilteredTasks.toArray())
                .containsExactly(
                        new Task("task 1"),
                        new Task("task 2"),
                        new Task("task 4")
                );
    }





}
