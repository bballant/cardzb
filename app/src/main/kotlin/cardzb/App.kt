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


  println(someCards)
  println(ofAKind(someCards))
  println(listOf(false, false).any{it})

  val twoThreeFour =
    (2..4).map{combinations(someCards, it)}
        .map{combs -> combs.map{ofAKind(it)}.any{it}}

  println(twoThreeFour)
}
