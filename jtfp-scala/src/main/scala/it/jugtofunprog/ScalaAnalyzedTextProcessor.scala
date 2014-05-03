package it.jugtofunprog

import scala.collection.JavaConversions.asScalaBuffer

import it.jugtofunprog.textanalysis.AnalyzedTextProcessor
import it.jugtofunprog.textanalysis.model.AnalyzedText
import it.jugtofunprog.textanalysis.model.Mood
import it.jugtofunprog.textanalysis.model.Polarity

class ScalaAnalyzedTextProcessor extends AnalyzedTextProcessor {

  def extractMood(analyzedText: AnalyzedText): Mood = {

    // scorre la lista delle annotazioni presenti in un AnalyzedText e restituisce, 
    // se presente, la prima di tipo Polarity
    // firstPol:Option[Polarity]
    val firstPol = analyzedText.getAnnotations collectFirst { case a: Polarity => a }

    // applica alla Option una funzione che ne estrae il valore di Mood
    // restituisce il valore calcolato se la Polarity era presente
    // in caso contrario restituisce il valore di default (NONE)
    firstPol.map(pol => pol.getMood()).getOrElse(Mood.NONE);
  }

}