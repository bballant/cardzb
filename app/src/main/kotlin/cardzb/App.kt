package cardzb

import cardzb.playingcards.*

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
  var x = 0
  (1..10000).map {
    val myCards = Deck.fiftyTwoCards.shuffled().take(5)
    val combos = windows(myCards, 4).map{ isNOfAKind(4, it.first) }
    if (combos.any {it}) {
        println(myCards)
        x = x + 1
    }
  }
  println(x)
}
