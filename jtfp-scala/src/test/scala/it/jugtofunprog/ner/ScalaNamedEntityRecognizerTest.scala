package it.jugtofunprog.ner

import org.junit.Test
import it.jugtofunprog.textanalysis.ScalaNamedEntityRecognizer

@Test
class ScalaNamedEntityRecognizerTest extends NamedEntityRecognizerTest {

  override def getNER(neRepo: NamedEntityRepository) = new ScalaNamedEntityRecognizer(neRepo)

}