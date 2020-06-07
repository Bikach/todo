package oui.sncf.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import oui.sncf.todo.adapters.driven.mongodb.MongoDbTaskRepository;
import oui.sncf.todo.adapters.driving.console.TaskConsoleCommands;
import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.usecases.RetrieveTasks;


@SpringBootApplication
public class TodoApplication {

	@Autowired
	private static RetrieveTasks retrieveTasks;

	public TodoApplication(RetrieveTasks retrieveTasks) {
		TodoApplication.retrieveTasks = retrieveTasks;
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);

		TaskConsoleCommands consoleCommands = new TaskConsoleCommands(retrieveTasks);

		consoleCommands.display();
	}
}
