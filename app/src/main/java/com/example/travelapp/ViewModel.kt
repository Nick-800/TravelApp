package com.example.travelapp

import androidx.lifecycle.ViewModel
import com.example.travelapp.data.Userdata
import com.example.travelapp.ui.theme.screens.Travelcard

import androidx.compose.runtime.mutableStateListOf


class UserdataViewModel : ViewModel() {
     // This would be your data source, replace with your actual data source
     val userdata = mutableStateListOf<Userdata>(
         Userdata(
              0,
              "Admin",
              "Admin@gmail.com",
              "Admin",
         )
     )


     // Method to add a new user
     fun addUser(user: Userdata) {
          userdata.add(user)
     }
}


class TravelcardViewModel : ViewModel() {

     val travel_cards_data = mutableListOf<Travelcard>(

          // Archaeological Sites
          Travelcard(R.drawable.site1, "Archaeological Sites", "Sabratha", 4.8, "Recommended"),
          Travelcard(R.drawable.site2, "Archaeological Sites", "Cyrene", 4.9, "Recommended"),
          Travelcard(R.drawable.site4, "Archaeological Sites", "Leptis Magna", 4.9, "Recommended"),
          Travelcard(R.drawable.site3, "Archaeological Sites", "Ghadames", 4.7, "Recommended"),
          Travelcard(R.drawable.site5, "Archaeological Sites", "Tadrart Acacus", 4.8, "Recommended"),
          Travelcard(R.drawable.site6, "Archaeological Sites", "Oases of the Desert", 4.7, "Archaeological Sites"),
          Travelcard(R.drawable.site7, "Archaeological Sites", "Ptolemais", 4.6, "Archaeological Sites"),
          Travelcard(R.drawable.site8, "Archaeological Sites", "Apollonia", 4.7, "Archaeological Sites"),
          Travelcard(R.drawable.site9, "Archaeological Sites", "Benghazi Fort", 4.5, "Archaeological Sites"),
          Travelcard(R.drawable.site10, "Archaeological Sites", "Tomb of the Unknown Soldier", 4.6, "Archaeological Sites"),

          // Forests
          Travelcard(R.drawable.forest1, "Forests", "Jebel Akhdar", 4.6, "Forests"),
          Travelcard(R.drawable.forest2, "Forests", "Jebel Nafusa", 4.7, "Forests"),
          Travelcard(R.drawable.forest3, "Forests", "Wadi al-Hayat", 4.5, "Forests"),
          Travelcard(R.drawable.forest4, "Forests", "Jebel al-Sawda", 4.6, "Forests"),
          Travelcard(R.drawable.forest5, "Forests", "Jebel al-Gharbi", 4.7, "Forests"),
          Travelcard(R.drawable.forest6, "Forests", "Al Jabal al Akhdar National Park", 4.8, "Forests"),
          Travelcard(R.drawable.forest7, "Forests", "Wadi al-Qurra", 4.6, "Forests"),
          Travelcard(R.drawable.forest8, "Forests", "Wadi al-Hamra", 4.7, "Forests"),
          Travelcard(R.drawable.forest9, "Forests", "Jebel al-Nafusa", 4.5, "Forests"),
          Travelcard(R.drawable.forest10, "Forests", "Jebel al-Maqrun", 4.6, "Forests"),

          // Mountains
          Travelcard(R.drawable.m1, "Mountains", "Jebel Nafusa", 4.7, "Mountains"),
          Travelcard(R.drawable.m2, "Mountains", "Jebel Akhdar", 4.8, "Mountains"),
          Travelcard(R.drawable.m3, "Mountains", "Jebel al-Sawda", 4.6, "Mountains"),
          Travelcard(R.drawable.m4, "Mountains", "Jebel al-Gharbi", 4.7, "Mountains"),
          Travelcard(R.drawable.m5, "Mountains", "Jebel al-Tarif", 4.5, "Mountains"),
          Travelcard(R.drawable.m6, "Mountains", "Jebel al-Maqrun", 4.6, "Mountains"),
          Travelcard(R.drawable.m7, "Mountains", "Jebel al-Faragh", 4.8, "Mountains"),
          Travelcard(R.drawable.m8, "Mountains", "Jebel al-Qubbah", 4.7, "Mountains"),
          Travelcard(R.drawable.m9, "Mountains", "Jebel al-Baida", 4.6, "Mountains"),
          Travelcard(R.drawable.m10, "Mountains", "Jebel al-Hanash", 4.5, "Mountains"),

          // Desert
          Travelcard(R.drawable.d1, "Desert", "Great Sand Sea", 4.8, "Desert"),
          Travelcard(R.drawable.d2, "Desert", "Ubari Sand Sea", 4.9, "Desert"),
          Travelcard(R.drawable.d3, "Desert", "Wadi al-Hayat", 4.7, "Desert"),
          Travelcard(R.drawable.d4, "Desert", "Akakus Mountains", 4.8, "Desert"),
          Travelcard(R.drawable.d5, "Desert", "Murzuq Sand Sea", 4.7, "Desert"),

          // Coastlines
          Travelcard(R.drawable.error, "Coastlines", "Tripoli", 4.7, "Coastlines"),
          Travelcard(R.drawable.error, "Coastlines", "Benghazi", 4.6, "Coastlines"),
          Travelcard(R.drawable.error, "Coastlines", "Derna", 4.8, "Coastlines"),
          Travelcard(R.drawable.error, "Coastlines", "Zuwarah", 4.7, "Coastlines"),
          Travelcard(R.drawable.error, "Coastlines", "Misrata", 4.6, "Coastlines"),
          Travelcard(R.drawable.error, "Coastlines", "Sabratha", 4.8, "Coastlines"),
          Travelcard(R.drawable.error, "Coastlines", "Homs", 4.7, "Coastlines"),
          Travelcard(R.drawable.error, "Coastlines", "Tobruk", 4.6, "Coastlines"),
          Travelcard(R.drawable.error, "Coastlines", "Al Khums", 4.8, "Coastlines"),
          Travelcard(R.drawable.error, "Coastlines", "Zliten", 4.7, "Coastlines"),

          // Historical Cities
          Travelcard(R.drawable.error, "Historical Cities", "Tripoli", 4.7, "Historical Cities"),
          Travelcard(R.drawable.error, "Historical Cities", "Benghazi", 4.6, "Historical Cities"),
          Travelcard(R.drawable.error, "Historical Cities", "Derna", 4.8, "Historical Cities"),
          Travelcard(R.drawable.error, "Historical Cities", "Misrata", 4.7, "Historical Cities"),
          Travelcard(R.drawable.error, "Historical Cities", "Sabratha", 4.8, "Historical Cities"),
          Travelcard(R.drawable.error, "Historical Cities", "Ghadames", 4.7, "Historical Cities"),
          Travelcard(R.drawable.error, "Historical Cities", "Cyrene", 4.9, "Historical Cities"),
          Travelcard(R.drawable.error, "Historical Cities", "Leptis Magna", 4.9, "Historical Cities"),
          Travelcard(R.drawable.error, "Historical Cities", "Tadrart Acacus", 4.8, "Historical Cities"),
          Travelcard(R.drawable.error, "Historical Cities", "Ptolemais", 4.6, "Historical Cities"),

          // Culture
          Travelcard(R.drawable.error, "Culture", "Libyan Museum", 4.8, "Culture"),
          Travelcard(R.drawable.error, "Culture", "National Art Gallery", 4.7, "Culture"),
          Travelcard(R.drawable.error, "Culture", "Traditional Festivals", 4.9, "Culture"),
          Travelcard(R.drawable.error, "Culture", "Local Art Exhibitions", 4.6, "Culture"),
          Travelcard(R.drawable.error, "Culture", "Folklore Museum", 4.8, "Culture"),
          Travelcard(R.drawable.error, "Culture", "Cultural Heritage Sites", 4.7, "Culture"),
          Travelcard(R.drawable.error, "Culture", "Historic Theatres", 4.9, "Culture"),
          Travelcard(R.drawable.error, "Culture", "Crafts Workshops", 4.6, "Culture"),
          Travelcard(R.drawable.error, "Culture", "Traditional Music Venues", 4.8, "Culture"),
          Travelcard(R.drawable.error, "Culture", "Art Galleries", 4.7, "Culture"),

          // Natural Wonders
          Travelcard(R.drawable.error, "Natural Wonders", "Jebel Nafusa", 4.8, "Natural Wonders"),
          Travelcard(R.drawable.error, "Natural Wonders", "Great Sand Sea", 4.8, "Natural Wonders"),
          Travelcard(R.drawable.error, "Natural Wonders", "Akakus Mountains", 4.9, "Natural Wonders"),
          Travelcard(R.drawable.error, "Natural Wonders", "Ubari Sand Sea", 4.8, "Natural Wonders"),
          Travelcard(R.drawable.error, "Natural Wonders", "Wadi al-Hayat", 4.7, "Natural Wonders"),
          Travelcard(R.drawable.error, "Natural Wonders", "Murzuq Sand Sea", 4.6, "Natural Wonders"),
          Travelcard(R.drawable.error, "Natural Wonders", "Sahara Desert", 4.9, "Natural Wonders"),
          Travelcard(R.drawable.error, "Natural Wonders", "Jebel al-Sawda", 4.7, "Natural Wonders"),
          Travelcard(R.drawable.error, "Natural Wonders", "Wadi al-Hamra", 4.6, "Natural Wonders"),
          Travelcard(R.drawable.error, "Natural Wonders", "Jebel Akhdar", 4.8, "Natural Wonders"),



          Travelcard(R.drawable.vegetables, "Vegetables", "Apple", 4.8, "Vegetables"),
          Travelcard(R.drawable.vegetables, "Vegetables", "Strawberry", 4.8, "Vegetables"),
          Travelcard(R.drawable.vegetables, "Vegetables", "Melon", 4.9, "Vegetables"),
          Travelcard(R.drawable.vegetables, "Vegetables", "Mango", 4.8, "Vegetables"),
          Travelcard(R.drawable.vegetables, "Vegetables", "Mango", 4.7, "Vegetables"),
          Travelcard(R.drawable.vegetables, "Vegetables", "Mango", 4.6, "Vegetables"),
          Travelcard(R.drawable.vegetables, "Vegetables", "Mango", 4.9, "Vegetables"),
          Travelcard(R.drawable.vegetables, "Vegetables", "Mango", 4.7, "Vegetables"),
          Travelcard(R.drawable.vegetables, "Vegetables", "Mango", 4.6, "Vegetables"),
          Travelcard(R.drawable.vegetables, "Vegetables", "Mango", 4.8, "Vegetables")



     )




}