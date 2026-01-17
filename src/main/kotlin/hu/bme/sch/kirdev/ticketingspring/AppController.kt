package hu.bme.sch.kirdev.ticketingspring

import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class AppController(
    private val appService: AppService
){
    @GetMapping("/")
    fun getHello(): String{
        return appService.getHello()
    }

    @GetMapping("/hello/{name}")
    fun getPersonalizedHello(@PathVariable name: String, @Param("day") day: String?): String{
        return appService.getPersonalizedHello(name, day)
    }

}