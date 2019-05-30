package com.stride.rickandmortycharactersjava.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RickAndMortyCharacter(val id: Long, val name: String, val image: String) : Parcelable