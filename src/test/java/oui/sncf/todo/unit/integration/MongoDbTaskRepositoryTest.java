package oui.sncf.todo.unit.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.adapters.mongodb.MongoDbTaskRepository;
import oui.sncf.todo.core.task.Task;
import oui.sncf.todo.core.task.TaskStatus;

import java.util.List;

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

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("task 6"));

        List<TaskDto> taskFromDB =  mongoTemplate.find(query, TaskDto.class);
        TaskDto taskDto = new TaskDto(task.getPrefix(), task.getName(), task.getStatus());
        assertThat(taskFromDB).contains(taskDto);
    }

    @Test
    @Disabled
    void should_remove_a_task(){
        Task task = new Task("task 6");
        mongoTemplate.save(task);

        taskRepository.remove(task);

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("task 6"));

        List<Task> taskFromDB =  mongoTemplate.find(query, Task.class);
        assertThat(taskFromDB).doesNotContain(task);
    }

}
