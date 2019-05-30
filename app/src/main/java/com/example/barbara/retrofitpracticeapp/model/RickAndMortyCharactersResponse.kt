package com.stride.rickandmortycharactersjava.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RickAndMortyCharactersResponse(val results: List<RickAndMortyCharacter>) : Parcelable
