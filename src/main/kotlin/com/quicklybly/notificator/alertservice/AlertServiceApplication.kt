package com.quicklybly.notificator.alertservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AlertServiceApplication

fun main(args: Array<String>) {
    runApplication<AlertServiceApplication>(*args)
}
