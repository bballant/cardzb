package cardzb

import cardzb.cards.*
import cardzb.hands.*

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {

//  val res =
//    SoCool.cards.map{
//        card ->
//            when (card.rank) {
//                is Rank.Ace -> "Nice Ace: " + card
//                else -> "Dang " + card
//            }
//        }
//  println(res)

  val someCards = Deck.fiftyTwoCards.shuffled().take(5)

  println(combinationsItr(someCards, listOf<List<Card>>(), 2, 0, listOf<Card>()))
  println(combinations(someCards, 2))

}
