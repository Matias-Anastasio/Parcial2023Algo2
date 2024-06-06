package ar.edu.unsam.algo2.parcial2023

data class Mail(val to: String,val from:String,val subject:String, val content:String)

interface MailSender {
    fun sendMail(mail: Mail)
}

data class Sms(val to: String,val message: String)

interface SmsSender{
    fun sendSms(sms: Sms)
}