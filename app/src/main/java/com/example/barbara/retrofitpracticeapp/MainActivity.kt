package com.example.barbara.retrofitpracticeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stride.rickandmortycharactersjava.model.RickAndMortyCharactersResponse
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var api: RickAndMortyApi
    lateinit var adapter: RickAndMortyCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = RickAndMortyCharactersAdapter(ItemListener { item ->
            val intent = Intent(this@MainActivity, CharacterDetailActivity::class.java)
            intent.putExtra(CharacterDetailActivity.CHARACTER, item)
            startActivity(intent)
        })
        recyclerview.adapter = adapter

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        api = retrofit.create<RickAndMortyApi>(RickAndMortyApi::class.java)
    }

    override fun onResume() {
        super.onResume()
        api.characters.enqueue(object : Callback<RickAndMortyCharactersResponse> {
            override fun onResponse(
                call: Call<RickAndMortyCharactersResponse>,
                response: Response<RickAndMortyCharactersResponse>
            ) {
                if (response.body() != null) {
                    adapter.setCharacters(response.body()?.results!!)
                }
            }

            override fun onFailure(call: Call<RickAndMortyCharactersResponse>, t: Throwable) {}
        })
    }
}
