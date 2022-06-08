package cardzb.playingcards

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
    val fiftyTwoCards: List<Card<Rank, Suit>> =
        suits.flatMap { suit -> ranks.map { rank -> Card(rank, suit) } }
}

val aceOfSpades: Card<Rank.Ace, Suit.Spades> = Card(Rank.Ace, Suit.Spades)

data class Card<R: Rank, S: Suit>(
    val rank: R,
    val suit: S
) {
    override fun toString() = brief
    val brief = rank.toString() + suit
    companion object {
        fun unicodeCard(card: Card<Rank, Suit>): String {
            val cardNum = card.suit.idx * 16 + card.rank.idx
            val cardUnicode = 0x1F090 + cardNum
            return Character.toString(cardUnicode)
        }
    }
}

abstract class Indexed: Comparable<Indexed> {
    abstract val idx : Int
    override fun toString() = idx.toString()
    override fun compareTo(other: Indexed) = this.idx.compareTo(other.idx)
}

sealed class Rank(
    override val idx: Int
): Indexed() {
    override fun toString() =
        when (this) {
            is Ace   -> "A"
            is Jack  -> "J"
            is King  -> "K"
            is Queen -> "Q"
            else -> idx.toString()
        }

    object Ace   : Rank(1)
    object Two   : Rank(2)
    object Three : Rank(3)
    object Four  : Rank(4)
    object Five  : Rank(5)
    object Six   : Rank(6)
    object Seven : Rank(7)
    object Eight : Rank(8)
    object Nine  : Rank(9)
    object Ten   : Rank(10)
    object Jack  : Rank(11)
    object King  : Rank(12)
    object Queen : Rank(13)
}

sealed class Suit(
    override val idx: Int
): Indexed() {
    override fun toString() =
        "♣♦♥♠".get(idx - 1).toString()
    object Clubs    : Suit(1)
    object Diamonds : Suit(2)
    object Hearts   : Suit(3)
    object Spades   : Suit(4)
}
