package com.cruise.cruisebooking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CruiseControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CruiseService dataService;

    List<CruiseData> myDataList;

    @BeforeEach
    void setUp() {
        myDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            myDataList.add(new CruiseData("person-"+i));
        }
    }

    @Test
    void getData() throws Exception {
        when(dataService.getCruiseData()).thenReturn(myDataList);

        mockMvc.perform(get("/mydata"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void addData() throws Exception {
        when(dataService.addToCruiseData(any(CruiseData.class))).thenReturn(new CruiseData("NewlyAddedName"));

        mockMvc.perform(post("/mydata")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"person-0\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("NewlyAddedName"));
    }

    @Test
    void getById() throws Exception {
        CruiseData actual = new CruiseData("GotById");
        actual.setCruiseId(999);
        when(dataService.getCruiseById(anyInt())).thenReturn(actual);
        mockMvc.perform(get("/mydata"))
                .andExpect(status().isOk());
    }

    @Test
    void getByName() throws Exception {
        CruiseData actual = new CruiseData("Rob");

        when(dataService.getCruiseByName(anyString())).thenReturn(actual);

        mockMvc.perform(get("/mydata/Rob"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Rob"));
    }

}
