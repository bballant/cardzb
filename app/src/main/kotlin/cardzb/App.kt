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
  // var x = 0
  // (1..10000).map {
  //   val myCards = Deck.fiftyTwoCards.shuffled().take(5)
  //   val combos = windows(myCards, 4).map{ isNOfAKind(4, it.first) }
  //   if (combos.any {it}) {
  //       println(myCards)
  //       x = x + 1
  //   }
  // }
  // println(x)

  val res =
    SoCool.cards.map{
        card ->
            when (card.rank) {
                is Rank.Ace -> "Nice Ace: " + card
                else -> "Dang " + card
            }
        }
  println(res)

  val someCards = Deck.fiftyTwoCards.shuffled().take(5)
  //val allCombos = windows(someCards, 2).map{it.first}
  //val allPairs = allCombos.map{APair(it.first(), it.drop(1).first())}
  //println(allPairs)

  //println(someCards)
  println(comb2(someCards))
  //println(comboN(2, someCards))
  println(combinations2(someCards, listOf<List<Card>>(), 2, 0, listOf<Card>()))
  println(combinationsB(someCards, 2))
  println(combinations2B(someCards, 2))

}
