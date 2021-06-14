package com.example.clickandcounter.service.impl;

import com.example.clickandcounter.dao.api.NumberOfClicksDAO;
import com.example.clickandcounter.dto.NumberOfClicksDto;
import com.example.clickandcounter.model.NumberOfClicks;
import com.example.clickandcounter.service.api.NumberOfClicksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class NumberOfClicksServiceImpl implements NumberOfClicksService {

    @Autowired
    private NumberOfClicksDAO numberOfClicksDAO;

    @Override
    public NumberOfClicksDto getNumberOfClicks() {
        return convertToDto(numberOfClicksDAO.getNumberOfClicks());
    }

    @Override
    @Transactional
    public synchronized void atomicIncreaseNumber() {
        NumberOfClicks numberOfClicks = numberOfClicksDAO.getNumberOfClicks();
        int counter = numberOfClicks.getNumberOfClicks() + 1;
        numberOfClicks.setNumberOfClicks(counter);
        numberOfClicksDAO.increaseNumber(numberOfClicks);
    }

    public NumberOfClicksDto convertToDto(NumberOfClicks numberOfClicks){
        NumberOfClicksDto numberOfClicksDto = new NumberOfClicksDto();
        numberOfClicksDto.setId(numberOfClicks.getId());
        numberOfClicksDto.setNumberOfClicks(numberOfClicks.getNumberOfClicks());
        return numberOfClicksDto;
    }

}
