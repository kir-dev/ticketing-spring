package hu.bme.sch.kirdev.ticketingspring

import org.springframework.stereotype.Service

@Service
class AppService{
    fun getHello(): String{
        return "Hello World!"
    }

    fun getPersonalizedHello(name: String, day: String?): String{
        val Day = day?:"day"
        return "Hello, $name, have a nice $Day!"
    }
}