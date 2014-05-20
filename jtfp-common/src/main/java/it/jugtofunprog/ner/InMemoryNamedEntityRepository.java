package it.jugtofunprog.ner;

import static it.jugtofunprog.ner.model.NamedEntity.EntityType.LOCATION;
import static it.jugtofunprog.ner.model.NamedEntity.EntityType.ORG;
import static it.jugtofunprog.ner.model.NamedEntity.EntityType.PERSON;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import it.jugtofunprog.ner.model.NamedEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryNamedEntityRepository implements NamedEntityRepository {

    private final Map<String, Collection<NamedEntity>> entities;

    public InMemoryNamedEntityRepository() {

        entities = new HashMap<>();
        entities.put("enrico fermi", asList(new NamedEntity("https://it.wikipedia.org/wiki/Enrico_Fermi", PERSON)));
        entities.put("einstein", asList(new NamedEntity("https://it.wikipedia.org/wiki/Einstein", PERSON)));
        entities.put("dumas", asList(new NamedEntity("https://it.wikipedia.org/wiki/Alexandre_Dumas_(padre)", PERSON),
                new NamedEntity("https://it.wikipedia.org/wiki/Alexandre_Dumas_(figlio)", PERSON)));
        entities.put("miguel de cervantes", asList(new NamedEntity("https://it.wikipedia.org/wiki/Miguel_de_Cervantes", PERSON)));
        entities.put("bach", asList(new NamedEntity("https://it.wikipedia.org/wiki/Bach", PERSON),
                new NamedEntity("https://it.wikipedia.org/wiki/Bach_(Francia)", LOCATION)));
        entities.put("ferrari", asList(new NamedEntity("https://it.wikipedia.org/wiki/Ferrari", ORG),
                new NamedEntity("https://it.wikipedia.org/wiki/Enzo_Ferrari", PERSON),
                new NamedEntity("https://it.wikipedia.org/wiki/Gaudenzio_Ferrari", PERSON)));
    }



    @Override
    public Collection<NamedEntity> recognize(String candidate) {
        if (entities.containsKey(candidate)) {
            return entities.get(candidate);
        }

        return emptyList();
    }

}
