package com.depop.cx.scratch.camunda.external

import org.camunda.bpm.engine.RuntimeService
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableScheduling
class CamundaExternalApplication(val runtimeService: RuntimeService) {

    @Scheduled(fixedRate = 5000)
    fun startProcess() {
        runtimeService.startProcessInstanceByKey("example_external_task_process")
    }

}

fun main(args: Array<String>) {
    runApplication<CamundaExternalApplication>(*args)
}


