package oui.sncf.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oui.sncf.todo.adapters.driven.mongodb.MongoDbTaskRepository;
import oui.sncf.todo.usecases.*;

@Configuration
public class BeanTasksUsesCaseFactory {

    private final MongoDbTaskRepository taskRepository;

    public BeanTasksUsesCaseFactory(MongoDbTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Bean
    public RetrieveTask retrieveTaskByName(){
        return new RetrieveTask(taskRepository);
    }

    @Bean
    public CreateTask createTaskByName(){
        return new CreateTask(taskRepository);
    }

    @Bean
    public ChangeTaskName chaneTaskName(){
        return new ChangeTaskName(taskRepository);
    }

    @Bean
    public ChangeTaskStatus changeTaskStatue(){
        return new ChangeTaskStatus(taskRepository);
    }

    @Bean
    public RemoveTask removeTask(){
        return new RemoveTask(taskRepository);
    }

    @Bean
    public RetrieveTasks retrieveTasks(){
        return new RetrieveTasks(taskRepository);
    }
}
