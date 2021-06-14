package com.example.clickandcounter.dao.api;

import com.example.clickandcounter.model.NumberOfClicks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberOfClicksRepo extends JpaRepository<NumberOfClicks, Long> {
}
