interface WebContent {
    fun getGamer(): List<Gamer>
    fun getGamer(id: Int) : Gamer
    fun putGamer(value: Gamer) : Gamer
    fun deleteGamer(id: Int): Gamer

}