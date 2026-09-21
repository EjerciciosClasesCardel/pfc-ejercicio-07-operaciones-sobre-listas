# Ejercicio 7 — Operaciones sobre listas

Fundamentos de Programación Funcional y Concurrente
Escuela de Ingeniería de Sistemas y Computación, Universidad del Valle
Carlos Andrés Delgado Saavedra

Cinco puntos sobre listas. Primero, las operaciones que la biblioteca ya
trae, escritas a mano con `head`, `tail` e `isEmpty`; después las mismas
ideas con `match` y los patrones `Nil` y `x :: xs`; y con eso en la mano, el
ordenamiento por inserción, aplanar una lista con sublistas a cualquier
profundidad y una codificación por corridas que se deshace con su inversa. La gracia no es tener las
funciones: es que después de escribirlas uno sabe qué cuesta cada una.

## La forma de una lista

Una lista es la lista vacía o un elemento seguido de otra lista. Las
funciones que la recorren siguen esa misma forma: un caso para la lista
vacía y otro que separa cabeza y cola.

Con las primitivas `head`, `tail` e `isEmpty`:

```scala
def longitud(l: List[Int]): Int =
  if (l.isEmpty) 0 else 1 + longitud(l.tail)
```

Con `match` y los dos patrones de listas:

```scala
def longitud(l: List[Int]): Int = l match {
  case Nil     => 0
  case _ :: xs => 1 + longitud(xs)
}
```

Las dos formas valen y las dos se usan en este ejercicio. La de `match`
saca cabeza y cola sin llamar a `head` ni a `tail`, y admite patrones más
precisos: `x :: Nil` es una lista de un solo elemento y `x :: y :: resto`
una de al menos dos.

Una `case class` también es un patrón, y un patrón puede ir dentro de otro.
En los dos últimos puntos se usan estas:

```scala
sealed trait Elemento
case class Valor(n: Int) extends Elemento
case class Sub(elementos: List[Elemento]) extends Elemento

case class Corrida(valor: Int, veces: Int)
```

`case Corrida(v, n) :: resto` saca en una sola línea el valor, el conteo y lo
que queda de la lista, y `case Sub(xs) :: resto` distingue una cabeza que es
sublista de una que es un número.

## Lo que hay que resolver

Todo va en `app/src/main/scala/taller/Ejercicio.scala`. En los ejemplos,
`l` es `List(4, 8, 15, 16, 23, 42)`.

### Punto 1: las operaciones básicas con `if/else`

```scala
def longitud(l: List[Int]): Int
def invertir(l: List[Int]): List[Int]
def concatenar(l1: List[Int], l2: List[Int]): List[Int]
def ultimo(l: List[Int]): Int
def tomar(l: List[Int], n: Int): List[Int]
def soltar(l: List[Int], n: Int): List[Int]
def enPosicion(l: List[Int], n: Int): Int
```

Las siete se escriben con recursión sobre la lista, `if/else` y las
primitivas `head`, `tail` e `isEmpty`. **No use `length`, `reverse`, `++`,
`last`, `take`, `drop` ni `apply` de la biblioteca**: son justamente las que
hay que escribir.

| Función | Qué devuelve |
|---|---|
| `longitud(l)` | cuántos elementos tiene |
| `invertir(l)` | los elementos en orden inverso |
| `concatenar(l1, l2)` | los de `l1` seguidos de los de `l2` |
| `ultimo(l)` | el último elemento |
| `tomar(l, n)` | los primeros `n` elementos |
| `soltar(l, n)` | la lista sin los primeros `n` |
| `enPosicion(l, n)` | el elemento en la posición `n`, desde cero |

