package com.fomichev.jc_rent.service.notification.email.html

import com.fomichev.jc_rent.controller.dto.request.RentRequest
import com.fomichev.jc_rent.model.Car
import com.fomichev.jc_rent.service.notification.email.templates.EmailTemplate
import com.fomichev.jc_rent.service.utils.prettyDateTime
import org.springframework.stereotype.Component

@Component
class HtmlBuilder {

    fun build(emailTemplate: EmailTemplate, payload: Map<String, Any>): String {
        return when (emailTemplate) {
            EmailTemplate.RENT_REQUEST -> buildRentRequestHtml(payload)
            else -> ""
        }
    }

    fun buildRentRequestHtml(payload: Map<String, Any>): String {
        val car = payload["car"] as Car
        val request = payload["request"] as RentRequest
        val duration = payload["duration"] as Int
        val price = payload["price"] as Double

        return "" +
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title>Journey Car Rent</title>\n" +
            "</head>\n" +
            "<body align=center>\n" +
            "\n" +
            "<h2>You request for car rent has been registered!</h2>\n" +
            "<br><br>\n" +
            "Below you will find detailed information:\n" +
            "<br><br>\n" +
            "<table align=center width=270>\n" +
            "<tr><td align=left width=120>Brand</td><td align=left>${car.brand}</td></tr>\n" +
            "<tr><td align=left>Model</td><td align=left>${car.model}</td></tr>\n" +
            "<tr><td align=left>Color</td><td align=left>${car.color}</td></tr>\n" +
            "<tr><td align=left>Engine</td><td align=left>${car.engineType}, ${car.engineCapacity}L, ${car.enginePower}</td></tr>\n" +
            "<tr><td align=left>Transmission</td><td align=left>${car.transmission}</td></tr>\n" +
            "<tr><td align=left>Licence plate</td><td align=left>${car.licencePlate}</td></tr>\n" +
            "</table>\n" +
            "\n" +
            "<table align=center width=270>\n" +
            "<tr><td align=left width=120>Date from:</td><td  align=left>${
            request.startDate.prettyDateTime()
            }</td></tr>\n" +
            "<tr><td align=left>Date to:</td><td  align=left>${request.endDate.prettyDateTime()}</td></tr>\n" +
            "<tr><td align=left>Total:</td><td align=left>$duration days, $price\$</td></tr>\n" +
            "</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>"
    }
}
