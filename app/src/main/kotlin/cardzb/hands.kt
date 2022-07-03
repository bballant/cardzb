package cardzb.hands

import cardzb.cards.*
import java.util.Optional

sealed class PokerHand(
    override val idx: Int
): Indexed() {
    object AHighCard : PokerHand(1)
    object APair : PokerHand(2)
    object TwoPair : PokerHand(3)
    object ThreeOfAKind : PokerHand(4)
    object Straight : PokerHand(5)
    object Flush : PokerHand(6)
    object FullHouse : PokerHand(7)
    object FourOfAKind : PokerHand(8)
    object StraightFlush : PokerHand(9)
    object RoyalFlush : PokerHand(10)
}

data class Scored(val cards: List<Card>, val pokerHand: PokerHand)

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
                    acc + combinations_(
                        len-1,
                        remInner.drop(1),
                        currRes + remInner.first()
                    ),
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

fun sameRank(cards: List<Card>): Boolean = cards.map{it.rank}.toSet().size == 1

fun isNOfAKind(n: Int, cards: List<Card>): Boolean =
    cards.size == n && sameRank(cards)

val someCards: List<Card> =
    listOf(Card(Rank.Ace, Suit.Spades), Card(Rank.King, Suit.Spades))

fun flushSuit(cards: List<Card>): Optional<Suit> {
    if (cards.size != 5) return Optional.empty()

    val suitSet = cards.map{it.suit}.toSet()
    if (suitSet.size == 1) {
        return Optional.of(suitSet.first())
    } else {
        return Optional.empty()
    }
}

fun straightHighRank(cards: List<Card>): Optional<Rank> {
    if (cards.size != 5) return Optional.empty()

    val cardsSorted = cards.sortedByDescending { it.rank }
    val pairs = cardsSorted
        .map{it.rank.idx}
        .zip(cardsSorted.first().rank.idx..cardsSorted.last().rank.idx)
    val isStraight = pairs.all{it.first == it.second}
    if (isStraight) {
        return Optional.of(cardsSorted.first().rank)
    } else {
        return Optional.empty()
    }
}

//fun highestPokerHand(cards: List<Card>): Scored {
//    val combs = combinations(cards, 5)
//    val flushes = combs.map{flushSuit(it)}.filter{isPresent}
//
//    return TODO()
//}
