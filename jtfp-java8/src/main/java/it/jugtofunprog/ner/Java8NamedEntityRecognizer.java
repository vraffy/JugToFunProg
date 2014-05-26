package it.jugtofunprog.ner;

import it.jugtofunprog.ner.model.NamedEntity;

import java.util.Collection;
import java.util.List;

public class Java8NamedEntityRecognizer implements NamedEntityRecognizer {

    private final NamedEntityRepository neRepo;

    public Java8NamedEntityRecognizer(NamedEntityRepository neRepo) {
        this.neRepo = neRepo;
    }

    @Override
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens) {
        return extractNamedEntities(tokens, 1);
    }

    @Override
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens, int ngramSize) {
        return null;
    }

}
