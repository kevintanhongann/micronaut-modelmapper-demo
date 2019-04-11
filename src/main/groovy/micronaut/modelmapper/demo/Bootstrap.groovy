package micronaut.modelmapper.demo

import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.event.annotation.EventListener

class Bootstrap {

  final PersonService personService

  Bootstrap(PersonService personService) {
    this.personService = personService
  }

  @EventListener
  void onStartup(ServiceStartedEvent event) {
    String charset = (('A'..'Z') + ('0'..'9')).join()

    // https://firebase.google.com/docs/auth/admin/custom-claims
    Item item = new Item()
    item.name = 'test'
    Item item2 = new Item()
    item2.name = 'test2'
    Item item3 = new Item()
    item3.name = 'test3'
    Item item4 = new Item()
    item4.name = 'test4'
    Item item5 = new Item()
    item5.name = 'test5'

    Person person = new Person()
    person.email = "kevin@kevin.com"
    person.name = 'kevin tan'
    person.items = [item, item2, item3, item4, item5]

    personService.save(person)

    Person person2 = new Person()
    person2.email = "kevin@kevin.com"
    person2.name = 'kevin tan'

    person2.items = [item, item2, item3, item4, item5]

    personService.save(person2)
  }
}
