package xyz.lib.bookstore.service;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.service
 * USER      : sean
 * DATE      : 05-Tue-Feb-2019
 * TIME      : 19:33
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public interface SequenceGeneratorService {

    /**
     * Generates an autoincrement value for all tables.
     * @param seqName of the table to generate a sequence for.
     * @return next incremented value.
     */
    Long generateSequence(String seqName);
}
