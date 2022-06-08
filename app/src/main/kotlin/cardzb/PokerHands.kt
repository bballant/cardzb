package cardzb

import cardzb.playingcards.*

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

fun combosOfN(n: Int, cards: List<Card<Rank,Suit>>): Pair<List<Card<Rank,Suit>>, List<Card<Rank,Suit>>> = TODO()

fun twoOfAKind(a: Card<Rank,Suit>, b: Card<Rank,Suit>): Boolean = a.rank == b.rank

fun threeOfAKind(a: Card<Rank,Suit>, b: Card<Rank,Suit>, c: Card<Rank,Suit>): Boolean =
    listOf(a, b, c).map {it.rank}.toSet().size == 1

fun fourOfAKind(a: Card<Rank,Suit>, b: Card<Rank,Suit>,
                c: Card<Rank,Suit>, d: Card<Rank,Suit>): Boolean =
    listOf(a, b, c, d).map {it.rank}.toSet().size == 1


val someCards: List<Card<Rank, Suit>> =
    listOf(Card(Rank.Ace, Suit.Spades), Card(Rank.King, Suit.Spades))

sealed class Handy(val cards: List<Card<Rank, Suit>>)


data class APairy<R: Rank>(
    val a: Card<R,Suit>,
    val b: Card<R,Suit>
): Handy(listOf(a as Card<Rank, Suit>, b as Card<Rank, Suit>))

//class SoCool<Rank>(): Handy<Rank>(someCards)

sealed class Hand()

data class APair<R: Rank>(
    val a: Card<R,Suit>,
    val b: Card<R,Suit>
//): Hand(listOf(a, b))
): Hand()

data class TwoPair<R1: Rank, R2: Rank>(
    val a: APair<R1>,
    val b: APair<R2>
//): Hand(a.cards + b.cards)
): Hand()

data class ThreeOfAKind<R: Rank>(
    val a: Card<R,Suit>,
    val b: Card<R,Suit>,
    val c: Card<R,Suit>
//): Hand(listOf(a, b, c))
): Hand()

data class FullHouse<R1: Rank, R2: Rank>(
    val a: APair<R1>,
    val b: ThreeOfAKind<R2>
//): Hand(a.cards + b.cards)
): Hand()

//fun mkPair(a: Card<R, Suit>, b: Card<R, Suite>): APair<R> = TODO()
