package at.tsc.sj23_tsc_ost.persistence;

import at.tsc.sj23_tsc_ost.presentation.api.MemberController;
import at.tsc.sj23_tsc_ost.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class MemberControllerTest {

    @MockBean
    private MemberService service;

    private ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {

        this.mockMvc = MockMvcBuilders.standaloneSetup(new MemberController(service)).build();

        objectMapper =  new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    @Test
    void Test() throws Exception {

        mockMvc.perform(get(MemberController.BASEURL).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType("application/json"))
                .andReturn();
    }


}
