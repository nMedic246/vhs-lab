package hr.truenorth.project.VHSRentalShop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.truenorth.project.VHSRentalShop.model.VHS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.sql.Time;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VHSControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void nonVerifiedUserGetVHSList() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/vhs/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
    @Test
    @WithMockUser(username = "yozo", password = "qwertz", roles = "USER")
    public void verifiedUserGetVHSList() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/vhs/list")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "nika123", password = "qwertz", roles = "ADMIN")
    public void getVHSById() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/vhs/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
    }

    @Test
    @WithMockUser(username = "nika123", password = "qwertz", roles = "ADMIN")
    public void createVHS() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/vhs/addVHS")
                        .content(asJsonString(new VHS("Dazed and confused", new Time(1,45,0), 1991)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "nika123", password = "qwertz", roles = "ADMIN")
    public void deleteVHS() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/vhs/removeVHS/{id}",2))
                .andExpect(status().isOk());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
