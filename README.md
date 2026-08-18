# Ejercicio 7 — Operaciones sobre listas

Fundamentos de Programación Funcional y Concurrente
Escuela de Ingeniería de Sistemas y Computación, Universidad del Valle
Carlos Andrés Delgado Saavedra

Escribir a mano las operaciones que la biblioteca ya trae. La gracia no es
tener las funciones: es que después de escribirlas uno sabe qué cuesta cada
una.

## La forma de una lista

Una lista es la lista vacía o un elemento seguido de otra lista. Las
funciones que la recorren siguen esa misma forma: un caso para `Nil` y otro
que separa cabeza y cola.

```scala
def longitud(l: List[Int]): Int = l match {
  case Nil     => 0
  case _ :: xs => 1 + longitud(xs)
}
```

También se puede escribir con las primitivas `head`, `tail` e `isEmpty`:

```scala
def longitud(l: List[Int]): Int =
  if (l.isEmpty) 0 else 1 + longitud(l.tail)
```

Las dos formas valen. La de `match` se lee mejor cuando hay varios casos.

## Lo que hay que resolver

Todo va en `app/src/main/scala/taller/Ejercicio.scala`. **No use `length`,
`reverse`, `++`, `last`, `take`, `drop` ni `apply` de la biblioteca**: son
justamente las que hay que escribir.

| Función | Qué devuelve |
|---|---|
| `longitud(l)` | cuántos elementos tiene |
| `invertir(l)` | los elementos en orden inverso |
| `concatenar(l1, l2)` | los de `l1` seguidos de los de `l2` |
| `ultimo(l)` | el último elemento |
| `tomar(l, n)` | los primeros `n` elementos |
| `soltar(l, n)` | la lista sin los primeros `n` |
| `enPosicion(l, n)` | el elemento en la posición `n`, desde cero |

### Ejemplos

Con `l = List(4, 8, 15, 16, 23, 42)`:

| Llamada | Resultado |
|---|---|
| `longitud(l)` | 6 |
| `invertir(l)` | `List(42, 23, 16, 15, 8, 4)` |
| `concatenar(List(1,2), List(3,4))` | `List(1, 2, 3, 4)` |
| `ultimo(l)` | 42 |
| `tomar(l, 3)` | `List(4, 8, 15)` |
| `tomar(l, 100)` | la lista entera |
| `soltar(l, 3)` | `List(16, 23, 42)` |
| `soltar(l, 100)` | `List()` |
| `enPosicion(l, 3)` | 16 |

### Los casos que fallan

`ultimo` de una lista vacía lanza `NoSuchElementException`, y `enPosicion`
con un índice fuera de rango lanza `IndexOutOfBoundsException`. Los dos casos
ya vienen escritos en el esqueleto; lo que falta es el resto.

`tomar` y `soltar` no fallan nunca: con un `n` mayor que la longitud
devuelven la lista entera y la lista vacía, respectivamente.

## Las propiedades que también se comprueban

Tres pruebas no miran un caso concreto sino una relación que debe cumplirse
siempre:

- invertir dos veces devuelve la lista original,
- la longitud de una concatenación es la suma de las longitudes,
- concatenar `tomar(l, n)` con `soltar(l, n)` reconstruye `l`.

Sirven para detectar una implementación que acierte los ejemplos y falle en
lo demás.

## Cómo está organizado el proyecto

```
app/src/main/scala/taller/
    App.scala          programa de arranque
    Ejercicio.scala    aquí van las siete funciones

app/src/test/scala/taller/
    AppSuite.scala        comprueba que el entorno quedó bien
    EjercicioTest.scala   los casos de arriba
```

Su código va en `main`. Las pruebas viven aparte y no se tocan.

## Cómo se ejecuta

```bash
./gradlew test    # corre las pruebas
```

Las pruebas arrancan en rojo y el trabajo es ponerlas en verde. El informe
completo queda en `app/build/reports/tests/test/index.html`.

## Cómo se trabaja

1. Haga fork de este repositorio.
2. En su fork, abra la pestaña **Actions** y habilítelas. GitHub las deja
   desactivadas en las copias hasta que el dueño lo confirme.
3. Clone, resuelva, haga commit y suba a `main`.
4. Verifique en **Actions** que la última ejecución quedó en verde.

## Restricciones

Este curso trabaja sin estado mutable: nada de `var`, `while`, `return` ni
variables que cambien. El resultado correcto por el camino equivocado no
cuenta como resultado correcto.
