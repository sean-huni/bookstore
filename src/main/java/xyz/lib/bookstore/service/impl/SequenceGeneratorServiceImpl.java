package xyz.lib.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import xyz.lib.bookstore.model.DatabaseSequence;
import xyz.lib.bookstore.service.SequenceGeneratorService;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service.impl
 * USER      : sean
 * DATE      : 05-Tue-Feb-2019
 * TIME      : 19:34
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceGeneratorServiceImpl.class);
    private MongoOperations mongoOperations;


    @Autowired
    public SequenceGeneratorServiceImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }


    @Override
    public Long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
        LOGGER.info("SeqName: {}, Value: {}", seqName, counter.getSeq());
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
