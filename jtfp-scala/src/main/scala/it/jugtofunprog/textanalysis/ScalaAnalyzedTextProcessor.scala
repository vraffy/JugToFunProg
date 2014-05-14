package it.jugtofunprog.textanalysis

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.mapAsJavaMap

import it.jugtofunprog.textanalysis.model.AnalyzedText
import it.jugtofunprog.textanalysis.model.Entity
import it.jugtofunprog.textanalysis.model.Entity.EntityType
import it.jugtofunprog.textanalysis.model.Mood
import it.jugtofunprog.textanalysis.model.Polarity

class ScalaAnalyzedTextProcessor extends AnalyzedTextProcessor {

  override def extractMood(analyzedText: AnalyzedText) = {

    // scorre la lista delle annotazioni presenti in un AnalyzedText e restituisce, 
    // se presente, la prima di tipo Polarity
    // firstPol:Option[Polarity]
    val firstPol = analyzedText.getAnnotations collectFirst { case a: Polarity => a }

    // applica alla Option una funzione che ne estrae il valore di Mood
    // restituisce il valore calcolato se la Polarity era presente
    // in caso contrario restituisce il valore di default (NONE)
    firstPol.map(pol => pol.getMood()).getOrElse(Mood.NONE);
  }

  override def indexPersons(analyzedText: AnalyzedText) = {

    // scorre la lista delle annotazioni presenti in un AnalyzedText e restituisce
    // quelle di tipo Entity che abbiano associato un type PERSON
    val persons = analyzedText.getAnnotations() filter { case a: Entity => a.getType().equals(EntityType.PERSON) }

    // converte ogni elemento della collezione persons in una coppia costituita dal begin dell'annotazione
    // e dall'annotazione stessa, quindi trasforma la lista di coppie in una mappa
    val idx = persons.map(p => (Integer.valueOf(p.getBegin()), p)).toMap

    // quest'orrore si trova qui solo per poter restituire una mappa gradita a Java :|
    mapAsJavaMap((collection.mutable.Map() ++ idx))
  }

}