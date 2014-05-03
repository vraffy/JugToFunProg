package it.jugtofunprog.textanalysis;

import it.jugtofunprog.textanalysis.model.AnalyzedText;
import it.jugtofunprog.textanalysis.model.Entity;
import it.jugtofunprog.textanalysis.model.Entity.EntityType;
import it.jugtofunprog.textanalysis.model.Mood;
import it.jugtofunprog.textanalysis.model.Polarity;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public abstract class AnalyzedTextProcessorTest {

    private final AnalyzedTextProcessor processor;

    public AnalyzedTextProcessorTest() {
        processor = getProcessor();
    }

    @Test
    public void extractMoodFromAnalyzedTextWithoutAnnotations() {

        final AnalyzedText analyzedText = new AnalyzedText("testo senza annotazioni");

        final Mood mood = processor.extractMood(analyzedText);

        Assert.assertThat(mood, Matchers.equalTo(Mood.NONE));
    }

    @Test
    public void extractMoodFromAnalyzedTextWithoutPolarityAnnotations() {

        final AnalyzedText analyzedText = new AnalyzedText("questo testo parla di Torino, Cuneo e Einstein, ma non esprime sentimenti");
        analyzedText.addAnnotation(new Entity(0, 22, 28, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Torino"));
        analyzedText.addAnnotation(new Entity(1, 30, 35, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Cuneo"));
        analyzedText.addAnnotation(new Entity(2, 38, 46, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Einstein"));

        final Mood mood = processor.extractMood(analyzedText);

        Assert.assertThat(mood, Matchers.equalTo(Mood.NONE));
    }

    @Test
    public void extractMoodFromAnalyzedTextWithNegativePolarity() {

        final AnalyzedText analyzedText = new AnalyzedText("Torino è una delle città più inquinate d'Italia :(");
        analyzedText.addAnnotation(new Entity(0, 0, 6, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Torino"));
        analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText().length(), 27));

        final Mood mood = processor.extractMood(analyzedText);

        Assert.assertThat(mood, Matchers.equalTo(Mood.NEGATIVE));
    }

    @Test
    public void extractMoodFromAnalyzedTextWithPositivePolarity() {

        final AnalyzedText analyzedText = new AnalyzedText("Einstein è stato un grande scienziato");
        analyzedText.addAnnotation(new Entity(0, 0, 8, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Einstein"));
        analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText().length(), 89));

        final Mood mood = processor.extractMood(analyzedText);

        Assert.assertThat(mood, Matchers.equalTo(Mood.POSITIVE));
    }

    @Test
    public void extractMoodFromAnalyzedTextWithMultiplePolarity() {

        final AnalyzedText analyzedText = new AnalyzedText("Mi piace vivere a Torino anche se purtroppo è molto inquinata");
        analyzedText.addAnnotation(new Entity(0, 18, 24, EntityType.LOCATION, "https://it.wikipedia.org/wiki/Torino"));
        analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText().length(), 95));
        analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText().length(), 27));

        final Mood mood = processor.extractMood(analyzedText);

        Assert.assertThat(mood, Matchers.equalTo(Mood.POSITIVE));
    }

    protected abstract AnalyzedTextProcessor getProcessor();
}
