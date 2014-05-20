package it.jugtofunprog.textanalysis

import scala.collection.JavaConversions.asJavaCollection
import scala.collection.JavaConversions.asScalaIterable

import it.jugtofunprog.ner.NamedEntityRecognizer
import it.jugtofunprog.ner.NamedEntityRepository

class ScalaNamedEntityRecognizer(val neRepo: NamedEntityRepository) extends NamedEntityRecognizer {

  override def extractNamedEntities(tokens: java.util.List[String]) = extractNamedEntities(tokens, 1)

  override def extractNamedEntities(tokens: java.util.List[String], ngramSize: Int) = {

    // conversione da Java a Scala :|
    val scalaTokens = asScalaIterable(tokens)

    // definisce tanti "sliding iterators" quanti sono i token che vogliamo includere in un singolo ngram
    val slidingIt = 1 to ngramSize map (scalaTokens sliding _)

    // crea una lista di liste di ngrammi, scorrendo i token della collezione originale
    // e applicando tanti "sliding iterators" quanti ne servono per raggiungere la lunghezza richiesta
    val ngrams = for {
      onegram <- scalaTokens
      ngram <- slidingIt if ngram.hasNext
    } yield (ngram.next)

    // "appiattisce" la lista di ngrammi, concatenando gli elementi composti da più token in un'unica stringa
    // utilizzabile come "lookup" all'interno del repository
    val ngramTokens = ngrams map (n => n.mkString(" "))

    // ogni token viene trasformato nella corrispondente collezione di NamedEntity riconosciute
    // il risultato viene poi "appiattito" dalla flatMap per restituire una collezione di NamedEntity
    // anziché una collezione di collezioni di NamedEntity
    val entities = ngramTokens flatMap (t => asScalaIterable(neRepo.recognize(t)))

    // conversione da Scala a Java :|
    asJavaCollection(entities)
  }

}