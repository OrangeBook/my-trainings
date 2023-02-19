package orange.book.practicespringtesting.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import orange.book.practicespringtesting.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PersonController.class)//parameter need for injecting only specific controller (if empty - all controllers would be injected)
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean // need to add this bean in application context in order to have ability to use mockmvc
  private PersonService personService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void test() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk()).andReturn();
  }
}
