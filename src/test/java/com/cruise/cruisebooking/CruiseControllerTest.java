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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CruiseControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CruiseService cruiseService;

    List<CruiseData> cruiseList;

    @BeforeEach
    void setUp() {
        cruiseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cruiseList.add(new CruiseData("CruiseName-"+i));
        }
    }

    @Test
    void getData() throws Exception {
        when(cruiseService.getCruiseData()).thenReturn(cruiseList);

        mockMvc.perform(get("/cruisedata"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void addData() throws Exception {
        when(cruiseService.addToCruiseData(any(CruiseData.class))).thenReturn(new CruiseData("NewlyAddedCruiseName"));

        mockMvc.perform(post("/cruisedata")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cruiseName\":\"cruise-0\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cruiseName").value("NewlyAddedCruiseName"));
    }

    @Test
    void getById() throws Exception {
        CruiseData actual = new CruiseData("GotCruiseById");
        actual.setCruiseId(17);
        when(cruiseService.getCruiseById(anyInt())).thenReturn(actual);
        mockMvc.perform(get("/cruisedata"))
                .andExpect(status().isOk());
    }

    @Test
    void getByName() throws Exception {
        CruiseData actual = new CruiseData("Princess Elsa");

        when(cruiseService.getCruiseByName(anyString())).thenReturn(actual);

        mockMvc.perform(get("/cruisedata/PrincessElsa"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("cruiseName").value("Princess Elsa"));
    }

    @Test
    void deleteById() throws Exception {
        cruiseList.remove(7);
        when(cruiseService.deleteCruiseById(anyInt())).thenReturn(cruiseList);

        mockMvc.perform(delete("/cruisedata/{id}", 7))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)));
    }

//    @Test
//    void updateById() throws Exception {
//        String actual = "Princess Mary";
//        when(cruiseService.updateCruiseById(any(CruiseData.class))).thenReturn(actual);
//
//        mockMvc.perform(put("/cruisedata/{id}", 6))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("cruiseName").value("Princess Mary"));
//
//    }
}
