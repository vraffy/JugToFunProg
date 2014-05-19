package it.jugtofunprog

import it.jugtofunprog.ner.InMemoryNamedEntityRepository
import scala.collection.JavaConversions.asScalaIterable

object Sandbox {

  val scalaTokens = List("questo", "testo", "parla", "di", "enrico", "fermi")
                                                  //> scalaTokens  : List[String] = List(questo, testo, parla, di, enrico, fermi)

  val slidingIt = 1 to 3 map (scalaTokens sliding _)
                                                  //> slidingIt  : scala.collection.immutable.IndexedSeq[Iterator[List[String]]] =
                                                  //|  Vector(non-empty iterator, non-empty iterator, non-empty iterator)

  val ngrams = for {
    onegram <- scalaTokens
    ngram <- slidingIt if ngram.hasNext
  } yield (ngram.next)                            //> ngrams  : List[List[String]] = List(List(questo), List(questo, testo), List(
                                                  //| questo, testo, parla), List(testo), List(testo, parla), List(testo, parla, d
                                                  //| i), List(parla), List(parla, di), List(parla, di, enrico), List(di), List(di
                                                  //| , enrico), List(di, enrico, fermi), List(enrico), List(enrico, fermi), List(
                                                  //| fermi))
  val ngramTokens = ngrams map (n => n.mkString(" "))
                                                  //> ngramTokens  : List[String] = List(questo, questo testo, questo testo parla,
                                                  //|  testo, testo parla, testo parla di, parla, parla di, parla di enrico, di, d
                                                  //| i enrico, di enrico fermi, enrico, enrico fermi, fermi)

  val neRepo = new InMemoryNamedEntityRepository  //> neRepo  : it.jugtofunprog.ner.InMemoryNamedEntityRepository = it.jugtofunpro
                                                  //| g.ner.InMemoryNamedEntityRepository@6fb05c54
  val entities = ngramTokens flatMap (t => asScalaIterable(neRepo.recognize(t)))
                                                  //> entities  : List[it.jugtofunprog.ner.model.NamedEntity] = List(it.jugtofunpr
                                                  //| og.ner.model.NamedEntity@5b54614c)

  entities map (e => e.getIri)                    //> res0: List[String] = List(https://it.wikipedia.org/wiki/Enrico_Fermi)
  
  
  // ngrams map (n => n.foldLeft("")((f, s) => f+" "+s))
}