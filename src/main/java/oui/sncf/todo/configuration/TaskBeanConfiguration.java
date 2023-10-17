package oui.sncf.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.usecases.*;

@Configuration
public class TaskBeanConfiguration {

    @Bean
    public RetrieveTask retrieveTaskByName(TaskRepository taskRepository){
        return new RetrieveTask(taskRepository);
    }

    @Bean
    public CreateTask createTaskByName(TaskRepository taskRepository){
        return new CreateTask(taskRepository);
    }

    @Bean
    public ChangeTaskName chaneTaskName(TaskRepository taskRepository){
        return new ChangeTaskName(taskRepository);
    }

    @Bean
    public ChangeTaskStatus changeTaskStatue(TaskRepository taskRepository){
        return new ChangeTaskStatus(taskRepository);
    }

    @Bean
    public RemoveTask removeTask(TaskRepository taskRepository){
        return new RemoveTask(taskRepository);
    }

    @Bean
    public RetrieveTasks retrieveTasks(TaskRepository taskRepository){
        return new RetrieveTasks(taskRepository);
    }
}
