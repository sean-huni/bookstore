package xyz.lib.bookstore.listener.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import xyz.lib.bookstore.model.UserRole;
import xyz.lib.bookstore.service.SequenceGeneratorService;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.repo.events
 * USER      : sean
 * DATE      : 05-Tue-Feb-2019
 * TIME      : 19:48
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Component
public class UserRoleListener extends AbstractMongoEventListener<UserRole> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleListener.class);
    private SequenceGeneratorService generatorService;

    @Autowired
    public UserRoleListener(SequenceGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<UserRole> event){
        LOGGER.info("onBeforeConvert({})", event.getSource());
        event.getSource().setId(generatorService.generateSequence(UserRole.SEQUENCE_NAME));
    }
}
