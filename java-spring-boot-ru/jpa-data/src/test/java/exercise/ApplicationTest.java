package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ApplicationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PersonRepository personRepository;

    private Person testPerson;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();
        testPerson = new Person();
        personRepository.save(testPerson);
    }

    @Test
    public void testWelcomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/people"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/people/" + testPerson.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(testPerson)));
    }

    @Test
    public void testCreate() throws Exception {
        Map person = Map.of(
                "firstName", "test",
                "lastName", "user"
        );

        var request = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(person));

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(om.writeValueAsString(person)));

        assertThat(personRepository.findAll()).hasSize(2);
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/people/" + testPerson.getId()))
                .andExpect(status().isNoContent());

        assertThat(personRepository.findAll()).isEmpty();
    }
}
