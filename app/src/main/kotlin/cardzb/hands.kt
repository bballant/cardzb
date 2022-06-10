package cardzb.hands

import cardzb.cards.*

fun<T> window(l: List<T>, idx: Int, size: Int): Pair<List<T>,List<T>>  {
    val slice = l.drop(idx).take(size)
    val win = if (slice.size < size) {
        slice + l.take(size - slice.size)
    } else {
        slice
    }
    return Pair(win, l.filterNot { win.toSet().contains(it) })
}

fun<T> windows(l: List<T>, size: Int): List<Pair<List<T>, List<T>>> =
    (0..l.size-1).map { window(l, it, size)}

fun comb2(l: List<Card>): List<List<Card>> {
    fun innerComb2(
            acc: List<List<Card>>,
            nLeft: List<Card>,
            currCard: Card,
            currLeft: List<Card>
    ): List<List<Card>> {
        return (if (nLeft.isEmpty()) {
            acc
        } else if (currLeft.isEmpty()) {
            innerComb2(
                    acc,
                    nLeft.drop(1),
                    nLeft.first(),
                    nLeft.drop(1)
            )
        } else {
            innerComb2(
                    acc + listOf(listOf(currCard, currLeft.first())),
                    nLeft,
                    currCard,
                    currLeft.drop(1)
            )
        })
    }
    return innerComb2(listOf<List<Card>>(), l.drop(1), l.first(), l.drop(1))
}

fun comboOneToN(c: Card, ls: List<List<Card>>) =
    ls.map{it + listOf(c)}

fun comboN(n: Int, l: List<Card>): List<List<Card>> {
    println(l)
        if (l.size == n) {
            return listOf(l)
        } else {
            return comboOneToN(l.first(), comboN(n, l.drop(1)))
        }
}

fun combinations2(
    arr: List<Card>,
    acc: List<List<Card>>,
    len: Int,
    currStart: Int,
    currRes: List<Card>
): List<List<Card>> {
    if (len == 0) {
        return listOf(currRes)
    } else {
        var accr = acc
        for (i in currStart..arr.size-len) {
            accr = accr + combinations2(arr, acc, len-1, i+1, currRes + arr[i])
        }
        return accr
    }
}

fun combinations2B(nCards: List<Card>, kLen: Int): List<List<Card>> {

    fun combinations2BInner(
        len: Int,
        rem:  List<Card>,
        currRes: List<Card>
    ): List<List<Card>> {
        if (len == 0) {
            return listOf(currRes)
        } else {
            fun remLoop(acc: List<List<Card>>, remInner: List<Card>): List<List<Card>> {
                if (remInner.size < len) {
                  return acc
                } else {
                  val newAcc = acc + combinations2BInner(len-1, remInner.drop(1), currRes + remInner.first())
                  return remLoop(newAcc, remInner.drop(1))
                }
            }
            val foo = remLoop(listOf<List<Card>>(), rem)
            return foo
        }
    }

    return combinations2BInner(kLen, nCards, listOf<Card>())
}

fun combinationsB(
    arr: List<Card>,
    len: Int
): List<List<Card>> {

    fun combinationsJ(lenJ: Int, rem: List<Card>, res: List<Card>): List<List<Card>> {
        fun combinationsK(acc: List<List<Card>>, lenK: Int, remK: List<Card>, resK: List<Card>): List<List<Card>> {
            if (remK.size <= lenK) {
                return acc
            } else {
                return combinationsK(
                    acc + combinationsJ(lenK-1, remK.drop(1), resK + remK.first()),
                    len, remK.drop(1), listOf<Card>())
            }
        }

        if (lenJ == 0) {
            return listOf(res)
        } else {
            return combinationsK(listOf<List<Card>>(), lenJ, rem, res)
        }
    }

    println("starting")
    return combinationsJ(len, arr, listOf<Card>())
}

// fun combos(size: Int, cards: List<Card>): List<List<Card>> {
//     fun combosForCard(
//         acc: List<List<Card>>,
//         c: Card,
//         rest: List<Card>
//     ): List<List<Card>> {
//         if (rest.isEmpty()) {
//             return acc
//         }
//         val newCombo = listOf(c) + rest.take(size - 1)
//         val newRest = rest.drop(size - 1)
//         return combosForCard(acc + listOf(newCombo), c, newRest.drop(1))
//     }
//
//     fun combosForCardLoop(
//         acc: List<List<Card>>,
//         rest: List<Card>
//     ): List<List<Card>> {
//         if (rest.isEmpty()) {
//             return acc
//         } else {
//
//         }
//     }
//
//     return combosForCard(listOf(listOf<Card>()), cards.first(), cards.drop(1))
// }

fun isNOfAKind(n: Int, cards: List<Card>): Boolean =
    cards.size == n && cards.map{it.rank}.toSet().size == 1

fun combosOfN(n: Int, cards: List<Card>): Pair<List<Card>, List<Card>> = TODO()

fun twoOfAKind(a: Card, b: Card): Boolean = a.rank == b.rank

fun threeOfAKind(a: Card, b: Card, c: Card): Boolean =
    listOf(a, b, c).map {it.rank}.toSet().size == 1

fun fourOfAKind(a: Card, b: Card,
                c: Card, d: Card): Boolean =
    listOf(a, b, c, d).map {it.rank}.toSet().size == 1

val someCards: List<Card> =
    listOf(Card(Rank.Ace, Suit.Spades), Card(Rank.King, Suit.Spades))

sealed class Hand(val cards: List<Card>)

data class APair(
    val a: Card,
    val b: Card
): Hand(listOf(a,b))

object SoCool: Hand(someCards)

data class TwoPair(
    val a: APair,
    val b: APair
): Hand(a.cards + b.cards)

data class ThreeOfAKind(
    val a: Card,
    val b: Card,
    val c: Card
): Hand(listOf(a, b, c))

data class FullHouse(
    val a: APair,
    val b: ThreeOfAKind
): Hand(a.cards + b.cards)
