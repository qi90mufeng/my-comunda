package com.snfq.module.flow.service;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LifecycleListener implements CaseExecutionListener {

    private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS-CMMN");

    public void notify(DelegateCaseExecution caseExecution) throws Exception {
        LOGGER.info("Plan Item '" + caseExecution.getActivityId() + "' labeled '"
                + caseExecution.getActivityName() + "' has performed transition: "
                + caseExecution.getEventName());
    }

}