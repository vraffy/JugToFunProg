package it.jugtofunprog.textanalysis;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import it.jugtofunprog.textanalysis.model.AnalyzedText;
import it.jugtofunprog.textanalysis.model.Annotation;
import it.jugtofunprog.textanalysis.model.Entity;
import it.jugtofunprog.textanalysis.model.Entity.EntityType;
import it.jugtofunprog.textanalysis.model.Mood;
import it.jugtofunprog.textanalysis.model.Polarity;

import java.util.Map;

import org.junit.Test;

public abstract class AnalyzedTextProcessorTest {

    private final AnalyzedTextProcessor processor;

    public AnalyzedTextProcessorTest() {
        processor = getProcessor();
    }


    /** extractMood */

    @Test
    public void extractMoodFromAnalyzedTextWithoutAnnotations() {

        final AnalyzedText analyzedText = new AnalyzedText("testo senza annotazioni");

        final Mood mood = processor.extractMood(analyzedText);

        assertThat(mood, equalTo(Mood.NONE));
    }

    @Test
    public void extractMoodFromAnalyzedTextWithoutPolarityAnnotations() {

        final AnalyzedText analyzedText = new AnalyzedText("questo testo parla di Torino, Cuneo e Einstein, ma non esprime sentimenti");
        analyzedText.addAnnotation(new Entity(0, 22, 28, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Torino"));
        analyzedText.addAnnotation(new Entity(1, 30, 35, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Cuneo"));
        analyzedText.addAnnotation(new Entity(2, 38, 46, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Einstein"));

        final Mood mood = processor.extractMood(analyzedText);

        assertThat(mood, equalTo(Mood.NONE));
    }

    @Test
    public void extractMoodFromAnalyzedTextWithNegativePolarity() {

        final AnalyzedText analyzedText = new AnalyzedText("Torino è una delle città più inquinate d'Italia :(");
        analyzedText.addAnnotation(new Entity(0, 0, 6, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Torino"));
        analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText().length(), 27));

        final Mood mood = processor.extractMood(analyzedText);

        assertThat(mood, equalTo(Mood.NEGATIVE));
    }

    @Test
    public void extractMoodFromAnalyzedTextWithPositivePolarity() {

        final AnalyzedText analyzedText = new AnalyzedText("Einstein è stato un grande scienziato");
        analyzedText.addAnnotation(new Entity(0, 0, 8, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Einstein"));
        analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText().length(), 89));

        final Mood mood = processor.extractMood(analyzedText);

        assertThat(mood, equalTo(Mood.POSITIVE));
    }

    @Test
    public void extractMoodFromAnalyzedTextWithMultiplePolarity() {

        final AnalyzedText analyzedText = new AnalyzedText("Mi piace vivere a Torino anche se purtroppo è molto inquinata");
        analyzedText.addAnnotation(new Entity(0, 18, 24, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Torino"));
        analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText().length(), 95));
        analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText().length(), 27));

        final Mood mood = processor.extractMood(analyzedText);

        assertThat(mood, equalTo(Mood.POSITIVE));
    }

    /** indexPersons */

    @Test
    public void indexPersonsFromAnalyzedTextWithoutAnnotations() {

        final AnalyzedText analyzedText = new AnalyzedText("testo senza annotazioni");

        Map<Integer, Annotation> personIdx = processor.indexPersons(analyzedText);

        assertThat(personIdx.size(), is(0));
    }

    @Test
    public void indexPersonsFromAnalyzedTextWithoutPersonAnnotations() {

        final AnalyzedText analyzedText = new AnalyzedText("Mi piace vivere a Torino anche se purtroppo è molto inquinata");
        analyzedText.addAnnotation(new Entity(0, 18, 24, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Torino"));

        Map<Integer, Annotation> personIdx = processor.indexPersons(analyzedText);

        assertThat(personIdx.size(), is(0));
    }

    @Test
    public void indexPersonsFromAnalyzedTextWithPersonAnnotations() {

        final AnalyzedText analyzedText =
                new AnalyzedText(
                        "Il 29 settembre del 1901 nasceva a Roma Enrico Fermi. Paragonato a Galileo, Fermi è stato uno scienziato brillante. È morto a Chicago nel 1954.");
        analyzedText.addAnnotation(new Entity(0, 35, 39, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Roma"));
        analyzedText.addAnnotation(new Entity(1, 40, 52, EntityType.PERSON, "https://it.wikipedia.org/wiki/Enrico_Fermi"));
        analyzedText.addAnnotation(new Entity(2, 67, 74, EntityType.PERSON, "https://it.wikipedia.org/wiki/Galileo"));
        analyzedText.addAnnotation(new Entity(3, 76, 80, EntityType.PERSON, "https://it.wikipedia.org/wiki/Enrico_Fermi"));
        analyzedText.addAnnotation(new Entity(4, 126, 133, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Chicago"));

        Map<Integer, Annotation> personIdx = processor.indexPersons(analyzedText);

        assertThat(personIdx.size(), is(3));
        assertThat(personIdx.get(40).getId(), is(1));
        assertThat(personIdx.get(76).getId(), is(3));
        assertThat(personIdx.get(67).getId(), is(2));
    }


    protected abstract AnalyzedTextProcessor getProcessor();
}