| Llamada | Resultado |
|---|---|
| `longitud(List())` | 0 |
| `longitud(l)` | 6 |
| `invertir(l)` | `List(42, 23, 16, 15, 8, 4)` |
| `concatenar(List(1, 2), List(3, 4))` | `List(1, 2, 3, 4)` |
| `concatenar(List(), l)` | `List(4, 8, 15, 16, 23, 42)` |
| `ultimo(l)` | 42 |
| `tomar(l, 3)` | `List(4, 8, 15)` |
| `tomar(l, 100)` | `List(4, 8, 15, 16, 23, 42)` |
| `tomar(List(), 3)` | `List()` |
| `soltar(l, 3)` | `List(16, 23, 42)` |
| `soltar(l, 100)` | `List()` |
| `enPosicion(l, 0)` | 4 |
| `enPosicion(l, 3)` | 16 |

`ultimo` de una lista vacía lanza `NoSuchElementException`, y `enPosicion`
con un índice fuera de rango lanza `IndexOutOfBoundsException`. Los dos
casos ya vienen escritos en el esqueleto; lo que falta es el resto. `tomar`
y `soltar` no fallan nunca: con un `n` mayor que la longitud devuelven la
lista entera y la lista vacía, respectivamente.

Tres pruebas no miran un caso concreto sino una relación que se cumple
siempre: invertir dos veces devuelve la lista original, la longitud de una
concatenación es la suma de las longitudes, y concatenar `tomar(l, n)` con
`soltar(l, n)` reconstruye `l`. Sirven para detectar una implementación que
acierte los ejemplos y falle en lo demás.

### Punto 2: las mismas ideas con `match`

```scala
def sumar(l: List[Int]): Int
def contiene(l: List[Int], e: Int): Boolean
def inicio(l: List[Int]): List[Int]
def esCreciente(l: List[Int]): Boolean
```

Estas cuatro van con `l match` y los patrones `Nil`, `x :: xs`, `_ :: Nil` y
`x :: y :: resto`. Nada de `head`, `tail` ni `isEmpty` en este punto, y
tampoco `sum`, `contains` ni `init` de la biblioteca.

- `sumar` suma los elementos; la lista vacía suma 0.
- `contiene` dice si `e` aparece en la lista.
- `inicio` es la lista sin su último elemento, la pareja de `ultimo`. Con
  la lista vacía lanza `NoSuchElementException`, y con una lista de un solo
  elemento devuelve `List()`: ese es el caso que necesita el patrón
  `_ :: Nil`.
- `esCreciente` dice si cada elemento es estrictamente menor que el
  siguiente. Una lista de cero o un elemento es creciente. Hay que comparar
  cada elemento con su vecino, y el vecino a su vez con el que sigue: en
  `List(1, 3, 2, 4)` los pares 1 < 3 y 2 < 4 se cumplen y aun así la lista
  no es creciente, porque 3 y 2 también son vecinos.

| Llamada | Resultado |
|---|---|
| `sumar(List())` | 0 |
| `sumar(List(7))` | 7 |
| `sumar(l)` | 108 |
| `sumar(List(-3, 3, -5))` | -5 |
| `contiene(l, 4)` | `true` |
| `contiene(l, 42)` | `true` |
| `contiene(l, 5)` | `false` |
| `contiene(List(), 1)` | `false` |
| `inicio(List(7))` | `List()` |
| `inicio(l)` | `List(4, 8, 15, 16, 23)` |
| `esCreciente(List())` | `true` |
| `esCreciente(List(5))` | `true` |
| `esCreciente(List(1, 2))` | `true` |
| `esCreciente(l)` | `true` |
| `esCreciente(List(2, 1))` | `false` |
| `esCreciente(List(3, 3))` | `false` |
| `esCreciente(List(1, 3, 2, 4))` | `false` |
| `esCreciente(List(1, 2, 3, 3))` | `false` |

### Punto 3: ordenamiento por inserción

```scala
def insertar(x: Int, l: List[Int]): List[Int]
def ordenar(l: List[Int]): List[Int]
```

`insertar(x, l)` recibe una lista **ya ordenada** de menor a mayor y
devuelve la lista con `x` puesto en su lugar. Si `x` ya está en la lista, el
nuevo queda junto a su igual. `ordenar(l)` ordena cualquier lista: la lista
vacía ya está ordenada, y una lista con cabeza y cola se ordena ordenando la
cola e insertando la cabeza donde va. `ordenar` se escribe con `insertar`;
`sorted` y `sortWith` de la biblioteca no se usan.

