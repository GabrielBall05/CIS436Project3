# CIS 436 Project 3 - Dog Breed App

## Team Members
- Gabriel Ball
- Francisco Hernandez
- Giovanni Claro
---

## Project Overview
This application is a Dog Breed information app that uses the Dog API to retrieve real-time data about different dog breeds.

The app allows users to:
- View a list of dog breeds in a dropdown (Spinner)
- Select a breed
- View detailed information about the selected breed, including:
  - Name
  - Temperament
  - Origin
  - Life Span
  - Image

The app follows Android Jetpack principles using Fragments and a shared ViewModel.

---

## Technologies Used
- Kotlin
- Android Studio
- Jetpack (Fragments, ViewModel, LiveData)
- Volley (API requests)
- Coil (image loading)
- The Dog API (https://thedogapi.com)

---

## Features Implemented

### API Integration
The app uses Volley to make HTTP requests to the Dog API and retrieve breed data in JSON format.

### JSON Parsing
The JSON response is parsed and converted into DogBreed objects which are stored in LiveData.

### Spinner (Dropdown)
The list of breeds is displayed in a Spinner at the top of the app.

### Dynamic UI Updates
When a user selects a breed:
- The ViewModel updates the selectedBreed LiveData
- The UI automatically updates in the details fragment

### Breed Details Display
The bottom fragment displays:
- Dog name
- Temperament
- Origin
- Life Span
- Image loaded from URL

---

## Team Contributions

### Gabriel Ball
- Set up project structure
- Created fragments and layouts
- Implemented initial ViewModel structure
- Designed UI layout using ConstraintLayout and ScrollView
- Other small things like keeping selection on orientation change
  and retrying initial fetch if it fails.
- DogBreed data model

### Francisco Hernandez
- Implemented API integration using Volley
- Parsed JSON response into DogBreed objects
- Connected API data to LiveData
- Implemented Spinner population logic
- Implemented breed selection handling
- Implemented BreedDetailsFragment UI updates

## Giovanni Claro
- Implemented initial API integration for Volley
- Developed test plan
- Executed test plan

---

## Biggest Challenge
The biggest challenge was integrating the Dog API and correctly parsing the JSON response into usable data for the app.

Handling nullable values (such as missing images or origins) also required careful error handling to prevent crashes.

---

## Biggest Time Consumption
The most time-consuming part of the project was:
- Debugging API responses
- Ensuring the Spinner and LiveData updated correctly
- Fixing issues with image loading and null values

---

## Notes
This app follows the MVVM (Model-View-ViewModel) architecture pattern using Android Jetpack components.

The app successfully retrieves and displays real-time data from an external API.
