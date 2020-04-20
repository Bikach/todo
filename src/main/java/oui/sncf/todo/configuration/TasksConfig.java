package oui.sncf.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oui.sncf.todo.adapters.secondary.tasksdata.DataBaseTaskRepository;
import oui.sncf.todo.adapters.secondary.tasksdata.DataBaseTasksLoader;
import oui.sncf.todo.core.usecases.tasks.*;

@Configuration
public class TasksConfig {

    private final DataBaseTasksLoader dataBaseTasksLoader;
    private final DataBaseTaskRepository taskRepository;

    public TasksConfig(DataBaseTasksLoader dataBaseTasksLoader, DataBaseTaskRepository taskRepository) {
        this.dataBaseTasksLoader = dataBaseTasksLoader;
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
        return new RetrieveTasks(dataBaseTasksLoader);
    }
}
