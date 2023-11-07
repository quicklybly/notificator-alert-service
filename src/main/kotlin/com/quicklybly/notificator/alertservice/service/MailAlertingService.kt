package com.quicklybly.notificator.alertservice.service

import com.quicklybly.notificator.alertservice.dto.Alert
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailAlertingService(val javaMailSender: JavaMailSender) {

    @Value("\${spring.mail.username}")
    private lateinit var mailFrom: String

    @KafkaListener(
        topics = ["\${spring.kafka.template.default-topic}"],
        // todo add group id
        groupId = "some-group-id",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun alertsListener(alert: Alert) {
        // todo add recipient validation
        val mail = SimpleMailMessage().apply {
            from = mailFrom
            setTo(alert.recipient)
            text = alert.message
        }
        javaMailSender.send(mail)
    }
}