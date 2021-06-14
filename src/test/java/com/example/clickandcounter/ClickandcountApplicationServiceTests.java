package com.example.clickandcounter;

import com.example.clickandcounter.dao.api.NumberOfClicksDAO;
import com.example.clickandcounter.dto.NumberOfClicksDto;
import com.example.clickandcounter.model.NumberOfClicks;
import com.example.clickandcounter.service.impl.NumberOfClicksServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClickandcountApplicationServiceTests {

    NumberOfClicks testNumberOfClicks = new NumberOfClicks();

    @BeforeEach
    public void prepareTestData() {
        testNumberOfClicks.setId(1L);
        testNumberOfClicks.setNumberOfClicks(1);
    }


    @Mock
    private NumberOfClicksDAO numberOfClicksDAO;

    private NumberOfClicksDto numberOfClicksDto;

    @InjectMocks
    private NumberOfClicksServiceImpl numberOfClicksService;

    @Test
    void atomicIncreaseNumberTest() {
        when(numberOfClicksDAO.getNumberOfClicks()).thenReturn(testNumberOfClicks);
        numberOfClicksService.atomicIncreaseNumber();
        verify(numberOfClicksDAO, times(1)).getNumberOfClicks();
        verify(numberOfClicksDAO, times(1)).increaseNumber(testNumberOfClicks);
        assertEquals(2, testNumberOfClicks.getNumberOfClicks());
    }

    @Test
    void getNumberOfClickTest() {
        when(numberOfClicksDAO.getNumberOfClicks()).thenReturn(testNumberOfClicks);
        numberOfClicksDto = numberOfClicksService.getNumberOfClicks();
        verify(numberOfClicksDAO, times(1)).getNumberOfClicks();
        assertEquals(1, numberOfClicksDto.getNumberOfClicks());
        assertEquals(1L, numberOfClicksDto.getId());

    }

}
