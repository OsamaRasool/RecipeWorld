package ie.wit.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import ie.wit.helpers.exists
import ie.wit.helpers.read
import ie.wit.helpers.write
import org.jetbrains.anko.AnkoLogger
import java.util.*


val JSON_FILE = "recipelist.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<RecipeModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class RecipeJSONStore : RecipeStore, AnkoLogger {

    val context: Context
    var recipesStore = mutableListOf<RecipeModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<RecipeModel> {
        return recipesStore
    }

    override fun create(recipe: RecipeModel) {
        recipe.id = generateRandomId()
        recipesStore.add(recipe)
        serialize()
    }




    private fun serialize() {
        val jsonString = gsonBuilder.toJson(recipesStore, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        recipesStore = Gson().fromJson(jsonString, listType)
    }
}