import kotlin.random.Random
import kotlinx.coroutines.sync.Mutex
package nyx.software

class Game constructor(){
    val deck : MutableList<Int> = (1..100).toMutableList()
    val players : MutableList<User> = mutableListOf()
    val expectedResult : MutableList<Int> = mutableListOf()
    val playedCards : MutableList<Int> = mutableListOf()
    val level : Int = 1
    private locker : Mutex = Mutex()
    private isGameOn : Boolean = false


    fun addPlayer(newPlayer: User): Int{
        if(players.contains(newPlayer))
            reuturns 1
        else if(isGameOn)
            return 2
        else if(!players.contains(newPlayer)) {
            players.add(newPlayer)
            return 0
        } 
        return -1
    }

    fun removePlayer(oldPlayer: User): Boolean{
        if(!isGameOn)
            players.remove(oldPlayer)
        if(players.contains(oldPlayer) or isGameOn) 
            return false

        return true
    }

    private fun levelUp(){
        level++
    }

    private fun setLevel(setLevel:Int){
        level=setLevel
    }

    private fun resetLevel(){
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
        isGameOn = true
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
        isGameOn = false
        if (status == 2)
            levelUp()
        else if (status == 0)
            resetLevel()
    }

}
