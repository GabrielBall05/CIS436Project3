package com.example.project3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project3.data.DogBreed

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
        fetchBreeds()
    }

    //To get all dog breeds upon opening app
    fun fetchBreeds() {
        //TODO: Use Volley to get a list of all dog breeds
        //I made a Constants.kt where there is the base url and the api key. Just do Constants.BASE_URL or .API_KEY
        //to access them. Just follow along the example he gave us for making the request and parsing the response.
        //Make sure to look at the documentation and make some requests (outside the app) to see what it gives you first.
        //TODO: Parse JSON response into objects of DogBreed class and add to a temporary MutableListOf<DogBreed>()
        //TODO: Then once the whole response is parsed, do breedsList.value = tempList
    }

    //To update the selection when the spinner changes
    fun onBreedSelected(breed: DogBreed) {
        //Changing selectedBreed's value means anything observing selectedBreed
        //will automatically update its information based on the selected breed.
        selectedBreed.value = breed
    }

    //TODO: We will probably need a method here for fetching the image since that is a separate request to the api
}