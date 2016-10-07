/**
 * 
 */
package jms.simple.javaee.services;

import java.util.List;

import jms.simple.javaee.domain.Hello;

/**
 * @author jsantuci
 *
 */
public interface HelloService {

    void createMessage(Hello hello);
    Hello findMessage(Long code);
    List<Hello> findAll();

    String sayHello();
}
