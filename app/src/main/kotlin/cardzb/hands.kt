package cardzb.hands

import cardzb.cards.*

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

fun combinations(
    nCards: List<Card>,
    kLen: Int
): List<List<Card>>{
    fun combinations_(
        len: Int,
        rem:  List<Card>,
        currRes: List<Card>
    ): List<List<Card>> {
        fun loop(
            acc: List<List<Card>>,
            remInner: List<Card>
        ): List<List<Card>> {
            if (remInner.size < len) {
                return acc
            } else {
                return loop(
                    acc + combinations_(len-1, remInner.drop(1), currRes + remInner.first()),
                    remInner.drop(1)
                )
            }
        }
        if (len == 0) {
            return listOf(currRes)
        } else {
            return loop(listOf<List<Card>>(), rem)
        }
    }
    return combinations_(kLen, nCards, listOf<Card>())
}

fun combinationsItr(
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
            accr = accr + combinationsItr(arr, acc, len-1, i+1, currRes + arr[i])
        }
        return accr
    }
}

fun isNOfAKind(n: Int, cards: List<Card>): Boolean =
    cards.size == n && cards.map{it.rank}.toSet().size == 1

fun twoOfAKind(a: Card, b: Card): Boolean = a.rank == b.rank

fun threeOfAKind(a: Card, b: Card, c: Card): Boolean =
    listOf(a, b, c).map {it.rank}.toSet().size == 1

fun fourOfAKind(a: Card, b: Card,
                c: Card, d: Card): Boolean =
    listOf(a, b, c, d).map {it.rank}.toSet().size == 1

val someCards: List<Card> =
    listOf(Card(Rank.Ace, Suit.Spades), Card(Rank.King, Suit.Spades))

