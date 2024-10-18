package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    private Task generateTestTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    @Test
    public void testCreate() throws Exception {
        var task = generateTestTask();

        var response = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        // Проверяем структуру JSON-ответа
        assertThatJson(response.getContentAsString()).and(
                json -> json.node("title").isEqualTo(task.getTitle()),
                json -> json.node("description").isEqualTo(task.getDescription()),
                json -> json.node("id").isNotNull(),
                json -> json.node("createdAt").isNotNull()
        );

        // Проверяем сохранение в БД
        var taskFromRepo = taskRepository.findByTitle(task.getTitle()).get();
        assertThat(taskFromRepo.getTitle()).isEqualTo(task.getTitle());
        assertThat(taskFromRepo.getDescription()).isEqualTo(task.getDescription());
    }

    @Test
    public void testShow() throws Exception {
        var task = generateTestTask();
        taskRepository.save(task);

        var response = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // Проверяем структуру JSON-ответа
        assertThatJson(response.getContentAsString()).and(
                json -> json.node("id").isEqualTo(task.getId()),
                json -> json.node("title").isEqualTo(task.getTitle()),
                json -> json.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testUpdate() throws Exception {
        var task = generateTestTask();
        taskRepository.save(task);

        var updatedData = new HashMap<String, String>();
        updatedData.put("title", "Updated Title");
        updatedData.put("description", "Updated Description");

        var response = mockMvc.perform(put("/tasks/" + task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(updatedData)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        // Проверяем структуру JSON-ответа
        assertThatJson(response.getContentAsString()).and(
                json -> json.node("id").isEqualTo(task.getId()),
                json -> json.node("title").isEqualTo("Updated Title"),
                json -> json.node("description").isEqualTo("Updated Description"),
                json -> json.node("updatedAt").isNotNull()
        );

        // Проверяем обновление в БД
        var updatedTask = taskRepository.findById(task.getId()).get();
        assertThat(updatedTask.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedTask.getDescription()).isEqualTo("Updated Description");
    }

    @Test
    public void testDelete() throws Exception {
        var task = generateTestTask();
        taskRepository.save(task);

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        // Проверяем удаление из БД
        assertThat(taskRepository.findById(task.getId())).isEmpty();

        // Проверяем, что повторный запрос возвращает 404
        mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isNotFound());
    }
    // END
}
