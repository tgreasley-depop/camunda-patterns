package com.depop.cx.scratch.camunda.rest

import org.camunda.bpm.engine.RuntimeService
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@SpringBootApplication
@EnableScheduling
class CamundaEngineApplication(val runtimeService: RuntimeService) {

    companion object {
        private val logger = LoggerFactory.getLogger(CamundaEngineApplication::class.java)
    }

    @Scheduled(fixedRate = 5000)
    fun startProcess() {
        logger.info("Starting process...")
        runtimeService.startProcessInstanceByKey("example_external_task_process")
    }

}

fun main(args: Array<String>) {
    runApplication<CamundaEngineApplication>(*args)
}


