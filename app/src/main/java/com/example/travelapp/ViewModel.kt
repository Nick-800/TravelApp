package com.example.travelapp

import androidx.lifecycle.ViewModel
import com.example.travelapp.data.Userdata

class UserdataViewModel : ViewModel() {

     val userdata = mutableListOf<Userdata>()

}