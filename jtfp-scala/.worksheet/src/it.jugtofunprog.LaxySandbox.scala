package it.jugtofunprog

object LaxySandbox {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(78); 
  
  val myList = 1 to 10 toList;System.out.println("""myList  : List[Int] = """ + $show(myList ));$skip(78); 
  
  val valFiltered = myList filter (elem => {println(elem); elem % 2 == 0});System.out.println("""valFiltered  : List[Int] = """ + $show(valFiltered ));$skip(61); val res$0 = 
                                                  
   myList;System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(127); 
                                                  
  def defFiltered  = myList filter (elem => {println(elem); elem % 2 == 0});System.out.println("""defFiltered: => List[Int]""");$skip(65); val res$1 = 
                                                  
  defFiltered;System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(17); val res$2 = 
  
  defFiltered;System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(132); 
                                                  
  lazy val lazyFiltered = myList filter (elem => {println(elem); elem % 2 == 0});System.out.println("""lazyFiltered: => List[Int]""");$skip(66); val res$3 = 
                                                  
  lazyFiltered;System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(18); val res$4 = 
  
  lazyFiltered;System.out.println("""res4: List[Int] = """ + $show(res$4))}
  
  
}
