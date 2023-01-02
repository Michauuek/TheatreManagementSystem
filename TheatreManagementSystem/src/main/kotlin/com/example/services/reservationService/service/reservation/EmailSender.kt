package com.example.services.reservationService.service.reservation

import java.io.File
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class EmailSender(private val toMail: String) {
    private val senderEmail = System.getenv("EMAIL")
    private val password = System.getenv("PASSWORD")

    private val subject = "Bilet - Teatr Kraków"
    private val text = """Dzień Dobry <b>IMIE</b> <br> 
        Dziękujemy za dokonanie rezerwacji. <br><br>
        
        Twój bilet został zarezerwowany. Zapraszamy po odbiór biletu minimum 15 minut przed przedstawieniem.
        <br> Dziękujemy za skorzystanie z naszych usług :) <br><br>
        
        <b>Numer rezerwacji: 123456789</b> <br>
        <b>Kwota: 100 zł </b>""".trimMargin()

    fun sendEmail() {
        val props = Properties()
        putIfMissing(props, "mail.smtp.host", "smtp.gmail.com")
        putIfMissing(props, "mail.smtp.port", "587")
        putIfMissing(props, "mail.smtp.auth", "true")
        putIfMissing(props, "mail.smtp.starttls.enable", "true")

        val session = Session.getDefaultInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(senderEmail, password)
            }
        })

        session.debug = true



        try {
            val mimeMessage = MimeMessage(session)
            mimeMessage.setFrom(InternetAddress(senderEmail))
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail, false))
            mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(toMail, false))

            val mimeBodyPart = MimeBodyPart()
            mimeBodyPart.setContent(text, "text/html; charset=utf-8")

            val multipart: Multipart = MimeMultipart()
            multipart.addBodyPart(mimeBodyPart)
            mimeMessage.setContent(multipart)

            //Add file to message
            val pdf = PdfGenerator.create()
            val attachmentBodyPart = MimeBodyPart()
            attachmentBodyPart.attachFile(File("ticket.pdf"))
            multipart.addBodyPart(attachmentBodyPart)

            mimeMessage.subject = subject
            mimeMessage.sentDate = Date()

            val smtpTransport = session.getTransport("smtp")
            smtpTransport.connect()
            smtpTransport.sendMessage(mimeMessage, mimeMessage.allRecipients)
            smtpTransport.close()
        } catch (messagingException: MessagingException) {
            messagingException.printStackTrace()
        }
    }

    private fun putIfMissing(props: Properties, key: String, value: String) {
        if (!props.containsKey(key)) {
            props[key] = value
        }
    }
}
