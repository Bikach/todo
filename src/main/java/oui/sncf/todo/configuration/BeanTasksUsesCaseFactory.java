package oui.sncf.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oui.sncf.todo.adapters.mongodb.MongoDbTaskRepository;
import oui.sncf.todo.usecases.*;

@Configuration
public class BeanTasksUsesCaseFactory {

    private final MongoDbTaskRepository taskRepository;

    public BeanTasksUsesCaseFactory(MongoDbTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Bean
    RetrieveTask retrieveByName(){
        return new RetrieveTask(taskRepository);
    }

    @Bean
    CreateTask createTaskByName(){
        return new CreateTask(taskRepository);
    }

    @Bean
    ChangeTaskStatus changeTaskStatue(){
        return new ChangeTaskStatus(taskRepository);
    }

    @Bean
    RemoveTask removeTask(){
        return new RemoveTask(taskRepository);
    }

    @Bean
    RetrieveTasks retrieveTasks(){
        return new RetrieveTasks(taskRepository);
    }
}
