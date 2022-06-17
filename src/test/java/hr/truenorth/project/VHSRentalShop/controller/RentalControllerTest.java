package hr.truenorth.project.VHSRentalShop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.truenorth.project.VHSRentalShop.dto.RentalForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Calendar;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RentalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "yozo", password = "qwertz", roles = "USER")
    public void userGetRentalList() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/rental/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
    @Test
    @WithMockUser(username = "nika123", password = "qwertz", roles = "ADMIN")
    public void adminGetRentalList() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/rental/list")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "nika123", password = "qwertz", roles = "ADMIN")
    public void createRental() throws Exception {
        Calendar c= Calendar.getInstance();
        c.set(2022,4,12,0,0);
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/rental/rentVHS")
                        .content(asJsonString(new RentalForm((long )2,"nika123",c.getTime())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    //Integirty confilict
    @Test
    @WithMockUser(username = "nika123", password = "qwertz", roles = "ADMIN")
    public void sameVHSsameDayRental() throws Exception {
        Calendar c= Calendar.getInstance();
        c.set(2021,3,17,0,0,0);
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/rental/rentVHS")
                        .content(asJsonString(new RentalForm((long )1,"nika123",c.getTime())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/rental/rentVHS")
                        .content(asJsonString(new RentalForm((long )1,"yozo",c.getTime())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isConflict());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
