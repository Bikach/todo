package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.usecases.ChangeTaskStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class changeTaskStatusTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final ChangeTaskStatus changeTaskStatus = new ChangeTaskStatus(taskRepository);

    @Test
    void should_return_a_task_that_has_changed_to_done(){
        Task task = new Task("ouigo", TaskStatus.TODO);
        taskRepository.save(task);

        changeTaskStatus.of("ouigo", TaskStatus.DONE);


        Task actualTask = taskRepository.getByName("ouigo").get();
        assertThat(actualTask.getStatus()).isEqualTo(TaskStatus.DONE);
    }

    @Test
    void should_return_a_task_that_has_changed_to_todo(){
        Task task = new Task("ouigo", TaskStatus.DONE);
        taskRepository.save(task);

        changeTaskStatus.of("ouigo", TaskStatus.TODO);

        Task actualTask = taskRepository.getByName(task.getName()).get();
        assertThat(actualTask.getStatus()).isEqualTo(TaskStatus.TODO);
    }
}
