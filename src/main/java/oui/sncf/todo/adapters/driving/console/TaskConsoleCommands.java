package oui.sncf.todo.adapters.driving.console;

import oui.sncf.todo.domain.port.DisplayTasks;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class TaskConsoleCommands {

    private final DisplayTasks displayTasks;

    public TaskConsoleCommands(DisplayTasks displayTasks) {
        this.displayTasks = displayTasks;
    }

    public void display(){
        System.out.println("bonjour !");

        Scanner scanner = new Scanner(System.in);
        String next;
        do{
            System.out.println("Ecrivez le status des taches que vous voulez afficher (DONE/TODO)");

            Optional<TaskStatus> statusOptional = Optional.of(TaskStatus.valueOf(scanner.next().toUpperCase()));

            TaskStatus status = statusOptional.orElseGet(() -> TaskStatus.valueOf(null));
            Set<Task> tasks = displayTasks.retrieve(status);

            System.out.println(tasks);

            System.out.print("Continuer à afficher les taches ? (oui/non) : ");
            next = scanner.next();

        }while (next.equals("oui"));

        System.out.println("good bye !!");
        System.exit(0);
    }
}
