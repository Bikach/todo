package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.usecases.ChangeTaskName;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeTaskNameTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final ChangeTaskName changeTaskName = new ChangeTaskName(taskRepository);


    @Test
    void should_can_change_the_task_name(){
        Task task = new Task("ouigo");
        taskRepository.save(task);

        changeTaskName.of(task, "nongo");

        TaskDto taskDto = taskRepository.getByName("nongo");
        assertThat(taskDto.getName()).isEqualTo("nongo");
    }
}
