package com.depop.cx.scratch.camunda.delegate

import org.camunda.bpm.engine.RuntimeService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@SpringBootApplication
@EnableScheduling
class CaumdaDelegateApplication(val runtimeService: RuntimeService) {

    @Scheduled(fixedRate = 10000)
    fun startProcess() {
        runtimeService.startProcessInstanceByKey("example_delegate_task_process")
    }

}

fun main(args: Array<String>) {
    runApplication<CaumdaDelegateApplication>(*args)
}
