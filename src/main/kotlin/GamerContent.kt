open class GamerContent : WebContent {

    val Gamer1 = Gamer(1,"Tony", 100)
    val Gamer2 = Gamer(2,"Mark",125)
    val Gamer3 = Gamer(3,"Hans",155)
    val Gamer4 = Gamer(4,"Jamal",170)
    val Gamer5 = Gamer(5,"Mike",200)


    val myList = mutableListOf<Gamer>(Gamer1,Gamer2,Gamer3, Gamer4, Gamer5)
    override fun getGamer(): List<Gamer> {
        return myList
    }

    override fun getGamer(id: Int): Gamer {
       myList.forEach { g ->
           if (g.id == id) {
               return g;
           }
       }

        return Gamer(0, "Not found", 0);
    }

    override fun putGamer(value: Gamer): Gamer {
        myList.add(value);
        return value;
    }

    override fun deleteGamer(id: Int): Gamer {
        var deletingGamer = Gamer(0, "Not found", 0)
        for (gamer in myList) {
            if(gamer.id == id){
                myList.remove(gamer)
                deletingGamer = gamer
            }
        }
        return deletingGamer
    }

}