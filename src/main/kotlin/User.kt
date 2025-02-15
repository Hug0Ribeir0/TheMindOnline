package nyx.software

class User constructor(uName: String, userId: String){
    val userName = uName
    val id : Int = userId
    val gameID
    val cardHand : MutableList<Int> = mutableListOf()

    fun setGame(){

    }

    fun addCard(card:Int){
        cardHand.add(card)
    }

    fun removeCard(card:Int):Int{
        if(cardHand.lenght < 1 or !cardHand.contains(card))
            return -1

        cardHand.remove(card)
        return 0
    }

    fun hasCard(hasCard:Int):Boolean{
        if(cardHand.contains(hasCard))
            return true

        return false
    }
}
