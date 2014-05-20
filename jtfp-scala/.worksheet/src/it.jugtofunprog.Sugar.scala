package it.jugtofunprog

object Sugar {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(695); 

// Given n>0 find all pairs i and j where 1 <= j <= i <= n and i+j is prime

  /** Java 8 version (by Mario Fusco)
      http://www.slideshare.net/mariofusco/monadic-java
      
      Stream.iterate(1, i -> i+1).limit(n)
            .flatMap(i -> Stream.iterate(1, j -> j+1).limit(n)
            .map(j -> new int[]{i, j}))
            .filter(pair -> isPrime(pair[0] + pair[1]))
            .collect(toList());
         
      public boolean isPrime(int n) {
         return Stream.iterate(2, i -> i+1)
                      .limit((long) Math.sqrt(n))
                      .noneMatch(i -> n % i == 0);
      }

*/

/** Scala version */
  
  val n = 10;System.out.println("""n  : Int = """ + $show(n ));$skip(70); 
  
  val pairs = for {
   i <- 1 to n
   j <- i to n
  } yield (i, j);System.out.println("""pairs  : scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(pairs ));$skip(169); 
                                                  
  def isPrime(num: Int) = {
     val hasDiv = (2 to Math.sqrt(num).toInt) exists (d => num % d == 0)
     !hasDiv
  };System.out.println("""isPrime: (num: Int)Boolean""");$skip(46); val res$0 = 
  
  pairs filter (p => isPrime(p._1 + p._2));System.out.println("""res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$0))}
                                                  
                                                  
                                                   
  
}
