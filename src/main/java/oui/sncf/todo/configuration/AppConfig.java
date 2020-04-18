package oui.sncf.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oui.sncf.todo.adapters.secondary.tasksdata.DataBaseTaskRepository;
import oui.sncf.todo.adapters.secondary.tasksdata.DataBaseTasksLoader;
import oui.sncf.todo.core.usecases.tasks.*;

@Configuration
public class AppConfig {

    private final DataBaseTasksLoader dataBaseTasksLoader;
    private final DataBaseTaskRepository taskRepository;

    public AppConfig(DataBaseTasksLoader dataBaseTasksLoader, DataBaseTaskRepository taskRepository) {
        this.dataBaseTasksLoader = dataBaseTasksLoader;
        this.taskRepository = taskRepository;
    }

    @Bean
    RetrieveTaskByName retrieveTaskByName(){
        return new RetrieveTaskByName(taskRepository);
    }

    @Bean
    CreateTaskByName createTaskByName(){
        return new CreateTaskByName(taskRepository);
    }

    @Bean
    ChangeTaskStatue changeTaskStatue(){
        return new ChangeTaskStatue(taskRepository);
    }

    @Bean
    RemoveTask removeTask(){
        return new RemoveTask(taskRepository);
    }

    @Bean
    FilterTheTasks filterTheTasks(){
        return new FilterTheTasks(dataBaseTasksLoader);
    }

    @Bean
    SortTasks sortTasks(){
        return new SortTasks(dataBaseTasksLoader);
    }

}
