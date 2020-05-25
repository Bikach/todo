package oui.sncf.todo.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oui.sncf.todo.adapters.driven.dtos.TaskDto;
import oui.sncf.todo.adapters.driven.dtos.TaskDtoBuilder;
import oui.sncf.todo.adapters.driven.mongodb.MongoDbTaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MongoDbTaskRepositoryTest {

    @Autowired
    private  MongoTemplate mongoTemplate;

    @Autowired
    private  MongoDbTaskRepository taskRepository;

    @BeforeEach
    void inti(){
        mongoTemplate.dropCollection(Task.class);
        mongoTemplate.save(new TaskDto("task 1", TaskStatus.TODO));
        mongoTemplate.save(new TaskDto("task 2", TaskStatus.TODO));
        mongoTemplate.save(new TaskDto("task 3", TaskStatus.DONE));
        mongoTemplate.save(new TaskDto("task 4", TaskStatus.TODO));
        mongoTemplate.save(new TaskDto("task 5", TaskStatus.DONE));
    }

    @Test
    void Should_save_a_new_task(){
        Task task = new Task("task 6");
        taskRepository.save(task);

        List<TaskDto> tasksFromDB =  mongoTemplate.findAll(TaskDto.class);
        assertThat(tasksFromDB).contains(new TaskDtoBuilder()
                .name("task 6")
                .status(TaskStatus.TODO)
                .build());
    }

    @Test
    void should_remove_a_task(){
        Task task = new Task("task 4");
        taskRepository.remove(task);

        List<TaskDto> tasksFromDB =  mongoTemplate.findAll(TaskDto.class);
        assertThat(tasksFromDB)
                .doesNotContain(
                        new TaskDtoBuilder()
                                .name("task 4")
                                .status(TaskStatus.TODO)
                                .build()
                );
    }

    @Test
    void should_return_a_task_using_his_name(){
        Optional<Task> task = taskRepository.getByName("task 2");
        assertThat(task)
                .isEqualTo(Optional.of(new Task( "task 2", TaskStatus.TODO)));
    }

    @Test
    void should_return_tasks_without_filter(){
        Set<Task> tasksFromDB = taskRepository.fetch(null);

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new Task( "task 1", TaskStatus.TODO),
                        new Task( "task 2", TaskStatus.TODO),
                        new Task("task 3", TaskStatus.DONE),
                        new Task( "task 4", TaskStatus.TODO),
                        new Task( "task 5", TaskStatus.DONE)
                );
    }

    @Test
    void should_only_return_done_tasks(){
        Set<Task> tasksFromDB = taskRepository.fetch(TaskStatus.DONE);

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new Task("task 3", TaskStatus.DONE),
                        new Task( "task 5", TaskStatus.DONE)
                );
    }

    @Test
    void should_only_return_in_progress_tasks(){
        Set<Task> tasksFromDB = taskRepository.fetch(TaskStatus.TODO);

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new Task("task 1", TaskStatus.TODO),
                        new Task( "task 2", TaskStatus.TODO),
                        new Task( "task 4", TaskStatus.TODO)
                );
    }
}
