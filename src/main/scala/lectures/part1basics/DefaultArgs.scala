package lectures.part1basics

object DefaultArgs extends App {

  def tailRecursiveFactorial(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else tailRecursiveFactorial(n-1, n*acc)

  val fact10 = tailRecursiveFactorial(10)
  val twiceFact10 = tailRecursiveFactorial(10, 2)

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving picture ...")
  savePicture("jpg", 800, 600)
  savePicture(width = 800)
  savePicture(height = 800, width = 1100, format = "bmp") // This way allows reordering of arguments
  /*
    1. Pass in every leading argument
      OR
    2. Name the arguments
  */
}
