package com.depop.cx.scratch.camunda.external

import org.camunda.bpm.engine.ExternalTaskService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeUnit

// With embedded external tasks, it's up to us to find and schedule our own work by querying the camunda engine.
// The configuration in the process definition simply defines a work topic:
//
//     <bpmn:serviceTask ... camunda:type="external" camunda:topic="example_external_task_topic">

// In this case, we are using spring boot scheduling (backed by a thread pool) to ask camunda for work,
// which we put on an internal queue.  A second scheduled job then does the work and
// marks the task as complete.
//
// For a production system, we would need to come up with a pattern(s) for embedded handling
// external tasks.  There are many options :)

@Component
class ExampleExternalTaskHandler(val externalTaskService: ExternalTaskService) {

    val workerId: String = "worker_1"
    val workerTopic: String = "example_external_task_topic"

    companion object {
        private val logger = LoggerFactory.getLogger(ExampleExternalTaskHandler::class.java)
    }

    val taskList : Queue<String> = ConcurrentLinkedQueue()

    @Scheduled(fixedDelay = 1000)
    fun pollForWork() {

        val tasks = externalTaskService.fetchAndLock(10, workerId)
            .topic(workerTopic, 60L * 1000L)
            .execute()

        tasks.forEach {
            logger.info("New task {}", it.id)
            taskList.add(it.id)
        }

    }

    @Scheduled(fixedRate = 1000)
    fun doWork() {

        // Poll our internal queue
        val task = taskList.poll()

        if(task != null) {

            logger.info("Starting task {}", task)

            //Do some expensive work
            TimeUnit.SECONDS.sleep(5)

            logger.info("Ending task {}", task)

            externalTaskService.complete(task, workerId)

        }
    }



}
