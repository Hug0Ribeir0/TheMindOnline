import kotlin.random.Random
package nyx.software

class Game constructor(){
    val deck : MutableList<Int> = (1..100).toMutableList()
    val players : MutableList<User> = mutableListOf()
    val expectedResult : MutableList<Int> = mutableListOf()
    val playedCards : MutableList<Int> = mutableListOf()
    val level : Int = 1





    fun addPlayer(newPlayer: User): Boolean{
        if(!players.contains(newPlayer)) {
            players.add(newPlayer)
            return true
        }

        return false
    }

    fun removePlayer(oldPlayer: User): Boolean{
        players.remove(oldPlayer)
        return true
    }

    fun levelUp(){
        level++
    }

    fun levelDown(){
        level--
    }

    fun resetLevel(){
        level = 1
    }

    fun shuffle(){
        for(i in 1..level){
            for(user in players) {
                if (deck.isEmpty())
                    return -1

                val randomIndex = deck.get(Random.nextInt(deck.size))
                user.addCard(randomIndex)
                expectedResult.add(randomIndex)
                deck.remove(randomIndex)
            }
        }
        expectedResult = expectedResult.sort()
        return 0
    }

    fun playCard(user: User, card: Int){
        if(!players.contains(user) or !players.get(players.indexOf(user)).hasCard(card))
            return -1

        playedCards.add(card)
        players.get(players.indexOf(user)).removeCard(card)

        val gameStatus = checkPlay()

        if (gameStatus == 0)
            endGame(gameStatus)
        else if (gameStatus == 2){
            println("U won")
            endGame(gameStatus)
        }



    }

    private fun checkPlay(){
        for(i in 0..playedCards.size)
            if(playedCards.get(i) != expectedResult.get(i))
                return 0

        if (playedCards.size == expectedResult.size)
            return 2

        return 1

    }

    private fun endGame(status:Int){
        deck = (1..100).toMutableList()
        expectedResult = mutableListOf()
        playedCards = mutableListOf()
        if (status == 2)
            levelUp()
        else if (status == 0)
            resetLevel()
    }

}
