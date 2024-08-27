package com.example.todoapp

import androidx.lifecycle.ViewModel
import com.example.todoapp.model.Userdata

class UserdataViewModel : ViewModel() {

    private val userdata = mutableListOf<Userdata>()

    fun addUserdataViewModel(employee: Userdata) {
        userdata.add(employee)
    }

    fun getUserdataViewModel() = userdata
}