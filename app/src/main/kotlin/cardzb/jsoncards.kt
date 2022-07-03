package cardzb.jsoncards

import com.google.gson.Gson
import kotlin.io.println
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result


fun foo() {
    val gson = Gson()
    println(gson.toJson(1))
    println(gson.toJson("abcd"))
    println(gson.toJson(Rank.Ace))

    "http://localhost:9200".httpGet().response {
        request, response, result -> {
            println(request)
            println(response)
            println(result)
            }
    }
}

object Deck {
    val ranks: List<Rank> =
            listOf(
                    Rank.Two,
                    Rank.Three,
                    Rank.Four,
                    Rank.Five,
                    Rank.Six,
                    Rank.Seven,
                    Rank.Eight,
                    Rank.Nine,
                    Rank.Ten,
                    Rank.Jack,
                    Rank.King,
                    Rank.Queen,
                    Rank.Ace
            )
    val suits: List<Suit> =
            listOf(
                    Suit.Clubs,
                    Suit.Diamonds,
                    Suit.Hearts,
                    Suit.Spades,
            )
    val fiftyTwoCards: List<Card> =
        suits.flatMap { suit -> ranks.map { rank -> Card(rank, suit) } }
}

val aceOfSpades: Card = Card(Rank.Ace, Suit.Spades)

data class Card(
    val rank: Rank,
) {
    override fun toString() = brief
    val brief = rank.toString() + suit
    companion object {
        fun unicodeCard(card: Card): String {
            val cardNum = card.suit.idx * 16 + card.rank.idx
            val cardUnicode = 0x1F090 + cardNum
            return Character.toString(cardUnicode)
        }
    }
}

abstract class IndexedSym: Comparable<IndexedSym> {
    abstract val idx : Int
    abstract val sym : String
    override fun toString() = sym
    override fun compareTo(other: IndexedSym) = this.idx.compareTo(other.idx)
}

sealed class Rank(
    override val idx: Int,
    override val sym: String = idx.toString()
): IndexedSym() {
    object Ace   : Rank(1, "A")
    object Two   : Rank(2)
    object Three : Rank(3)
    object Four  : Rank(4)
    object Five  : Rank(5)
    object Six   : Rank(6)
    object Seven : Rank(7)
    object Eight : Rank(8)
    object Nine  : Rank(9)
    object Ten   : Rank(10)
    object Jack  : Rank(11, "J")
    object King  : Rank(12, "K")
    object Queen : Rank(13, "Q")
}

sealed class Suit(
    override val idx: Int,
    override val sym: String = idx.toString()
): IndexedSym() {
    object Clubs    : Suit(1, "♣")
    object Diamonds : Suit(2, "♦")
    object Hearts   : Suit(3, "♥")
    object Spades   : Suit(4, "♠")
}
