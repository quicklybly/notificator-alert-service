package com.quicklybly.notificator.alertservice.dto

class Alert() {
    lateinit var recipient: String
    lateinit var message: String

    constructor(recipient: String, message: String) : this() {
        this.recipient = recipient
        this.message = message
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Alert

        if (recipient != other.recipient) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = recipient.hashCode()
        result = 31 * result + message.hashCode()
        return result
    }

    override fun toString(): String {
        return "Alert(recipient='$recipient', message='$message')"
    }

}
