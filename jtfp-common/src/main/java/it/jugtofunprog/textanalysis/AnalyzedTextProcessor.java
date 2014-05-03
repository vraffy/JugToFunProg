package it.jugtofunprog.textanalysis;

import it.jugtofunprog.textanalysis.model.AnalyzedText;
import it.jugtofunprog.textanalysis.model.Mood;

public interface AnalyzedTextProcessor {

    public Mood extractMood(AnalyzedText analyzedText);

}
