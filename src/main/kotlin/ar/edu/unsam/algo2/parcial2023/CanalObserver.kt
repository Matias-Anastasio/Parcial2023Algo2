package ar.edu.unsam.algo2.parcial2023

interface CanalObserver {
    fun agregarNuevoPrograma(canal: Canal,programa: Programa)
}

class NotificacionObserver(val mailSender: MailSender) : CanalObserver{
    override fun agregarNuevoPrograma(canal: Canal, programa: Programa) {
        programa.mailsDeConductores().forEach{mail->
            mailSender.sendMail(Mail(
                to = mail,
                from = "oportunidades@canal.com",
                subject = "Oportunidad!",
                content = "Fuiste contratado para conducir ${programa.titulo}! Ponete en contacto con la gerencia"
            ))
        }
    }
}

class PresupuestoFaltanteObserver(val smsSender: SmsSender): CanalObserver{
    override fun agregarNuevoPrograma(canal: Canal, programa: Programa) {
        if (programa.presupuesto>100_000){
            smsSender.sendSms(Sms(
                to="Cliowin",
                message = "$${programa.presupuesto} - ${programa.titulo} - CONSEGUIR SPONSOR URGENTE!"
            ))
        }
    }
}

class RevisionObserver : CanalObserver{
    override fun agregarNuevoPrograma(canal: Canal, programa: Programa) {
        canal.sincronizarProgramasEnRevision()
    }
}