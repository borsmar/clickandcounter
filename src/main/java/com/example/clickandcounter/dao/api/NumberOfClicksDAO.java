package com.example.clickandcounter.dao.api;

import com.example.clickandcounter.model.NumberOfClicks;

public interface NumberOfClicksDAO {

    NumberOfClicks getNumberOfClicks();
    void increaseNumber(NumberOfClicks numberOfClicks);

}
