package cardzb

import cardzb.playingcards.*

// sealed class PokerHand(
//     override val rank: Int,
//     override val string: String
// ): RankedString() {
//     object Pair          : PokerHand(1 , "Pair")
//     object TwoPair       : PokerHand(2 , "Two Pair")
//     object ThreeOfAKind  : PokerHand(3 , "Three of a Kind")
//     object Straight      : PokerHand(4 , "Straight")
//     object Flush         : PokerHand(5 , "Flush")
//     object FullHouse     : PokerHand(6 , "Full House")
//     object FourOfAKind   : PokerHand(7 , "Four of a Kind")
//     object StraightFlush : PokerHand(8 , "Straight Flush")
//     object RoyalFlush    : PokerHand(9 , "Royal Flush")
// }
//
// sealed class PH(
//     override val rank: Int,
//     override val string: String
// ) : RankedString()
//
// sealed class OfAKindHand(
//     rank: Int,
//     string: String,
//     val n: Int
// ): PH(rank, string)
//
// sealed class ComboHand(
//     rank: Int,
//     string: String,
//     h1: PH,
//     h2: PH
// ): PH(rank, string)
//
// object PairHand         : OfAKindHand(1 , "Pair", 2)
// object TwoPairHand      : ComboHand(2 , "Two Pair", PairHand, PairHand)
// object ThreeOfAKindHand : OfAKindHand(3 , "Three of a Kind", 3)
// object StraightHand     : PH(4 , "Straight")
// object FlushHand        : PH(5 , "Flush")
// object FullHouseHand    : ComboHand(6 , "Full House", PairHand, ThreeOfAKindHand)
// object FourOfAKindHand  : OfAKindHand(7 , "Four of a Kind", 4)

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

fun isNOfAKind(n: Int, cards: List<Card<Rank,Suit>>): Boolean =
    cards.size == n && cards.map{it.rank}.toSet().size == 1

abstract class Hand(val cards: List<Card<Rank,Suit>>): Comparable<Hand>

class FiveCardStudHand(cards: List<Card<Rank,Suit>>): Hand(cards) {
    override fun compareTo(other: Hand) = cards.size.compareTo(other.cards.size)
}

fun combosOfN(n: Int, cards: List<Card<Rank,Suit>>): Pair<List<Card<Rank,Suit>>, List<Card<Rank,Suit>>> = TODO()

fun twoOfAKind(a: Card<Rank,Suit>, b: Card<Rank,Suit>): Boolean = a.rank == b.rank

fun threeOfAKind(a: Card<Rank,Suit>, b: Card<Rank,Suit>, c: Card<Rank,Suit>): Boolean =
    listOf(a, b, c).map {it.rank}.toSet().size == 1

fun fourOfAKind(a: Card<Rank,Suit>, b: Card<Rank,Suit>, c: Card<Rank,Suit>, d: Card<Rank,Suit>): Boolean =
    listOf(a, b, c, d).map {it.rank}.toSet().size == 1

object Foo {
    sealed class Hand(val cards: List<Card<Rank,Suit>>)
    data class Pair(val a: Card<Rank,Suit>, val b: Card<Rank,Suit>): Hand(listOf(a, b))
    data class TwoPair(val a: Pair, val b: Pair)
    data class ThreeOfAKind(val a: Card<Rank,Suit>, val b: Card<Rank,Suit>, val c: Card<Rank,Suit>): Hand(listOf(a, b, c))
    data class FullHouse(val a: Pair, val b: ThreeOfAKind): Hand(a.cards + b.cards)
}
