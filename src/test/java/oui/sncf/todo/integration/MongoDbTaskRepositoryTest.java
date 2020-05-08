package oui.sncf.todo.integration;

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
import oui.sncf.todo.core.task.TaskStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
        Optional<TaskDto> taskDto = taskRepository.getByName("task 2");
        assertThat(taskDto)
                .isEqualTo(Optional.of(new TaskDto("manger", "task 2", TaskStatus.IN_PROGRESS)));
    }

    @Test
    void should_return_a_task_using_his_prefix(){
        Optional<TaskDto> taskDto = taskRepository.getByPrefix("manger");
        assertThat(taskDto)
                .isEqualTo(Optional.of(new TaskDto("manger", "task 2", TaskStatus.IN_PROGRESS)));
    }

    @Test
    void should_return_tasks_without_filter(){
        Set<TaskDto> tasksFromDB = taskRepository.fetch(Optional.empty());

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
        Set<TaskDto> tasksFromDB = taskRepository.fetch(Optional.of(TaskStatus.DONE));

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new TaskDto("","task 3", TaskStatus.DONE),
                        new TaskDto("cinema", "task 5", TaskStatus.DONE)
                );
    }

    @Test
    void should_only_return_in_progress_tasks(){
        Set<TaskDto> tasksFromDB = taskRepository.fetch(Optional.of(TaskStatus.IN_PROGRESS));

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new TaskDto("", "task 1", TaskStatus.IN_PROGRESS),
                        new TaskDto("manger", "task 2", TaskStatus.IN_PROGRESS),
                        new TaskDto("", "task 4", TaskStatus.IN_PROGRESS)
                );
    }

}
