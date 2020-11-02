package co.edu.udea.eplatform.component.user.io.web.v1;

import co.edu.udea.eplatform.component.user.io.web.v1.model.UserSaveRequest;
import co.edu.udea.eplatform.component.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCreateAnUserWithAllData_thenReturn201() throws Exception{

        UserSaveRequest userToCreate = UserSaveRequest.builder()
                .names("Johana").lastNames("Barrientos").username("johana22")
                .primaryEmailAddress("johana@eplatform.com")
                .password("123456abc")
                .primaryPhoneNumber("+573043567820").build();

        client.perform(post("/api/v1/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userToCreate)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenCreateAnUserLackingPrimaryPhoneNumber_thenReturns201() throws Exception{
        UserSaveRequest userToCreate = UserSaveRequest.builder()
                .names("Pablo").lastNames("Perez").username("pablo")
                .primaryEmailAddress("pablo@eplatform.com")
                .password("123456abc").build();

        client.perform(post("/api/v1/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userToCreate)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenCreateAnUserLackingNamesSize_thenReturn400() throws Exception{
        UserSaveRequest userToCreate = UserSaveRequest.builder()
                .names("Dilsa").lastNames("Barrientos").username("d")
                .primaryEmailAddress("dilsa@eplatform.com")
                .password("123456abc").build();

        client.perform(post("/api/v1/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userToCreate)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenFindAnUserByIdThatNotExist_thenReturns404() throws Exception{
        client.perform(get("/api/v1/users/{id}", 999999))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenFindAnUserThatExist_thenReturnsThatUser() throws Exception{
        client.perform(get("/api/v1/users/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void whenDeleteAnUserThatExist_thenReturns204() throws Exception{
        client.perform(delete("/api/v1/users/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDeleteAnUserThatNotExist_thenReturns404() throws Exception{
        client.perform(delete("/api/v1/users/{id}", 99999))
                .andExpect(status().isNotFound());
    }
}
