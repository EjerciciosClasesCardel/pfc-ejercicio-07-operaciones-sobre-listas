package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class EjercicioTest extends AnyFunSuite {
  val obj = new Ejercicio()
  val l = List(4, 8, 15, 16, 23, 42)

  test("Longitud") {
    assert(obj.longitud(List()) == 0)
    assert(obj.longitud(List(7)) == 1)
    assert(obj.longitud(l) == 6)
  }

  test("Invertir") {
    assert(obj.invertir(List()) == List())
    assert(obj.invertir(List(7)) == List(7))
    assert(obj.invertir(l) == List(42, 23, 16, 15, 8, 4))
  }

  test("Invertir dos veces devuelve la lista original") {
    assert(obj.invertir(obj.invertir(l)) == l)
  }

  test("Concatenar") {
    assert(obj.concatenar(List(), List()) == List())
    assert(obj.concatenar(List(), l) == l)
    assert(obj.concatenar(l, List()) == l)
    assert(obj.concatenar(List(1, 2), List(3, 4)) == List(1, 2, 3, 4))
  }

  test("La longitud de la concatenación es la suma de las longitudes") {
    assert(obj.longitud(obj.concatenar(l, l)) == 12)
  }

  test("Último") {
    assert(obj.ultimo(List(7)) == 7)
    assert(obj.ultimo(l) == 42)
  }

  test("Último de la lista vacía falla") {
    assertThrows[NoSuchElementException](obj.ultimo(List()))
  }

  test("Tomar") {
    assert(obj.tomar(l, 0) == List())
    assert(obj.tomar(l, 3) == List(4, 8, 15))
    assert(obj.tomar(l, 6) == l)
    assert(obj.tomar(l, 100) == l)
    assert(obj.tomar(List(), 3) == List())
  }

  test("Soltar") {
    assert(obj.soltar(l, 0) == l)
    assert(obj.soltar(l, 3) == List(16, 23, 42))
    assert(obj.soltar(l, 6) == List())
    assert(obj.soltar(l, 100) == List())
  }

  test("Tomar y soltar reconstruyen la lista") {
    assert(obj.concatenar(obj.tomar(l, 2), obj.soltar(l, 2)) == l)
  }

  test("En posición") {
    assert(obj.enPosicion(l, 0) == 4)
    assert(obj.enPosicion(l, 5) == 42)
    assert(obj.enPosicion(l, 3) == 16)
  }

  test("Una posición fuera de rango falla") {
    assertThrows[IndexOutOfBoundsException](obj.enPosicion(l, 6))
    assertThrows[IndexOutOfBoundsException](obj.enPosicion(l, -1))
  }
}
