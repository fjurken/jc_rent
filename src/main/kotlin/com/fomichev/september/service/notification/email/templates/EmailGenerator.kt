package com.fomichev.september.service.notification.email.templates

// abstract class EmailGenerator(
//    private val notificationKafkaTemplate: KafkaTemplate<String, Email>
// ) : AbstractService() {
//
//    abstract fun composeEmail(client: Client, payload: Map<String, String>?): Email
//
//    abstract fun getMyCode(): EmailTemplate
//
//    fun send(email: Email) {
//        notificationKafkaTemplate.send("test", email)
//        /*try {
//            log.info("Email $email successfully sent to kafka!")
//        } catch (ex: Throwable) {
//            log.error("Something went wrong during sendigm email $email", ex)
//        }*/
//    }
// }
//
// class JsonSerializerWithJTM<T> : org.springframework.kafka.support.serializer.JsonSerializer<T>() {
//    init {
//        objectMapper
//            .registerModule(JavaTimeModule())
//            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
//    }
// }
