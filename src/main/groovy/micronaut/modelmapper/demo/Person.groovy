/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package micronaut.modelmapper.demo

import grails.gorm.annotation.Entity

/**
 *
 * @author kevintan
 */
@Entity
class Person {  
    
    String name
    String email
    
    static hasMany = [items: Item]
    
    static mapping = {
        autoTimestamp true
    }

}

