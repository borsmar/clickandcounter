package com.example.clickandcounter.service.api;

import com.example.clickandcounter.dto.NumberOfClicksDto;

public interface NumberOfClicksService {

    NumberOfClicksDto getNumberOfClicks();

    void atomicIncreaseNumber();
}
