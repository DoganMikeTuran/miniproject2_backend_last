class JsonConverterTool() {

    fun gamerToJson(gamer: Gamer) : String {

        var myString = "... personen sagde \"hej med dig\""

        var jsonTemplate : String = "{ \"id\":{id}, \"nickname\":\"{nickname}\", \"score\":{score}}"
        jsonTemplate = jsonTemplate.replace("{id}", gamer.id.toString());
        jsonTemplate = jsonTemplate.replace("{nickname}", gamer.nickname.toString());
        jsonTemplate = jsonTemplate.replace("{score}", gamer.score.toString());
        return jsonTemplate;
    }

    fun gamersListToJson(gamersList : List<Gamer>) : String {
        var jsonArrayTemplate = "{ \"Gamers\" : [\"{listContent}\"]}";
        var listContent = ""

        for (gamer in gamersList) {
            listContent += gamerToJson(gamer) + ","
        }

        return jsonArrayTemplate.replace("{listContent}", listContent.trimEnd(','))
    }
}