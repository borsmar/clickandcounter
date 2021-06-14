package com.example.clickandcounter.dao.impl;

import com.example.clickandcounter.dao.api.NumberOfClicksDAO;
import com.example.clickandcounter.dao.api.NumberOfClicksRepo;
import com.example.clickandcounter.model.NumberOfClicks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class NumberOfClicksImpl implements NumberOfClicksDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    NumberOfClicksRepo numberOfClicksRepo;

    @Override
    public NumberOfClicks getNumberOfClicks() {

        return (NumberOfClicks) entityManager
                .createQuery("FROM NumberOfClicks ")
                .getSingleResult();
    }

    @Override
    public synchronized void increaseNumber(NumberOfClicks numberOfClicks) {
        entityManager.persist(numberOfClicks);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void runAfterStartup() {
        if(this.numberOfClicksRepo.findAll().size() == 0) {
            NumberOfClicks n = new NumberOfClicks();
            n.setNumberOfClicks(0);
            this.numberOfClicksRepo.save(n);
        }
    }

}
