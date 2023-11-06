package com.quicklybly.notificator.alertservice.service

import com.quicklybly.notificator.alertservice.dto.Alert
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MailAlertingService {

    @KafkaListener(
        topics = ["\${spring.kafka.template.default-topic}"],
        // todo add group id
        groupId = "some-group-id",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun alertsListener(alert: Alert) {
        println("alert is received: $alert")
    }
}