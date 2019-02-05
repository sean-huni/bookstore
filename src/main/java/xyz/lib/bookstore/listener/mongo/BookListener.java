package xyz.lib.bookstore.listener.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import xyz.lib.bookstore.model.Book;
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
public class BookListener extends AbstractMongoEventListener<Book> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookListener.class);
    private SequenceGeneratorService generatorService;

    @Autowired
    public BookListener(SequenceGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event){
        LOGGER.info("onBeforeConvert({})", event.getSource());
        event.getSource().setId(generatorService.generateSequence(Book.SEQUENCE_NAME));
    }
}
