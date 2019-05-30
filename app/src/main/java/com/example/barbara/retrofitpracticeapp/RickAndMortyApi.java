package com.example.barbara.retrofitpracticeapp;

import com.stride.rickandmortycharactersjava.model.RickAndMortyCharacter;
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharactersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RickAndMortyApi {
    @GET("character")
    Call<RickAndMortyCharactersResponse> getCharacters();

    @GET("character/{id}")
    Call<RickAndMortyCharacter> getCharacter(@Path("id") long id);
}
