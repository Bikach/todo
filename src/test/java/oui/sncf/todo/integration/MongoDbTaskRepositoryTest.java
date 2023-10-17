package oui.sncf.todo.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oui.sncf.todo.core.domain.task.Task;
import oui.sncf.todo.core.domain.task.TaskStatus;
import oui.sncf.todo.infrastructure.mongoDB.document.TaskDocument;
import oui.sncf.todo.infrastructure.mongoDB.document.TaskDocumentBuilder;
import oui.sncf.todo.infrastructure.mongoDB.mongodb.MongoDbTaskRepository;

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
    private MongoDbTaskRepository taskRepository;

    @BeforeEach
    void inti(){
        mongoTemplate.dropCollection(Task.class);
        mongoTemplate.save(new TaskDocument("task 1", TaskStatus.TODO));
        mongoTemplate.save(new TaskDocument("task 2", TaskStatus.TODO));
        mongoTemplate.save(new TaskDocument("task 3", TaskStatus.DONE));
        mongoTemplate.save(new TaskDocument("task 4", TaskStatus.TODO));
        mongoTemplate.save(new TaskDocument("task 5", TaskStatus.DONE));
    }

    @Test
    void Should_save_a_new_task(){
        Task task = new Task("task 6");
        taskRepository.save(task);

        List<TaskDocument> tasksFromDB =  mongoTemplate.findAll(TaskDocument.class);
        assertThat(tasksFromDB).contains(new TaskDocumentBuilder()
                .name("task 6")
                .status(TaskStatus.TODO)
                .build());
    }

    @Test
    void should_remove_a_task(){
        Task task = new Task("task 4");
        taskRepository.remove(task);

        List<TaskDocument> tasksFromDB =  mongoTemplate.findAll(TaskDocument.class);
        assertThat(tasksFromDB)
                .doesNotContain(
                        new TaskDocumentBuilder()
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
