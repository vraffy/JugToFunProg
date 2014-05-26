package it.jugtofunprog.ner;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import it.jugtofunprog.ner.model.NamedEntity;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public abstract class NamedEntityRecognizerTest {

    private final NamedEntityRecognizer ner;

    public NamedEntityRecognizerTest() {
        ner = getNER(new InMemoryNamedEntityRepository());
    }


    /** extractNamedEntities */

    @Test
    public void extractNamedEntitiesFromEmptyText() {

        final List<String> tokens = emptyList();

        final Collection<NamedEntity> entities = ner.extractNamedEntities(tokens);

        assertThat(entities, hasSize(0));
    }

    @Test
    public void extractNamedEntitiesWithoutAmbiguities() {

        final List<String> tokens = asList("questo", "testo", "parla", "di", "einstein", "e", "di", "enrico", "fermi");

        final Collection<NamedEntity> entities = ner.extractNamedEntities(tokens);

        assertThat(entities, hasSize(1));

        Iterator<NamedEntity> it = entities.iterator();
        assertThat(it.next().getIri(), equalTo("https://it.wikipedia.org/wiki/Einstein"));
    }

    @Test
    public void extractNamedEntitiesUsingBigrams() {

        final List<String> tokens = asList("questo", "testo", "parla", "di", "einstein", "e", "di", "enrico", "fermi");

        final Collection<NamedEntity> entities = ner.extractNamedEntities(tokens, 2);

        assertThat(entities, hasSize(2));

        Iterator<NamedEntity> it = entities.iterator();
        assertThat(it.next().getIri(), equalTo("https://it.wikipedia.org/wiki/Einstein"));
        assertThat(it.next().getIri(), equalTo("https://it.wikipedia.org/wiki/Enrico_Fermi"));
    }

    @Test
    public void extractNamedEntitiesWithAmbiguities() {

        final List<String> tokens = asList("einstein", "leggeva", "dumas", "?", "forse", "ma", "quale", "?");

        final Collection<NamedEntity> entities = ner.extractNamedEntities(tokens);

        assertThat(entities, hasSize(3));

        Iterator<NamedEntity> it = entities.iterator();
        assertThat(it.next().getIri(), equalTo("https://it.wikipedia.org/wiki/Einstein"));
        assertThat(it.next().getIri(), equalTo("https://it.wikipedia.org/wiki/Alexandre_Dumas_(padre)"));
        assertThat(it.next().getIri(), equalTo("https://it.wikipedia.org/wiki/Alexandre_Dumas_(figlio)"));
    }

    @Test
    public void extractNamedEntitiesUsingTrigrams() {

        final List<String> tokens =
                asList("adesso", "è", "chiaro", "che", "parliamo", "di", "miguel", "de", "cervantes", ", ", "einstein", "ed", "enrico", "fermi");

        final Collection<NamedEntity> entities = ner.extractNamedEntities(tokens, 3);

        assertThat(entities, hasSize(3));

        Iterator<NamedEntity> it = entities.iterator();
        assertThat(it.next().getIri(), equalTo("https://it.wikipedia.org/wiki/Miguel_de_Cervantes"));
        assertThat(it.next().getIri(), equalTo("https://it.wikipedia.org/wiki/Einstein"));
        assertThat(it.next().getIri(), equalTo("https://it.wikipedia.org/wiki/Enrico_Fermi"));
    }


    protected abstract NamedEntityRecognizer getNER(NamedEntityRepository neRepo);
}
