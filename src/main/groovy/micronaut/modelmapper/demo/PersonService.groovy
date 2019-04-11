/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package micronaut.modelmapper.demo

import grails.gorm.services.Service
import groovy.transform.CompileStatic

import javax.inject.Singleton

/**
 *
 * @author kevintan
 */
@Service(Person)
@Singleton
@CompileStatic
interface PersonService {
    Person get(Serializable id)
    
    List<Person> list(Map args)
    
    Long count()
    
    void delete(Serializable id)
    
    Person save(Person object)
}

