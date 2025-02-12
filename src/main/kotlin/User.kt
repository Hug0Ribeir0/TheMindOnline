package nyx.software

class User constructor(uName: String){
    val userName = uName
    val id : Int
    val gameID
    val cardHand : MutableList<Int> = mutableListOf()

    fun setGame(){

    }

    fun addCard(card:Int){
        cardHand.add(card)
    }
}
