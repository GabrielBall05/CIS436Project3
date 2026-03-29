package com.example.project3.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.project3.data.Constants
import com.example.project3.data.DogBreed
import org.json.JSONArray

class DogViewModel : ViewModel() {
    //List of all the breeds as LiveData
    //This will be filled in with all the information from the initial API response
    //It will be observed by the Spinner (svBreeds) in BreedSelectorFragment
    //and any change to this list will automatically change the items in the spinner if done correctly
    val breedsList = MutableLiveData<List<DogBreed>>()

    //Selected dog breed as LiveData
    //This will hold the single breed the user currently has selected
    //Its data will be observed by the text views and image in BreedDetailsFragment
    //and any change to this will automatically change the information details in the fragment if done correctly
    val selectedBreed = MutableLiveData<DogBreed>()

    init {
        //Call fetchBreeds() right here as soon as the app has loaded to retrieve initial data from the dog api
        fetchBreeds(context: Context)
    }

    //To get all dog breeds upon opening app

    fun fetchBreeds(context: Context) {
        val baseUrl = Constants.BASE_URL
        val apiKey = Constants.API_KEY
        val url = "$baseUrl?api_key=$apiKey"

        // 1. Create the RequestQueue
        val queue = Volley.newRequestQueue(context)

        // 2. Create the JsonArrayRequest
        val request = object : JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                // Success: Log the number of breeds found
                Log.i("DogApi", "Successfully retrieved ${response.length()} breeds.")

                for(i in 0 until response.length()) {
                    val curBreed = response.getJSONObject(i)
                    val name = curBreed.getString("name")
                    val lifeSpan = curBreed.getString("life_span")
                    val temperament = curBreed.getString("temperament")

                    // Detailed log for each breed
                    Log.i("DogApi", "Breed[$i]: $name | Temperament: $temperament | Life Span: $lifeSpan")
                }
            },
            Response.ErrorListener  { error ->
                // Handle error (e.g., Timeout, No Internet, 403 Unauthorized)
                Log.i("DogApi", "Error: ${error.message}")
            }
        ) {} // Close object - braces are required

        // 3. Add the request to the RequestQueue
        queue.add(request)
    } // Close fetchDogBreeds


    //fun fetchBreeds() {
        //TODO: Use Volley to get a list of all dog breeds
        //I made a Constants.kt where there is the base url and the api key. Just do Constants.BASE_URL or .API_KEY
        //to access them. Just follow along the example he gave us for making the request and parsing the response.
        //Make sure to look at the documentation and make some requests (outside the app) to see what it gives you first.
        //TODO: Parse JSON response into objects of DogBreed class and add to a temporary MutableListOf<DogBreed>()
        //TODO: Then once the whole response is parsed, do breedsList.value = tempList

        //val api = Constants.BASE_URL
    //}

    //To update the selection when the spinner changes
    fun onBreedSelected(breed: DogBreed) {
        //Changing selectedBreed's value means anything observing selectedBreed
        //will automatically update its information based on the selected breed.
        selectedBreed.value = breed
    }

    //TODO: We will probably need a method here for fetching the image since that is a separate request to the api
}