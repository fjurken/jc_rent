package com.fomichev.jc_rent.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Properties

@Configuration
class EmailSenderConfiguration {

    @Value("\${notifications.email.sender}")
    lateinit var sender: String

    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "smtp.gmail.com"
        mailSender.port = 587
        mailSender.username = getMailSenderProps(0)
        mailSender.password = getMailSenderProps(1)
        val props: Properties = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        /*props["mail.debug"] = "true"*/
        return mailSender
    }

    fun getMailSenderProps(propLine: Int): String {
        val path = Paths.get("/Users/yury/Documents/Fomichev/dev/secret/java_mail_sender.txt")
        return try {
            val file = Files.readAllLines(path)
            if (propLine < file.size) {
                file[propLine]
            } else ""
        } catch (ex: IOException) {
            ""
        }
    }
}