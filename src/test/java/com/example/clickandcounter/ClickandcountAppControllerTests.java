package com.example.clickandcounter;

import com.example.clickandcounter.controller.AppController;
import com.example.clickandcounter.dto.NumberOfClicksDto;
import com.example.clickandcounter.service.api.NumberOfClicksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AppController.class)
public class ClickandcountAppControllerTests {
    @MockBean
    NumberOfClicksService service;

    @Autowired
    private MockMvc mockMvc;


    @InjectMocks
    AppController appController;

    NumberOfClicksDto testNumberOfClicks = new NumberOfClicksDto();

    @BeforeEach
    public void prepareTestData() {
        testNumberOfClicks.setId(1L);
        testNumberOfClicks.setNumberOfClicks(1);
    }

    @Test
    void increaseNumberTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/index")
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
        verify(service, times(1)).atomicIncreaseNumber();
    }

    @Test
    void indexTest() throws Exception {
        when(service.getNumberOfClicks()).thenReturn(testNumberOfClicks);
        mockMvc.perform(MockMvcRequestBuilders.get("/index")
                .accept(MediaType.ALL))
                .andExpect(status().isOk());
        verify(service, times(1)).getNumberOfClicks();
    }
}
