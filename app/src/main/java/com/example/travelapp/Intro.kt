package com.example.travelapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController



@Composable
fun IntroScreen(nav: NavController, username: String, avatarResource: Int) {
    var pageIndex by remember { mutableIntStateOf(0) }
    val pages = listOf(
        listOf(
            PageData("Tickets", "Easily book your journey to explore the undiscovered treasures of Libya.", R.drawable.tickets),
            PageData("Hotels", "Experience Libyan warmth with our carefully selected accommodations.", R.drawable.hotels),
            PageData("Adventure", "Dive into extraordinary adventures across Libya's breathtaking landscapes.", R.drawable.adventure)
        ),
        listOf(
            PageData("Beaches", "Relax on Libya's stunning Mediterranean shores.", R.drawable.beach),
            PageData("Mountains", "Venture into Libya's awe-inspiring mountains and valleys.", R.drawable.mountain),
            PageData("Cultural Sites", "Discover Libya's rich heritage through its ancient ruins and cultural wonders.", R.drawable.cultural_sites)
        ),
        listOf(
            PageData("Services", "Custom-tailored travel services with translators and tour guides to enhance your Libyan experience.", R.drawable.hotels),
            PageData("Offers", "Exclusive deals and packages to explore Libya's vast attractions.", R.drawable.mountain),
            PageData("Guides", "Expert guides and translators to enrich your journey across Libya.", R.drawable.adventure)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF00B4D8), Color(0xFF0077B6))
            ))
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderSection(username = username, avatarResource = avatarResource)
            Spacer(modifier = Modifier.height(16.dp))
            FeatureCards(pageData = pages[pageIndex])
            Spacer(modifier = Modifier.height(16.dp))
            NavigationControls(pageIndex, pages.size, onPageChange = { pageIndex += it }, nav)
        }
    }
}

@Composable
fun HeaderSection(username: String, avatarResource: Int) {
    Row(horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start
            , modifier = Modifier.weight(2f)

        ) {
            Text(text = username.split(" ")[0], fontSize = 24.sp, color = Color.White)
            Text(text = username.split(" ")[1], fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(
                text = "We focus a lot on helping the first time or inexperienced traveler head out",
                fontSize = 14.sp,
                color = Color.White
            )
        }
        Image(
            painter = painterResource(id = avatarResource),
            contentDescription = "User Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)

        )
    }
}

@Composable
fun FeatureCards(pageData: List<PageData>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        pageData.forEach { data ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFB3E5FC))
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = data.iconId),
                    contentDescription = "${data.title} Icon",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = data.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = data.description, fontSize = 14.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun NavigationControls(currentPage: Int, totalPages: Int, onPageChange: (Int) -> Unit, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PageIndicators(pageIndex = currentPage, totalPages = totalPages)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { if (currentPage > 0) onPageChange(-1) },
                enabled = currentPage > 0
            ) {
                Text("Back")
            }
            if (currentPage == totalPages - 1) {
                Button(
                    onClick = {
                        navController.navigate("home")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Start Tour", color = Color.White)
                }
            } else {
                Button(
                    onClick = { if (currentPage < totalPages - 1) onPageChange(1) }
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun PageIndicators(pageIndex: Int, totalPages: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalPages) { index ->
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (index == pageIndex) Color.White else Color.LightGray)
                    .height(if (index == pageIndex) 22.dp else 12.dp )
                    .width(if (index == pageIndex) 10.dp else 12.dp )
            )
        }
    }
}

data class PageData(val title: String, val description: String, val iconId:Int)