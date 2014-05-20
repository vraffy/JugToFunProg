package it.jugtofunprog

object Sugar {

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
  
  val n = 10                                      //> n  : Int = 10
  
  val pairs = for {
   i <- 1 to n
   j <- i to n
  } yield (i, j)                                  //> pairs  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (
                                                  //| 1,2), (1,3), (1,4), (1,5), (1,6), (1,7), (1,8), (1,9), (1,10), (2,2), (2,3),
                                                  //|  (2,4), (2,5), (2,6), (2,7), (2,8), (2,9), (2,10), (3,3), (3,4), (3,5), (3,6
                                                  //| ), (3,7), (3,8), (3,9), (3,10), (4,4), (4,5), (4,6), (4,7), (4,8), (4,9), (4
                                                  //| ,10), (5,5), (5,6), (5,7), (5,8), (5,9), (5,10), (6,6), (6,7), (6,8), (6,9),
                                                  //|  (6,10), (7,7), (7,8), (7,9), (7,10), (8,8), (8,9), (8,10), (9,9), (9,10), (
                                                  //| 10,10))
                                                  
  def isPrime(num: Int) = {
     val hasDiv = (2 to Math.sqrt(num).toInt) exists (d => num % d == 0)
     !hasDiv
  }                                               //> isPrime: (num: Int)Boolean
  
  pairs filter (p => isPrime(p._1 + p._2))        //> res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (1,2
                                                  //| ), (1,4), (1,6), (1,10), (2,3), (2,5), (2,9), (3,4), (3,8), (3,10), (4,7), (
                                                  //| 4,9), (5,6), (5,8), (6,7), (7,10), (8,9), (9,10))
                                                  
                                                  
                                                   
  
}