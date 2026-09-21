package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EjercicioTest extends AnyFunSuite {
  val obj = new Ejercicio()
  val l = List(4, 8, 15, 16, 23, 42)

  // Punto 1: operaciones básicas con if/else

  test("longitud de la vacía, de un elemento y de l") {
    assert(obj.longitud(List()) == 0)
    assert(obj.longitud(List(7)) == 1)
    assert(obj.longitud(l) == 6)
  }

  test("invertir la vacía, una de un elemento y l") {
    assert(obj.invertir(List()) == List())
    assert(obj.invertir(List(7)) == List(7))
    assert(obj.invertir(l) == List(42, 23, 16, 15, 8, 4))
  }

  test("Invertir dos veces devuelve la lista original") {
    assert(obj.invertir(obj.invertir(l)) == l)
  }

  test("concatenar con la vacía a cada lado y dos listas cortas") {
    assert(obj.concatenar(List(), List()) == List())
    assert(obj.concatenar(List(), l) == l)
    assert(obj.concatenar(l, List()) == l)
    assert(obj.concatenar(List(1, 2), List(3, 4)) == List(1, 2, 3, 4))
  }

  test("La longitud de la concatenación es la suma de las longitudes") {
    assert(obj.longitud(obj.concatenar(l, l)) == 12)
  }

  test("ultimo de una lista de un elemento y de l") {
    assert(obj.ultimo(List(7)) == 7)
    assert(obj.ultimo(l) == 42)
  }

  test("Último de la lista vacía falla") {
    assertThrows[NoSuchElementException](obj.ultimo(List()))
  }

  test("tomar con n = 0, en medio, exacto, de más y de la vacía") {
    assert(obj.tomar(l, 0) == List())
    assert(obj.tomar(l, 3) == List(4, 8, 15))
    assert(obj.tomar(l, 6) == l)
    assert(obj.tomar(l, 100) == l)
    assert(obj.tomar(List(), 3) == List())
  }

  test("soltar con n = 0, en medio, exacto y de más") {
    assert(obj.soltar(l, 0) == l)
    assert(obj.soltar(l, 3) == List(16, 23, 42))
    assert(obj.soltar(l, 6) == List())
    assert(obj.soltar(l, 100) == List())
  }

  test("Tomar y soltar reconstruyen la lista") {
    assert(obj.concatenar(obj.tomar(l, 2), obj.soltar(l, 2)) == l)
  }

  test("enPosicion en el primero, el último y uno del medio") {
    assert(obj.enPosicion(l, 0) == 4)
    assert(obj.enPosicion(l, 5) == 42)
    assert(obj.enPosicion(l, 3) == 16)
  }

  test("Una posición fuera de rango falla") {
    assertThrows[IndexOutOfBoundsException](obj.enPosicion(l, 6))
    assertThrows[IndexOutOfBoundsException](obj.enPosicion(l, -1))
  }

  // Punto 2: pattern matching sobre listas

  test("sumar de la vacía es 0 y de una lista es la suma de sus elementos") {
    assert(obj.sumar(List()) == 0)
    assert(obj.sumar(List(7)) == 7)
    assert(obj.sumar(l) == 108)
    assert(obj.sumar(List(-3, 3, -5)) == -5)
  }

  test("contiene encuentra el primero, el último y ninguno") {
    assert(obj.contiene(l, 4))
    assert(obj.contiene(l, 42))
    assert(!obj.contiene(l, 5))
    assert(!obj.contiene(List(), 1))
  }

  test("inicio quita el último elemento") {
    assert(obj.inicio(List(7)) == List())
    assert(obj.inicio(l) == List(4, 8, 15, 16, 23))
  }

  test("inicio de la lista vacía falla") {
    assertThrows[NoSuchElementException](obj.inicio(List()))
  }

  test("esCreciente con listas de cero, uno y dos elementos") {
    assert(obj.esCreciente(List()))
    assert(obj.esCreciente(List(5)))
    assert(obj.esCreciente(List(1, 2)))
    assert(!obj.esCreciente(List(2, 1)))
    assert(!obj.esCreciente(List(3, 3)))
  }

  test("esCreciente compara cada par de vecinos, no uno de cada dos") {
    assert(obj.esCreciente(l))
    assert(!obj.esCreciente(List(1, 3, 2, 4)))
    assert(!obj.esCreciente(List(1, 2, 3, 3)))
  }

  // Punto 3: ordenamiento por inserción

  test("insertar en la vacía, al frente, en medio y al final") {
    assert(obj.insertar(5, List()) == List(5))
    assert(obj.insertar(1, List(2, 7, 10)) == List(1, 2, 7, 10))
    assert(obj.insertar(4, List(2, 7, 10)) == List(2, 4, 7, 10))
    assert(obj.insertar(12, List(2, 7, 10)) == List(2, 7, 10, 12))
  }

  test("insertar un repetido lo deja junto a su igual y respeta negativos") {
    assert(obj.insertar(7, List(2, 7, 10)) == List(2, 7, 7, 10))
    assert(obj.insertar(-3, List(-5, 0)) == List(-5, -3, 0))
  }

  test("ordenar una lista desordenada y una con repetidos") {
    assert(obj.ordenar(List(8, 4, 11, 2)) == List(2, 4, 8, 11))
    assert(obj.ordenar(List(3, 1, 4, 1, 5, 9, 2, 6)) == List(1, 1, 2, 3, 4, 5, 6, 9))
  }

  test("ordenar la vacía, una ya ordenada, una al revés y una con negativos") {
    assert(obj.ordenar(List()) == List())
    assert(obj.ordenar(List(1, 2, 3)) == List(1, 2, 3))
    assert(obj.ordenar(List(5, 4, 3, 2, 1)) == List(1, 2, 3, 4, 5))
    assert(obj.ordenar(List(0, -2, 7, -9)) == List(-9, -2, 0, 7))
  }

  test("ordenar la lista invertida la deja como estaba") {
    assert(obj.ordenar(obj.invertir(l)) == l)
    assert(obj.esCreciente(obj.ordenar(List(8, 4, 11, 2))))
  }

  // Punto 4: aplanar una lista anidada

  val anidada: List[Elemento] =
    List(Sub(List(Valor(1), Valor(1))), Valor(2), Sub(List(Valor(3), Sub(List(Valor(5), Valor(8))))))

  test("aplanar la vacía y una lista sin sublistas") {
    assert(obj.aplanar(List()) == List())
    assert(obj.aplanar(List(Valor(7))) == List(7))
    assert(obj.aplanar(List(Valor(4), Valor(8), Valor(15))) == List(4, 8, 15))
  }

  test("aplanar una lista de sublistas de un solo nivel") {
    assert(obj.aplanar(List(Sub(List(Valor(1), Valor(1))), Sub(List(Valor(2))), Sub(List(Valor(3), Valor(5), Valor(8))))) ==
      List(1, 1, 2, 3, 5, 8))
    assert(obj.aplanar(List(Sub(List()), Sub(List(Valor(7))), Sub(List()))) == List(7))
    assert(obj.aplanar(List(Sub(List()), Sub(List()))) == List())
  }

  test("aplanar con sublistas dentro de sublistas") {
    assert(obj.aplanar(anidada) == List(1, 1, 2, 3, 5, 8))
    assert(obj.aplanar(List(Sub(List(Sub(List(Sub(List(Valor(7))))))))) == List(7))
    assert(obj.aplanar(List(Sub(List(Sub(List()))))) == List())
  }

  test("aplanar respeta el orden con la sublista al frente o al final") {
    assert(obj.aplanar(List(Sub(List(Valor(1))), Valor(2))) == List(1, 2))
    assert(obj.aplanar(List(Valor(1), Sub(List(Valor(2), Valor(3))))) == List(1, 2, 3))
    assert(obj.longitud(obj.aplanar(List(Sub(anidada), Sub(anidada)))) == 12)
  }

  // Punto 5: codificación por corridas

  test("corridas de la vacía, de un elemento y de una lista constante") {
    assert(obj.corridas(List()) == List())
    assert(obj.corridas(List(7)) == List(Corrida(7, 1)))
    assert(obj.corridas(List(1, 1, 1)) == List(Corrida(1, 3)))
  }

  test("corridas de una lista con repeticiones consecutivas") {
    assert(obj.corridas(List(1, 1, 2, 3, 3, 3, 2, 2)) ==
      List(Corrida(1, 2), Corrida(2, 1), Corrida(3, 3), Corrida(2, 2)))
  }

  test("corridas solo junta vecinos iguales") {
    assert(obj.corridas(List(1, 2, 1)) == List(Corrida(1, 1), Corrida(2, 1), Corrida(1, 1)))
    assert(obj.corridas(List(-1, -1, 0)) == List(Corrida(-1, 2), Corrida(0, 1)))
  }

  test("expandir repite cada valor tantas veces como dice su corrida") {
    assert(obj.expandir(List()) == List())
    assert(obj.expandir(List(Corrida(1, 2), Corrida(2, 1))) == List(1, 1, 2))
    assert(obj.expandir(List(Corrida(9, 0), Corrida(5, 2))) == List(5, 5))
  }

  test("expandir deshace corridas") {
    assert(obj.expandir(obj.corridas(l)) == l)
    assert(obj.expandir(obj.corridas(List(1, 1, 2, 3, 3, 3, 2, 2))) == List(1, 1, 2, 3, 3, 3, 2, 2))
  }
}
