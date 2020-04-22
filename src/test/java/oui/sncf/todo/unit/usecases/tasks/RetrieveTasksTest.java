package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.usecases.RetrieveTasks;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveTasksTest {


    private static final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final RetrieveTasks retrieveTasks = new RetrieveTasks(taskRepository);

    @BeforeAll
    static void initAll(){
        taskRepository.save(new Task("task 1"));
        taskRepository.save(new Task("task 2"));
        taskRepository.save(new Task("task 3", TaskStatus.DONE));
        taskRepository.save(new Task("task 4"));
        taskRepository.save(new Task("task 5", TaskStatus.DONE));
    }


    @Test
    void should_return_tasks_without_filter_and_sort(){
        Set<Task> tasks = retrieveTasks.fetch(null);
        assertThat(tasks.toArray())
                .containsExactly(
                        new Task("task 1"),
                        new Task("task 2"),
                        new Task("task 3", TaskStatus.DONE),
                        new Task("task 4"),
                        new Task("task 5", TaskStatus.DONE)
                );
    }


    @Test
    void should_only_return_done_tasks(){
        Set<Task> doneStatusFilteredTasks = retrieveTasks.fetch(TaskStatus.DONE);
        assertThat(doneStatusFilteredTasks.toArray())
                .containsExactly(
                        new Task("task 3", TaskStatus.DONE),
                        new Task("task 5", TaskStatus.DONE)
                );
    }

    @Test
    void should_only_return_in_progress_tasks(){
        Set<Task> inProgressFilteredTasks = retrieveTasks.fetch(TaskStatus.IN_PROGRESS);
        assertThat(inProgressFilteredTasks.toArray())
                .containsExactly(
                        new Task("task 1"),
                        new Task("task 2"),
                        new Task("task 4")
                );
    }

}
