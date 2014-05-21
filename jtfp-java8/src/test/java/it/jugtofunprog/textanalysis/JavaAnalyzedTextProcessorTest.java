package it.jugtofunprog.textanalysis;

import it.jugtofunprog.textanalysis.AnalyzedTextProcessor;
import it.jugtofunprog.textanalysis.AnalyzedTextProcessorTest;
import it.jugtofunprog.textanalysis.JavaAnalyzedTextProcessor;

public class JavaAnalyzedTextProcessorTest extends AnalyzedTextProcessorTest {

    @Override
    protected AnalyzedTextProcessor getProcessor() {
        return new JavaAnalyzedTextProcessor();
    }

}
