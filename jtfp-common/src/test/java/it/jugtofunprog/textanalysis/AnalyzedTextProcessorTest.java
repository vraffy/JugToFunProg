package it.jugtofunprog.textanalysis;

import it.jugtofunprog.textanalysis.AnalyzedTextProcessor;
import it.jugtofunprog.textanalysis.model.AnalyzedText;
import it.jugtofunprog.textanalysis.model.Mood;

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

    protected abstract AnalyzedTextProcessor getProcessor();
}
