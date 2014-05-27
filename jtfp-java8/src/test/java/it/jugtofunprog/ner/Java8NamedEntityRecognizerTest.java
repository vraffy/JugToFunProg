package it.jugtofunprog.ner;



public class Java8NamedEntityRecognizerTest extends NamedEntityRecognizerTest {

    @Override
    protected NamedEntityRecognizer getNER(NamedEntityRepository neRepo) {
        return new Java8NamedEntityRecognizer(neRepo);
    }

}
