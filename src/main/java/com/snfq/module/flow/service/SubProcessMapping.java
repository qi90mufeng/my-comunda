/**
 * sinafenqi.com
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.snfq.module.flow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateVariableMapping;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Service;

/**
 * @author fujin
 * @version $Id: SubProcessMapping.java, v 0.1 2017-10-10 14:04 boyuan Exp $$
 */
@Service("subProcessMapping")
public class SubProcessMapping implements DelegateVariableMapping {
    @Override
    public void mapInputVariables(DelegateExecution delegateExecution, VariableMap variableMap) {
        variableMap.putValue("subprocess","Process_1");
        variableMap.putValue("tenantId","sina-test");
    }

    @Override
    public void mapOutputVariables(DelegateExecution delegateExecution, VariableScope variableScope) {

    }
}
