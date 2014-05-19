package it.jugtofunprog.ner;

import it.jugtofunprog.ner.model.NamedEntity;

import java.util.Collection;
import java.util.List;

public class JavaNamedEntityRecognizer implements NamedEntityRecognizer {

    private final NamedEntityRepository neRepo;

    public JavaNamedEntityRecognizer(NamedEntityRepository neRepo) {
        this.neRepo = neRepo;
    }

    @Override
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens) {
        // TODO your code here
        return null;
    }

    @Override
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens, int ngramSize) {
        // TODO your code here
        return null;
    }

}
