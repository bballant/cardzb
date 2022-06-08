package cardzb

object DeleteMe {
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
        val fiftyTwoCards: List<Card> = suits.flatMap { suit -> ranks.map { rank -> Card(rank, suit) } }
    }

    data class Card(
        val rank: Rank,
        val suit: Suit
    ) {
        override fun toString() = brief
        val unicode = unicodeCard(this)
        val brief = rank.toString() + suit
        companion object {
            fun unicodeCard(card: Card): String {
                val cardNum = card.suit.rank * 16 + card.rank.rank
                val cardUnicode = 0x1F090 + cardNum
                return Character.toString(cardUnicode)
            }
        }
    }

    abstract class RankedString: Comparable<RankedString> {
        abstract val rank : Int
        abstract val string : String
        override fun toString() = string
        override fun compareTo(other: RankedString) = this.rank.compareTo(other.rank)
    }

    sealed class Rank(
        override val rank: Int,
        override val string: String
    ): RankedString() {
        override fun toString() =
            if (this > Rank.Ten || this < Rank.Two) {
                string.take(1)
            } else {
                rank.toString()
            }
        object Ace   : Rank(1,  "Ace")
        object Two   : Rank(2 , "Two")
        object Three : Rank(3 , "Three")
        object Four  : Rank(4 , "Four")
        object Five  : Rank(5 , "Five")
        object Six   : Rank(6 , "Six")
        object Seven : Rank(7 , "Seven")
        object Eight : Rank(8 , "Eight")
        object Nine  : Rank(9 , "Nine")
        object Ten   : Rank(10, "Ten")
        object Jack  : Rank(11, "Jack")
        object King  : Rank(12, "King")
        object Queen : Rank(13, "Queen")
    }

    sealed class Suit(
        override val rank: Int,
        override val string: String,
        val symbol: Char
    ): RankedString() {
        override fun toString() = symbol.toString()
        object Clubs    : Suit(1 , "Clubs", '♣')
        object Diamonds : Suit(2 , "Diamonds", '♦')
        object Hearts   : Suit(3 , "Hearts", '♥')
        object Spades   : Suit(4 , "Spades", '♠')
    }
}
