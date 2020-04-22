package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.usecases.RetrieveTask;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveTaskTest {

    TaskRepository taskRepository = new InMemoryTaskRepository();
    RetrieveTask retrieveTask = new RetrieveTask(taskRepository);

    @Test
    void should_return_a_task_by_name(){
        Task expectedTask = new Task("ouigo");
        taskRepository.save(expectedTask);

        Task actualTask = retrieveTask.get("ouigo");

        assertThat(actualTask).isEqualTo(expectedTask);
    }
}
