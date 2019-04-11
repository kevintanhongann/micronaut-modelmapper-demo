/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package micronaut.modelmapper.demo

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single
import micronaut.modelmapper.demo.dto.PersonDTO
import org.modelmapper.Condition
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import org.modelmapper.config.Configuration
import org.modelmapper.spi.MappingContext

import java.lang.reflect.Type

/**
 *
 * @author kevintan
 */
@Controller("/person")
class PersonController {

    final PersonService personService

    PersonController(PersonService personService) {
        this.personService = personService
    }

    //this will presumably causes an exception
    @Get("/")
    Single<HttpResponse<?>> list() {
        Single.just(this.personService.list()).map({ result ->
            println 'person result ' + result
            List<PersonDTO> dtoList = ObjectMapperUtils.mapAll(result, PersonDTO)
            println 'dto '+dtoList
            HttpResponse.ok(dtoList)
        }).onErrorReturn({ throwable ->
            HttpResponse.badRequest(throwable.message)
        })
    }




}
