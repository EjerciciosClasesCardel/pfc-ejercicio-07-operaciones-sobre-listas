package taller

class Ejercicio {

  // Todas se escriben con recursión sobre la lista, usando head, tail e
  // isEmpty o el patrón x :: xs. No use los métodos equivalentes de la
  // biblioteca: el ejercicio es escribirlos.
  // Tal como está, las pruebas quedan en rojo.

  /** Cuántos elementos tiene la lista. */
  def longitud(l: List[Int]): Int = {
    0 // Completar
  }

  /** La lista con los elementos en orden inverso. */
  def invertir(l: List[Int]): List[Int] = {
    List() // Completar
  }

  /** Los elementos de l1 seguidos de los de l2. */
  def concatenar(l1: List[Int], l2: List[Int]): List[Int] = {
    List() // Completar
  }

  /** El último elemento. Falla con una lista vacía. */
  def ultimo(l: List[Int]): Int = {
    if (l.isEmpty) throw new NoSuchElementException("la lista está vacía")
    else 0 // Completar
  }

  /** Los primeros n elementos. Con n mayor que la longitud, la lista entera. */
  def tomar(l: List[Int], n: Int): List[Int] = {
    List() // Completar
  }

  /** La lista sin sus primeros n elementos. */
  def soltar(l: List[Int], n: Int): List[Int] = {
    List() // Completar
  }

  /** El elemento en la posición n, contando desde cero. */
  def enPosicion(l: List[Int], n: Int): Int = {
    if (n < 0 || n >= longitud(l)) throw new IndexOutOfBoundsException(n.toString)
    else 0 // Completar
  }
}
