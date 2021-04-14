package com.depop.cx.scratch.camunda.rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CamundaWorkerApplication

fun main(args: Array<String>) {
    runApplication<CamundaWorkerApplication>(*args)
}


