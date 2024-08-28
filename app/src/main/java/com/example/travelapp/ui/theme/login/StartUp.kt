package com.example.travelapp.ui.theme.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.travelapp.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelapp.ui.theme.components.CustomText
import com.example.travelapp.ui.theme.components.customFont
import com.example.travelapp.ui.theme.routes.Routes

@Composable
fun StartUpPage(nav:NavController){



    MaterialTheme{
        Scaffold (modifier = Modifier.fillMaxSize()){innerPadding->

            Box{
                Image(painter = painterResource(R.drawable.desertlogin),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(0.45f)
                )
            }
            Column(modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween) {
                Row(Modifier.padding(vertical = 32.dp)){
                    Spacer(modifier = Modifier.width(16.dp) )
                    Header()
                }

                Row (modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){
                    Actions(nav)
                }

            }






        }
    }

}

@Composable
private fun Header(){


    Column{


        Text(text = "Let's enjoy", style = TextStyle(
            fontFamily = customFont,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            color = Color.Black
        ))


        Text(text = "Welcome", style = TextStyle(
            fontFamily = customFont,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            fontSize = 42.sp,
            color = Color.Black
        ))

    }


}

@Composable
private fun Actions(nav: NavController){

    Column(Modifier.padding(vertical = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {nav.navigate(Routes.Login)},
            modifier = Modifier.fillMaxWidth().height(85.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF8EC3CC)
            )) {
            CustomText(text = "LOGIN",
                modifier = Modifier.fillMaxHeight(0.5f),
                fontWeight = FontWeight.W800,
                fontSize = 18,
                color = Color.White
            )

        }

        Button(onClick = {nav.navigate(Routes.Signup)},
            modifier = Modifier.fillMaxWidth().height(85.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF8EC3CC)
            )) {
            CustomText(text = "REGISTER",
                modifier = Modifier.fillMaxHeight(0.5f),
                fontWeight = FontWeight.W800,
                fontSize = 18,
                color = Color.White
            )

        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {nav.navigate("intro/Badr/800/${R.drawable.ad}")}) {
            CustomText(text = "Skip",
                fontWeight = FontWeight.W800,
                fontStyle = FontStyle.Italic,
                fontSize = 18,
                color = Color.White)
        }
    }


}