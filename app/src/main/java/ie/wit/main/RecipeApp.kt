package ie.wit.main


import android.app.Application
import android.util.Log
import ie.wit.models.RecipeJSONStore
import ie.wit.models.RecipeMemStore

import ie.wit.models.RecipeStore

class RecipeApp : Application() {

    lateinit var recipesStore: RecipeStore

    override fun onCreate() {
        super.onCreate()
       recipesStore = RecipeJSONStore(applicationContext)
        Log.v("Recipe","Recipe World App started")
    }
}