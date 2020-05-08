package oui.sncf.todo.unit.usecases.tasks;

import org.junit.jupiter.api.Test;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.adapters.inmemmories.InMemoryTaskRepository;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.TaskStatus;
import oui.sncf.todo.unit.builders.TaskDtoBuilder;
import oui.sncf.todo.usecases.CreateTask;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CreateTaskTest {

    private final TaskRepository taskRepository = new InMemoryTaskRepository();;
    private final CreateTask createTask = new CreateTask(taskRepository);

    private static final Optional<String> PREFIX_IS_NOT_PRESENT = Optional.empty();

    @Test
    void should_return_a_new_task_that_contain_a_name_without_prefix(){
        createTask.byName(PREFIX_IS_NOT_PRESENT, "ouigo");
        Optional<TaskDto> optionalTaskDto = taskRepository.getByName("ouigo");

        assertThat(optionalTaskDto)
                .isPresent()
                .isEqualTo(
                        Optional.of(new TaskDtoBuilder()
                                .name("ouigo")
                                .status(TaskStatus.IN_PROGRESS)
                                .build()
                        )
                );
    }

    @Test
    void should_return_a_new_task_that_contain_a_name_with_prefix(){
        createTask.byName(Optional.of("prefix"),"ouigo");
        Optional<TaskDto> optionalTaskDto  = taskRepository.getByName("prefix:ouigo");
        assertThat(optionalTaskDto)
                .isPresent()
                .isEqualTo(
                        Optional.of(new TaskDtoBuilder()
                                .prefix("prefix")
                                .name("prefix:ouigo")
                                .status(TaskStatus.IN_PROGRESS)
                                .build()
                        )
                );    }



}