package oui.sncf.todo.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oui.sncf.todo.adapters.driven.MysqlTaskRepository;
import oui.sncf.todo.adapters.driven.dtos.TaskDto;
import oui.sncf.todo.adapters.driven.dtos.TaskDtoBuilder;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MysqlTaskRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MysqlTaskRepository taskRepository;

    private static final String SQL_INSERT_TASK = "INSERT INTO taskDto (name, status) VALUES (?, ?)";
    private static final String SQL_TRUNCATE_TASKS = "TRUNCATE TABLE taskDto";

    @BeforeEach
    void init() {
        jdbcTemplate.execute(SQL_TRUNCATE_TASKS);
        jdbcTemplate.update(SQL_INSERT_TASK, "task 1", TaskStatus.TODO.toString());
        jdbcTemplate.update(SQL_INSERT_TASK, "task 2", TaskStatus.TODO.toString());
        jdbcTemplate.update(SQL_INSERT_TASK, "task 3", TaskStatus.TODO.toString());
        jdbcTemplate.update(SQL_INSERT_TASK, "task 4", TaskStatus.DONE.toString());
        jdbcTemplate.update(SQL_INSERT_TASK, "task 5", TaskStatus.DONE.toString());
    }

    @Test
    void Should_save_a_new_task() {
        Task task = new Task("task 6");

        taskRepository.save(task);

        TaskDto savedTask = jdbcTemplate.queryForObject(
                "select * from taskDto where name = ?",
                new Object[]{"task 6"},
                (rs, rowNum) -> new TaskDto(
                        rs.getString("name"),
                        TaskStatus.valueOf(rs.getString("status"))
                )
        );

        assertThat(savedTask).isEqualTo(new TaskDtoBuilder()
                .name("task 6")
                .status(TaskStatus.TODO)
                .build());
    }

    @Test
    void should_remove_a_task(){
        Task task = new Task("task 4");
        taskRepository.remove(task);

        List<TaskDto> tasks = jdbcTemplate.query(
                "select * from taskDto",
                (rs, rowNum) -> new TaskDto(
                                rs.getString("name"),
                                TaskStatus.valueOf(rs.getString("status"))
                )
        );

        assertThat(tasks).doesNotContain(new TaskDtoBuilder()
                .name("task 4")
                .status(TaskStatus.TODO)
                .build());
    }

    @Test
    void should_return_a_task_using_his_name() {
        Optional<Task> optionalTask = taskRepository.getByName("task 2");
        assertThat(optionalTask).isEqualTo(Optional.of(
                new Task("task 2", TaskStatus.TODO)
                )
        );
    }

    @Test
    void should_return_a_optional_task_empty_when_task_name_does_not_exist(){
        Optional<Task> optionalTask = taskRepository.getByName("bad name");
        assertThat(optionalTask).isEmpty();
    }

    @Test
    void should_return_tasks_without_filter(){
        Set<Task> tasksFromDB = taskRepository.fetch(null);

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new Task( "task 1", TaskStatus.TODO),
                        new Task( "task 2", TaskStatus.TODO),
                        new Task("task 3", TaskStatus.TODO),
                        new Task( "task 4", TaskStatus.DONE),
                        new Task( "task 5", TaskStatus.DONE)
                );
    }

    @Test
    void should_only_return_done_tasks(){
        Set<Task> tasksFromDB = taskRepository.fetch(TaskStatus.DONE);

        assertThat(tasksFromDB.toArray())
                .containsExactly(
                        new Task("task 4", TaskStatus.DONE),
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
                        new Task( "task 3", TaskStatus.TODO)
                );
    }
}
