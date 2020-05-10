package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskAlwaysTodoException;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.unit.builders.TaskBuilder;
import oui.sncf.todo.usecases.RemoveTask;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class RemoveTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();
    private final RemoveTask removeTask = new RemoveTask(taskRepository);

    @Test
    void should_removed_task(){
        Task task = new Task("ouigo", TaskStatus.DONE);
        taskRepository.save(task);

        removeTask.by(task);

        Set<Task> tasks = taskRepository.fetch(null);
        assertThat(tasks)
                .doesNotContain(new TaskBuilder()
                        .name("ouigo")
                        .status(TaskStatus.DONE)
                        .build());
    }

    @Test
    void should_not_remove_a_task_that_have_todo_status(){
        Task task = new Task("ouigo", TaskStatus.TODO);
        taskRepository.save(task);

        assertThatThrownBy(() -> removeTask.by(task))
                .isInstanceOf(TaskAlwaysTodoException.class)
                .hasMessage("The task is already todo");
    }
}
