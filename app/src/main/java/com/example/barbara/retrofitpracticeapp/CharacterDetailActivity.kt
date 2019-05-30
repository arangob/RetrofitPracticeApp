package com.example.barbara.retrofitpracticeapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharacter
import kotlinx.android.synthetic.main.activity_character_detail.*

class CharacterDetailActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var character: RickAndMortyCharacter
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        character = intent.getParcelableExtra(CHARACTER)
        Glide.with(character_image).load(character.image).into(character_image)
        character_name.text = character.name

        sharedPref = getSharedPreferences(FAVORITE_PREFERENCE_FILE, Context.MODE_PRIVATE)
        setFavorite()
    }

    private fun setFavorite() {
        isFavorite = sharedPref.getBoolean(character.name, false)
        setImageViewResource(isFavorite)
        setupOnClickListener()
    }

    private fun setImageViewResource(isFavorite: Boolean) {
        character_favorite.setImageResource(if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_empty)
    }

    private fun setupOnClickListener() {
        character_favorite.setOnClickListener {
            updateIsFavorite()
        }
    }

    private fun updateIsFavorite() {
        isFavorite = !isFavorite
        sharedPref.edit().apply {
            putBoolean(character.name, isFavorite)
            commit()
        }
        setImageViewResource(isFavorite)
    }

    companion object {
        const val CHARACTER = "character"
        const val FAVORITE_PREFERENCE_FILE = "favorites"
    }
}
