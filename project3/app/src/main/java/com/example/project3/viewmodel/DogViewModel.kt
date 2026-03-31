package com.example.project3.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.project3.data.Constants
import com.example.project3.data.DogBreed

class DogViewModel(application: Application) : AndroidViewModel(application) {
    // -Francisco: LiveData list that stores all dog breeds from the API
    val breedsList = MutableLiveData<List<DogBreed>>()

    //Selected dog breed as LiveData
    val selectedBreed = MutableLiveData<DogBreed>()

    //For retrying fetching initial breeds if there was an error with Volley
    private var retries = 0;
    private val maxRetries = 3;

    // -Francisco: automatically fetch data when app starts
    init {
        Log.d("DogViewModel", "ViewModel Initialized")
        fetchBreeds()
    }

    fun fetchBreeds() {
        val url = "${Constants.BASE_URL}?api_key=${Constants.API_KEY}"
        // Francisco: Create a Volley request queue
        val queue = Volley.newRequestQueue(getApplication())

        // Create the JsonArrayRequest
        val request = object : JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                val tempList = mutableListOf<DogBreed>()
                for (i in 0 until response.length()) {
                    val curBreed = response.getJSONObject(i)
                    
                    var imageUrl = ""
                    if (curBreed.has("image") && !curBreed.isNull("image")) {
                        imageUrl = curBreed.getJSONObject("image").optString("url", "")
                    }

                    tempList.add(DogBreed(
                        id = curBreed.optInt("id", 0),
                        name = curBreed.optString("name", "Unknown"),
                        temperament = curBreed.optString("temperament", "Unknown"),
                        origin = curBreed.optString("origin", "Unknown"),
                        lifeSpan = curBreed.optString("life_span", "Unknown"),
                        imageUrl = imageUrl
                    ))
                }

                Log.d("DogViewModel", "Fetched ${tempList.size} breeds")
                // Use .value since Volley returns on the Main Thread
                breedsList.value = tempList

                if (tempList.isNotEmpty() && selectedBreed.value == null) {
                    selectedBreed.value = tempList[0]
                }
            },
            Response.ErrorListener { error ->
                Log.e("DogViewModel", "Volley Error: ${error.message}")
                //Try again
                val isRecoverableError = error is com.android.volley.TimeoutError || error is com.android.volley.NoConnectionError
                if (isRecoverableError && retries < maxRetries) {
                    Log.e("DogViewModel", "Retrying breeds fetch")
                    fetchBreeds()
                    retries++
                }
            }
        ) {}
        queue.add(request)
    }

    fun onBreedSelected(breed: DogBreed) {
        if (selectedBreed.value != breed) {
            Log.d("DogViewModel", "New breed selected: ${breed.name}")
            selectedBreed.value = breed
        }
    }
}