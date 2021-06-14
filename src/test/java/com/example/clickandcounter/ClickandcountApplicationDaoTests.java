package com.example.clickandcounter;

import com.example.clickandcounter.dao.impl.NumberOfClicksImpl;
import com.example.clickandcounter.model.NumberOfClicks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClickandcountApplicationDaoTests {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @Mock
    private NumberOfClicks numberOfClicks;

    @InjectMocks
    private NumberOfClicksImpl numberOfClicksDAO;

    @Test
    void getNumberOfClicksTest() {
        when(entityManager.createQuery("FROM NumberOfClicks ")).thenReturn(query);
        numberOfClicksDAO.getNumberOfClicks();
        verify(entityManager, times(1)).createQuery("FROM NumberOfClicks ");
    }

    @Test
    void increaseNumberTest(){
        numberOfClicksDAO.increaseNumber(numberOfClicks);
        verify(entityManager,times(1)).persist(numberOfClicks);
    }



}
