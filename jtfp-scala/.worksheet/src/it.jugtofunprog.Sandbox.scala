package it.jugtofunprog

import it.jugtofunprog.ner.InMemoryNamedEntityRepository
import scala.collection.JavaConversions.asScalaIterable

object Sandbox {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(234); 

  val scalaTokens = List("questo", "testo", "parla", "di", "enrico", "fermi");System.out.println("""scalaTokens  : List[String] = """ + $show(scalaTokens ));$skip(54); 

  val slidingIt = 1 to 3 map (scalaTokens sliding _);System.out.println("""slidingIt  : scala.collection.immutable.IndexedSeq[Iterator[List[String]]] = """ + $show(slidingIt ));$skip(112); 

  val ngrams = for {
    onegram <- scalaTokens
    ngram <- slidingIt if ngram.hasNext
  } yield (ngram.next);System.out.println("""ngrams  : List[List[String]] = """ + $show(ngrams ));$skip(54); 
  val ngramTokens = ngrams map (n => n.mkString(" "));System.out.println("""ngramTokens  : List[String] = """ + $show(ngramTokens ));$skip(50); 

  val neRepo = new InMemoryNamedEntityRepository;System.out.println("""neRepo  : it.jugtofunprog.ner.InMemoryNamedEntityRepository = """ + $show(neRepo ));$skip(81); 
  val entities = ngramTokens flatMap (t => asScalaIterable(neRepo.recognize(t)));System.out.println("""entities  : List[it.jugtofunprog.ner.model.NamedEntity] = """ + $show(entities ));$skip(32); val res$0 = 

  entities map (e => e.getIri);System.out.println("""res0: List[String] = """ + $show(res$0));$skip(87); 
  
  
  // ngrams map (n => n.foldLeft("")((f, s) => f+" "+s))
  
  val list = 1 to 10;System.out.println("""list  : scala.collection.immutable.Range.Inclusive = """ + $show(list ));$skip(63); 
  
  val filtList = list filter (v => {println(v); v %2 == 0});System.out.println("""filtList  : scala.collection.immutable.IndexedSeq[Int] = """ + $show(filtList ));$skip(116); 
                                                  
   def filtListLazy = list filter (v => {println(v); v %2 == 0});System.out.println("""filtListLazy: => scala.collection.immutable.IndexedSeq[Int]""")}
                                                  
   // filtListLazy
}