| Llamada | Resultado |
|---|---|
| `insertar(5, List())` | `List(5)` |
| `insertar(1, List(2, 7, 10))` | `List(1, 2, 7, 10)` |
| `insertar(4, List(2, 7, 10))` | `List(2, 4, 7, 10)` |
| `insertar(12, List(2, 7, 10))` | `List(2, 7, 10, 12)` |
| `insertar(7, List(2, 7, 10))` | `List(2, 7, 7, 10)` |
| `insertar(-3, List(-5, 0))` | `List(-5, -3, 0)` |
| `ordenar(List())` | `List()` |
| `ordenar(List(8, 4, 11, 2))` | `List(2, 4, 8, 11)` |
| `ordenar(List(3, 1, 4, 1, 5, 9, 2, 6))` | `List(1, 1, 2, 3, 4, 5, 6, 9)` |
| `ordenar(List(1, 2, 3))` | `List(1, 2, 3)` |
| `ordenar(List(5, 4, 3, 2, 1))` | `List(1, 2, 3, 4, 5)` |
| `ordenar(List(0, -2, 7, -9))` | `List(-9, -2, 0, 7)` |
| `ordenar(invertir(l))` | `List(4, 8, 15, 16, 23, 42)` |

La última fila junta dos puntos: `l` ya viene ordenada, así que ordenar su
inversa tiene que devolverla tal cual. Otra prueba comprueba que
`esCreciente(ordenar(List(8, 4, 11, 2)))` es `true`.

### Punto 4: aplanar una lista anidada

```scala
sealed trait Elemento
case class Valor(n: Int) extends Elemento
case class Sub(elementos: List[Elemento]) extends Elemento

def aplanar(l: List[Elemento]): List[Int]
```

Una lista anidada tiene números y sublistas, y las sublistas pueden tener a
su vez sublistas, sin límite de profundidad. En clase se escribió sobre
`List[Any]`, que admite cualquier cosa adentro y por eso obliga a preguntar
por el tipo de cada cabeza. Aquí la lista se describe con un tipo propio:
cada elemento es un `Valor(n)` o un `Sub(elementos)`, y el compilador sabe
qué hay en cada posición. La lista de clase
`List(List(1, 1), 2, List(3, List(5, 8)))` se escribe así, y en los ejemplos
se llama `anidada`:

```scala
List(Sub(List(Valor(1), Valor(1))), Valor(2), Sub(List(Valor(3), Sub(List(Valor(5), Valor(8))))))
```

`aplanar` devuelve todos los números en una sola lista, en el orden en que
aparecen leyendo de izquierda a derecha y entrando en cada sublista. Las
sublistas vacías no aportan nada. Se escribe con `match`, con un patrón para
cada forma de la cabeza, y con `concatenar` del punto 1; `flatten` y `++` de
la biblioteca no se usan.

| Llamada | Resultado |
|---|---|
| `aplanar(List())` | `List()` |
| `aplanar(List(Valor(7)))` | `List(7)` |
| `aplanar(List(Valor(4), Valor(8), Valor(15)))` | `List(4, 8, 15)` |
| `aplanar(List(Sub(List(Valor(1), Valor(1))), Sub(List(Valor(2))), Sub(List(Valor(3), Valor(5), Valor(8)))))` | `List(1, 1, 2, 3, 5, 8)` |
| `aplanar(List(Sub(List()), Sub(List(Valor(7))), Sub(List())))` | `List(7)` |
| `aplanar(List(Sub(List()), Sub(List())))` | `List()` |
| `aplanar(anidada)` | `List(1, 1, 2, 3, 5, 8)` |
| `aplanar(List(Sub(List(Sub(List(Sub(List(Valor(7)))))))))` | `List(7)` |
| `aplanar(List(Sub(List(Sub(List())))))` | `List()` |
| `aplanar(List(Sub(List(Valor(1))), Valor(2)))` | `List(1, 2)` |
| `aplanar(List(Valor(1), Sub(List(Valor(2), Valor(3)))))` | `List(1, 2, 3)` |
| `longitud(aplanar(List(Sub(anidada), Sub(anidada))))` | 12 |

