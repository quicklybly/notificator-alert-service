package com.quicklybly.notificator.alertservice.dto.deserialize

import com.fasterxml.jackson.databind.ObjectMapper
import com.quicklybly.notificator.alertservice.dto.Alert
import org.apache.kafka.common.serialization.Deserializer

class AlertDeserializer : Deserializer<Alert> {
    private val mapper = ObjectMapper()

    override fun deserialize(topic: String?, data: ByteArray?): Alert {
        // todo care about exception and poison pill
        return mapper.readValue(data, Alert::class.java)
    }
}