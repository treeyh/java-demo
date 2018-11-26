package com.ejyi.demo.activiti03.drools;

import com.ejyi.demo.activiti03.model.Person;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tree.yu
 * @version 1.0
 * @description 描述
 * @create 2018-11-25 22:18
 */
@Component
public class DroolsDemo {

    @Autowired
    private KieSession kieSession;


    public void personTestDrools(){
        Person person = new Person();
        person.setId(2);
        person.setName("Tree");
        kieSession.insert(person);

        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
