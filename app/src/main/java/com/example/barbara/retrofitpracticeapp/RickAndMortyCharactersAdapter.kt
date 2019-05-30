package com.example.barbara.retrofitpracticeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharacter
import java.util.ArrayList

class RickAndMortyCharactersAdapter(private val itemListener: ItemListener<RickAndMortyCharacter>) :
    RecyclerView.Adapter<RickAndMortyCharactersAdapter.RickAndMortyCharacterViewHolder>() {

    private val characters = ArrayList<RickAndMortyCharacter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyCharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false)
        return RickAndMortyCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RickAndMortyCharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.characterNameLabel.text = character.name
        Glide.with(holder.characterImageView).load(character.image).into(holder.characterImageView)
        holder.itemView.setOnClickListener { itemListener.onClick(character) }
    }

    override fun getItemCount(): Int = characters.count()

    fun setCharacters(newCharacters: List<RickAndMortyCharacter>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    class RickAndMortyCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterNameLabel: TextView = itemView.findViewById(R.id.character_name)
        val characterImageView: ImageView = itemView.findViewById(R.id.character_image)
    }
}