Las filas de un solo nivel las acierta también una versión que solo abre la
primera capa de sublistas; `anidada` y la lista con tres `Sub` uno dentro de
otro son las que la separan de la correcta.

### Punto 5: codificación por corridas

```scala
case class Corrida(valor: Int, veces: Int)

def corridas(l: List[Int]): List[Corrida]
def expandir(c: List[Corrida]): List[Int]
```

Una corrida es un valor y cuántas veces seguidas aparece. `corridas(l)`
resume la lista como sus corridas, en el orden en que aparecen:
`List(1, 1, 2, 3, 3, 3, 2, 2)` tiene dos unos, un dos, tres treses y dos
doses. Solo se juntan vecinos iguales: el 2 del medio y los dos 2 del final
son corridas distintas. La pregunta que hay que responder es qué se hace con
la cabeza `x` cuando ya se tienen las corridas de la cola `xs`.

`expandir(c)` hace el camino de vuelta: repite cada valor tantas veces como
dice su corrida. Una corrida con cero veces no aporta elementos. Para
cualquier lista, `expandir(corridas(l))` es la misma `l`, y una de las
pruebas comprueba justamente eso.

| Llamada | Resultado |
|---|---|
| `corridas(List())` | `List()` |
| `corridas(List(7))` | `List(Corrida(7, 1))` |
| `corridas(List(1, 1, 1))` | `List(Corrida(1, 3))` |
| `corridas(List(1, 1, 2, 3, 3, 3, 2, 2))` | `List(Corrida(1, 2), Corrida(2, 1), Corrida(3, 3), Corrida(2, 2))` |
| `corridas(List(1, 2, 1))` | `List(Corrida(1, 1), Corrida(2, 1), Corrida(1, 1))` |
| `corridas(List(-1, -1, 0))` | `List(Corrida(-1, 2), Corrida(0, 1))` |
| `expandir(List())` | `List()` |
| `expandir(List(Corrida(1, 2), Corrida(2, 1)))` | `List(1, 1, 2)` |
| `expandir(List(Corrida(9, 0), Corrida(5, 2)))` | `List(5, 5)` |
| `expandir(corridas(l))` | `List(4, 8, 15, 16, 23, 42)` |

## Lo que la biblioteca ya trae

Cada función de este ejercicio tiene su equivalente en `List`. No se usan
aquí, porque son lo que hay que escribir, pero conviene conocerlas: sirven
para comprobar a mano un resultado en la consola de Scala.

| Escrita a mano | En la biblioteca |
|---|---|
| `longitud(l)` | `l.length` |
| `invertir(l)` | `l.reverse` |
| `concatenar(l1, l2)` | `l1 ++ l2` |
| `ultimo(l)` | `l.last` |
| `inicio(l)` | `l.init` |
| `tomar(l, n)` | `l.take(n)` |
| `soltar(l, n)` | `l.drop(n)` |
| `enPosicion(l, n)` | `l(n)` |
| `sumar(l)` | `l.sum` |
| `contiene(l, e)` | `l.contains(e)` |
| `ordenar(l)` | `l.sorted` |

`aplanar` no tiene equivalente directo: `flatten` quita un solo nivel de
sublistas y aquí puede haber varios.

Lo único de la biblioteca que sí se usa son los dos constructores, `Nil` y
`::`, y `List(...)` para escribir una lista de una vez.

## Cómo está organizado el proyecto

```
app/src/main/scala/taller/
    App.scala          programa de arranque
    Ejercicio.scala    los cinco puntos y los tipos Elemento y Corrida

app/src/test/scala/taller/
    AppSuite.scala        comprueba que el entorno quedó bien
    EjercicioTest.scala   los casos de las cinco tablas
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
