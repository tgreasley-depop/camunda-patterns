package com.depop.cx.scratch.camunda.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

// This component is made available to the camunda engine by the spring boot integration.
// It is called by the process by adding delegate configuration to the process definition:
//
// <bpmn:serviceTask ... camunda:delegateExpression="${helloWorldTask}">

// If not using Spring Boot, then you have to wire things up manually.
//
// The task will be called by camunda synchronously, and the engine thread will block until the task returns.
//
@Component
class ExampleDelegateTask : JavaDelegate {

    companion object {
        private val logger = LoggerFactory.getLogger(ExampleDelegateTask::class.java)
    }

    override fun execute(execution: DelegateExecution?) {
        logger.info("Executing delegate task {}", execution?.activityInstanceId)
    }

}
