package oui.sncf.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oui.sncf.todo.adapters.secondary.tasksdata.MongoDbTaskRepository;
import oui.sncf.todo.adapters.secondary.tasksdata.MongoDbTasksLoader;
import oui.sncf.todo.core.usecases.tasks.*;

@Configuration
public class TasksConfig {

    private final MongoDbTasksLoader tasksLoader;
    private final MongoDbTaskRepository taskRepository;

    public TasksConfig(MongoDbTasksLoader tasksLoader, MongoDbTaskRepository taskRepository) {
        this.tasksLoader = tasksLoader;
        this.taskRepository = taskRepository;
    }

    @Bean
    RetrieveByName retrieveByName(){
        return new RetrieveByName(taskRepository);
    }

    @Bean
    CreateTaskByName createTaskByName(){
        return new CreateTaskByName(taskRepository);
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
        return new RetrieveTasks(tasksLoader);
    }
}
