package oui.sncf.todo.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import oui.sncf.todo.adapters.driven.MysqlTaskRepository;
import oui.sncf.todo.adapters.driven.dtos.TaskDto;
import oui.sncf.todo.adapters.driven.dtos.TaskDtoBuilder;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.sql.SQLException;
import java.util.List;

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

        List<TaskDto> tasks = jdbcTemplate.queryForList("select * from taskDto", TaskDto.class);

        assertThat(tasks).doesNotContain(new TaskDtoBuilder()
                .name("task 4")
                .status(TaskStatus.TODO)
                .build());
    }

}
