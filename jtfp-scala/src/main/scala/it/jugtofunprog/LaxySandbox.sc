package it.jugtofunprog

object LaxySandbox {
  
  val myList = 1 to 10 toList                     //> myList  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  
  val valFiltered = myList filter (elem => {println(elem); elem % 2 == 0})
                                                  //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
                                                  //| valFiltered  : List[Int] = List(2, 4, 6, 8, 10)
                                                  
   myList                                         //> res0: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                                                  
  def defFiltered  = myList filter (elem => {println(elem); elem % 2 == 0})
                                                  //> defFiltered: => List[Int]
                                                  
  defFiltered                                     //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
                                                  //| res1: List[Int] = List(2, 4, 6, 8, 10)
  
  defFiltered                                     //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
                                                  //| res2: List[Int] = List(2, 4, 6, 8, 10)
                                                  
  lazy val lazyFiltered = myList filter (elem => {println(elem); elem % 2 == 0})
                                                  //> lazyFiltered: => List[Int]
                                                  
  lazyFiltered                                    //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
                                                  //| res3: List[Int] = List(2, 4, 6, 8, 10)
  
  lazyFiltered                                    //> res4: List[Int] = List(2, 4, 6, 8, 10)
  
  
}