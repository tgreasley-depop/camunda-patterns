package com.depop.cx.scratch.camunda.rest

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription
import org.camunda.bpm.client.task.ExternalTask
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

// With external tasks over rest we can optionally use the camunda external task client to process external tasks,
// or we can build our own worker client that polls the camunda rest endpoints for work.
//
// The configuration in the process definition simply defines a work topic:
//
//     <bpmn:serviceTask ... camunda:type="external" camunda:topic="example_external_task_topic">
//
// In this example we are using the spring boot external task client, which automatically subscribes to a camunda topic
// and does the work required.

@Service
@ExternalTaskSubscription(topicName = "example_external_task_topic", autoOpen = true)
class ExampleExternalTaskHandler : ExternalTaskHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(ExampleExternalTaskHandler::class.java)
    }

    override fun execute(task: ExternalTask?, externalTaskService: ExternalTaskService?) {

        logger.info("Starting task {}", task?.activityInstanceId)

        //Do some expensive work
        TimeUnit.SECONDS.sleep(5)

        logger.info("Ending task {}", task?.activityInstanceId)

    }

}
