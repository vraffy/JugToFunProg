package it.jugtofunprog.textanalysis;

import static it.jugtofunprog.ner.model.NamedEntity.EntityType.LOCATION;
import static it.jugtofunprog.ner.model.NamedEntity.EntityType.PERSON;
import static java.util.Comparator.naturalOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import it.jugtofunprog.textanalysis.model.AnalyzedText;
import it.jugtofunprog.textanalysis.model.Annotation;
import it.jugtofunprog.textanalysis.model.Entity;
import it.jugtofunprog.textanalysis.model.Mood;
import it.jugtofunprog.textanalysis.model.Polarity;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Java8AnalyzedTextProcessor implements AnalyzedTextProcessor {

    @Override
    public Mood extractMood(final AnalyzedText analyzedText) {

        return analyzedText.getAnnotations()
                .stream()
                .filter(a -> (a instanceof Polarity))
                .map(a -> (Polarity) a)
                .findFirst()
                .map(Polarity::getMood)
                .orElse(Mood.NONE);
    }

    @Override
    public Map<Integer, Annotation> indexPersons(AnalyzedText analyzedText) {
        return indexEntities(analyzedText, annotation -> (annotation instanceof Entity && ((Entity) annotation).getType().equals(PERSON)));
    }


    @Override
    public Map<Integer, Annotation> indexLocations(AnalyzedText analyzedText) {
        return indexEntities(analyzedText, annotation -> (annotation instanceof Entity && ((Entity) annotation).getType().equals(LOCATION)));
    }


    @Override
    public Map<Integer, Annotation> indexShortEntities(AnalyzedText analyzedText, int maxLength) {
        return indexEntities(analyzedText, annotation -> (annotation.getEnd() - annotation.getBegin() <= maxLength));
    }

    private Map<Integer, Annotation> indexEntities(AnalyzedText analyzedText, Predicate<Annotation> predicate) {

        Stream<Annotation> entities = analyzedText.getAnnotations().stream().filter(predicate);

        return entities.collect(toMap(Annotation::getBegin, identity()));
    }


    @Override
    public Collection<String> mostFrequentEntities(AnalyzedText analyzedText) {

        Stream<Entity> entities = analyzedText.getAnnotations()
                .stream()
                .filter(a -> (a instanceof Entity))
                .map(a -> (Entity) a);

        Map<String, Long> entitiesFreq = entities.collect(groupingBy(Entity::getIri, counting()));

        Optional<Long> maxFreq = entitiesFreq.values().stream().max(naturalOrder());

        return entitiesFreq.keySet().stream().filter(iri -> entitiesFreq.get(iri).equals(maxFreq.get())).collect(toList());
    }


}
