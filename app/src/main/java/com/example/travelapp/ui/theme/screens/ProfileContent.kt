package com.example.travelapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.travelapp.R
import com.example.travelapp.UserdataViewModel
import com.example.travelapp.ui.theme.components.DefaultButton
import com.example.travelapp.componets.InformationCard
import com.example.travelapp.ui.theme.components.ProfileAvatar
import com.example.travelapp.componets.SpaceHorizontal16
import com.example.travelapp.componets.SpaceVertical24
import com.example.travelapp.componets.SpaceVertical32
import com.example.travelapp.componets.TextButton
import com.example.travelapp.model.Userdata
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
    fun ProfileContent(nav: NavController,
                       p:PaddingValues,
                       viewModel : UserdataViewModel = viewModel()){
        viewModel.addUserdataViewModel(
            Userdata(1,"Walid","Alayash",
                "walidalayash@gmail.com","Libya",
                "09234578")
        )
        var user = viewModel.getUserdataViewModel()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SpaceVertical32()

            ProfileAvatar(
                painter = painterResource(id =  R.drawable.ad),
                size = 128
            )
            SpaceVertical24()

            TextButton(text = "Change Profile Picture") {}
            SpaceVertical32()

            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1F)) {
                    InformationCard(
                        title = "first Name",
                        information = user[0].name,
                        onTextChange = {}
                    )
                }
                SpaceHorizontal16()
                Box(modifier = Modifier.weight(1F)) {
                    InformationCard(
                        title = "Second_name",
                        information = user[0].secondName,
                        onTextChange = {}
                    )
                }
            }
            InformationCard(
                title = "Location",
                information = user[0].location,
                onTextChange = {}
            )
            InformationCard(
                title = "Email",
                information = user[0].email,
                onTextChange = {}
            )
            InformationCard(
                title ="Phone",
                information = user[0].phone,
                onTextChange = {}
            )

            DefaultButton(buttonText = "Save", onClick = {})
        }
    }




