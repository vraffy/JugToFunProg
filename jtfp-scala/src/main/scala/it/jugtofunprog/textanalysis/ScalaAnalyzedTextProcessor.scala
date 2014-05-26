package it.jugtofunprog.textanalysis

import java.util.Collections.emptySet
import scala.collection.JavaConversions.asJavaSet
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.mapAsJavaMap
import it.jugtofunprog.textanalysis.model.AnalyzedText
import it.jugtofunprog.textanalysis.model.Annotation
import it.jugtofunprog.textanalysis.model.Entity
import it.jugtofunprog.textanalysis.model.Mood
import it.jugtofunprog.textanalysis.model.Polarity
import it.jugtofunprog.ner.model.NamedEntity.EntityType

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

  override def indexPersons(analyzedText: AnalyzedText) =
    indexEntities(analyzedText, { case a: Entity => a.getType().equals(EntityType.PERSON) })

  override def indexLocations(analyzedText: AnalyzedText) =
    indexEntities(analyzedText, { case a: Entity => a.getType().equals(EntityType.LOCATION) })

  override def indexShortEntities(analyzedText: AnalyzedText, maxLength: Int) =
    indexEntities(analyzedText, { case a: Entity => (a.getEnd() - a.getBegin() <= maxLength) })

  private def indexEntities(analyzedText: AnalyzedText, predicate: Annotation => Boolean) = {

    // scorre la lista delle annotazioni presenti in un AnalyzedText e restituisce
    // quelle che soddisfano il predicato
    val entities = analyzedText.getAnnotations() filter predicate

    // converte ogni elemento della collezione persons in una coppia costituita dal begin dell'annotazione
    // e dall'annotazione stessa, quindi trasforma la lista di coppie in una mappa
    // Integer.valueOf() serve solo per poter restituire il tipo corretto a Java :|
    val idx = entities.map(p => (Integer.valueOf(p.getBegin()), p)).toMap

    // quest'orrore si trova qui solo per poter restituire una mappa gradita a Java :|
    mapAsJavaMap((collection.mutable.Map() ++ idx))
  }

  override def mostFrequentEntities(analyzedText: AnalyzedText) = {

    // colleziona la lista delle sole annotazioni di tipo Entity presenti nel testo
    val entities = analyzedText.getAnnotations() collect { case a: Entity => a }

    // raggruppa le annotazioni che fanno riferimento alla stessa entità, creando una mappa che
    // contiene come chiave una IRI e come valore la collezione di Entity corrispondente
    // i valori della mappa risultante sono poi trasformati estraendone la sola lunghezza
    val entitiesFreq = entities groupBy (e => e.getIri()) mapValues (v => v.size)

    // seleziona il valore massimo delle frequenze (se presente)
    // maxFreq:Option[Int]
    val maxFreq = entitiesFreq.values reduceOption (_ max _)

    // filtra la mappa mantenendo solo le entry per le quali il valore della frequenza
    // corrisponde con il valore massimo, quindi estrae l'elenco delle IRI
    val mostFreqEntities = entitiesFreq filter (ef => ef._2 == maxFreq.get) keySet

    // trasformazione necessaria per restituire un valore di tipo corretto a Java :|
    asJavaSet(mostFreqEntities)
  }

}