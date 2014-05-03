package it.jugtofunprog.textanalysis

import org.junit.Test
import it.jugtofunprog.ScalaAnalyzedTextProcessor

@Test
class ScalaAnalyzedTextProcessorTest extends AnalyzedTextProcessorTest {

  override def getProcessor() = {
    new ScalaAnalyzedTextProcessor()
  }

}


