package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.secondary.tasksdata.inmemmories.InMemoryTaskLoader;
import oui.sncf.todo.core.domain.tasks.models.Task;
import oui.sncf.todo.core.domain.tasks.models.TaskStatus;
import oui.sncf.todo.core.domain.tasks.port.TaskLoader;
import oui.sncf.todo.core.usecases.tasks.RetrieveTasks;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveTasksTest {

    private final TaskLoader taskLoader = new InMemoryTaskLoader();;
    private final RetrieveTasks retrieveTasks = new RetrieveTasks(taskLoader);


    @Test
    void should_return_tasks_without_filter_and_sort(){
        Set<Task> actualTasks = retrieveTasks.fetch(null, null);
        assertThat(actualTasks.toArray())
                .containsExactlyInAnyOrder(
                        new Task("task 1"),
                        new Task("task 2"),
                        new Task("task 3", TaskStatus.DONE),
                        new Task("task 4"),
                        new Task("task 5", TaskStatus.DONE)
                );
    }


    @Nested
    class filter{

        @Test
        void should_only_return_done_tasks(){
            Set<Task> doneStatusFilteredTasks = retrieveTasks.fetch(TaskStatus.DONE, null);
            assertThat(doneStatusFilteredTasks.toArray())
                    .containsExactly(
                            new Task("task 5", TaskStatus.DONE),
                            new Task("task 3", TaskStatus.DONE)
                    );
        }

        @Test
        void should_only_return_in_progress_tasks(){
            Set<Task> inProgressFilteredTasks = retrieveTasks.fetch(TaskStatus.IN_PROGRESS, null);
            assertThat(inProgressFilteredTasks.toArray())
                    .containsExactly(
                            new Task("task 4"),
                            new Task("task 2"),
                            new Task("task 1")
                    );
        }

    }

    @Nested
    class Sort {

        @Test
        void shoul_return_tasks_sorted_by_done_status(){
            Set<Task> doneSortedTasks = retrieveTasks.fetch(null, TaskStatus.DONE);
            assertThat(doneSortedTasks.toArray())
                    .containsExactly(
                            new Task("task 3", TaskStatus.DONE),
                            new Task("task 5", TaskStatus.DONE),
                            new Task("task 1"),
                            new Task("task 2"),
                            new Task("task 4")
                    );
        }


    }


}
