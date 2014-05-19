package it.jugtofunprog.textanalysis

import org.junit.Test

@Test
class ScalaAnalyzedTextProcessorTest extends AnalyzedTextProcessorTest {

  override def getProcessor() = new ScalaAnalyzedTextProcessor

}


