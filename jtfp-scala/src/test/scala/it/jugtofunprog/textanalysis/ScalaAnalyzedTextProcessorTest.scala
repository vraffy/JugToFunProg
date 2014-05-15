package it.jugtofunprog.textanalysis

import org.junit.Test
import it.jugtofunprog.textanalysis.ScalaAnalyzedTextProcessor

@Test
class ScalaAnalyzedTextProcessorTest extends AnalyzedTextProcessorTest {

  override def getProcessor() = {
    new ScalaAnalyzedTextProcessor()
  }

}


