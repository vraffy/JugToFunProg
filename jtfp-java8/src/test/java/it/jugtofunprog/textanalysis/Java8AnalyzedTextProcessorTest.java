package it.jugtofunprog.textanalysis;


public class Java8AnalyzedTextProcessorTest extends AnalyzedTextProcessorTest {

    @Override
    protected AnalyzedTextProcessor getProcessor() {
        return new Java8AnalyzedTextProcessor();
    }

}
