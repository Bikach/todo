package oui.sncf.todo.unit.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.adapters.mongodb.MongoDbTaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskDoesNotExistException;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MongoDbTaskRepositoryTest {

    private static final String NO_PREFIX = "";

    @Autowired
    private  MongoTemplate mongoTemplate;

    @Autowired
    private  MongoDbTaskRepository taskRepository;

    @BeforeEach
    void inti(){
        mongoTemplate.dropCollection(Task.class);
        mongoTemplate.save(new TaskDto("", "task 1", TaskStatus.IN_PROGRESS));
        mongoTemplate.save(new TaskDto("manger", "task 2", TaskStatus.IN_PROGRESS));
        mongoTemplate.save(new TaskDto("","task 3", TaskStatus.DONE));
        mongoTemplate.save(new TaskDto("", "task 4", TaskStatus.IN_PROGRESS));
        mongoTemplate.save(new TaskDto("cinema", "task 5", TaskStatus.DONE));
    }

    @Test
    void Should_save_a_new_task(){
        Task task = new Task("task 6");
        taskRepository.save(task);

        List<TaskDto> tasksFromDB =  mongoTemplate.findAll(TaskDto.class);
        TaskDto taskDto = new TaskDto(task.getPrefix(), task.getName(), task.getStatus());

        assertThat(tasksFromDB).contains(taskDto);
    }

    @Test
    void should_remove_a_task(){
        Task task = new Task("task 4");
        taskRepository.remove(task);

        List<TaskDto> tasksFromDB =  mongoTemplate.findAll(TaskDto.class);
        TaskDto taskDto = new TaskDto(task.getPrefix(), task.getName(), task.getStatus());

        assertThat(tasksFromDB).doesNotContain(taskDto);
    }

    @Test
    void should_return_a_task_using_his_name(){
        TaskDto taskDto = taskRepository.getByName("task 2");
        assertThat(taskDto).isEqualTo(new TaskDto("manger", "task 2", TaskStatus.IN_PROGRESS));
    }

    @Test
    void should_not_return_a_task_when_the_name_does_not_exist(){
        assertThatThrownBy(() -> taskRepository.getByName("bad name"))
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The Task doesn't exist.");
    }

    @Test
    void should_return_a_task_using_his_prefix(){
        TaskDto taskDto = taskRepository.getByPrefix("manger");
        assertThat(taskDto).isEqualTo(new TaskDto("manger", "task 2", TaskStatus.IN_PROGRESS));
    }

    @Test
    void should_not_return_a_task_when_the_prefix_does_not_exist(){
        assertThatThrownBy(() -> taskRepository.getByPrefix("bad prefix"))
                .isInstanceOf(TaskDoesNotExistException.class)
                .hasMessage("The Task doesn't exist.");
    }

    @Test
    void should_return_tasks_without_filter(){
        Set<TaskDto> tasksFromDB = taskRepository.fetch(null);

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new TaskDto("", "task 1", TaskStatus.IN_PROGRESS),
                        new TaskDto("manger", "task 2", TaskStatus.IN_PROGRESS),
                        new TaskDto("","task 3", TaskStatus.DONE),
                        new TaskDto("", "task 4", TaskStatus.IN_PROGRESS),
                        new TaskDto("cinema", "task 5", TaskStatus.DONE)
                );
    }

    @Test
    void should_only_return_done_tasks(){
        Set<TaskDto> tasksFromDB = taskRepository.fetch(TaskStatus.DONE);

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new TaskDto("","task 3", TaskStatus.DONE),
                        new TaskDto("cinema", "task 5", TaskStatus.DONE)
                );
    }

    @Test
    void should_only_return_in_progress_tasks(){
        Set<TaskDto> tasksFromDB = taskRepository.fetch(TaskStatus.IN_PROGRESS);

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new TaskDto("", "task 1", TaskStatus.IN_PROGRESS),
                        new TaskDto("manger", "task 2", TaskStatus.IN_PROGRESS),
                        new TaskDto("", "task 4", TaskStatus.IN_PROGRESS)
                );
    }

}
