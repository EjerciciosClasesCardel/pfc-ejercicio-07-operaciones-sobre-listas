package taller

// Un elemento de una lista anidada: un número o una sublista.
sealed trait Elemento
case class Valor(n: Int) extends Elemento
case class Sub(elementos: List[Elemento]) extends Elemento

// Una corrida es un valor y cuántas veces seguidas aparece.
case class Corrida(valor: Int, veces: Int)

class Ejercicio {

  // Tal como está, las pruebas quedan en rojo.

  // Punto 1. Recursión con if/else y las primitivas head, tail e isEmpty.
  // No use los métodos equivalentes de la biblioteca: el ejercicio es
  // escribirlos.

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

  // Punto 2. Recursión con match y los patrones Nil, x :: xs y sus variantes.

  /** La suma de los elementos; 0 para la lista vacía. */
  def sumar(l: List[Int]): Int = {
    0 // Completar
  }

  /** Si e aparece en la lista. */
  def contiene(l: List[Int], e: Int): Boolean = {
    false // Completar
  }

  /** La lista sin su último elemento. Falla con una lista vacía. */
  def inicio(l: List[Int]): List[Int] = {
    List() // Completar
  }

  /** Si cada elemento es estrictamente menor que el siguiente. */
  def esCreciente(l: List[Int]): Boolean = {
    false // Completar
  }

  // Punto 3. Ordenamiento por inserción.

  /** Pone x en su lugar dentro de una lista que ya está ordenada. */
  def insertar(x: Int, l: List[Int]): List[Int] = {
    List() // Completar
  }

  /** La lista ordenada de menor a mayor. */
  def ordenar(l: List[Int]): List[Int] = {
    List() // Completar
  }

  // Punto 4. Aplanar una lista anidada.

  /** Los números de la lista, en una sola lista y en el mismo orden,
    * sin importar a qué profundidad estén. */
  def aplanar(l: List[Elemento]): List[Int] = {
    List() // Completar
  }

  // Punto 5. Codificación por corridas y su inversa.

  /** Las corridas de la lista: cada valor con cuántas veces seguidas
    * aparece, en el orden en que aparecen. */
  def corridas(l: List[Int]): List[Corrida] = {
    List() // Completar
  }

  /** La lista que describen las corridas: cada valor repetido tantas veces
    * como dice su corrida. Una corrida con cero veces no aporta elementos. */
  def expandir(c: List[Corrida]): List[Int] = {
    List() // Completar
  }
}
