package com.example.travelapp.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.travelapp.R
import com.example.travelapp.TravelcardViewModel
import com.example.travelapp.ui.theme.routes.Routes
import kotlinx.coroutines.launch

data class Travelcard(
    val imageUri: Int ,
    val title: String,
    val location: String,
    val rating: Double,
    val category: String
)

@Composable
fun HomeScreen(username: String, email: String, password: String) {
    val navController = rememberNavController()
    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val travelcardsViewModel: TravelcardViewModel = viewModel()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController, drawerState)
        },
        content = {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController, email, password) }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = scrollState)
                        .background(Color(0xFFE3F2FD))
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    HeaderSection(username = username, onMenuClick = {
                        scope.launch { drawerState.open() }
                    })

                    Spacer(modifier = Modifier.height(16.dp))

                    val RecommendedCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Recommended Sites" }
                    Section(title = "Recommended", travelcards = RecommendedCards)
                    Spacer(modifier = Modifier.height(16.dp))

                    val archaeologicalSitesCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Archaeological Sites" }
                    Section(title = "Archaeological Sites", travelcards = archaeologicalSitesCards)
                    Spacer(modifier = Modifier.height(16.dp))

                    val forestsCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Forests" }
                    Section(title = "Forests", travelcards = forestsCards)
                    Spacer(modifier = Modifier.height(16.dp))

                    val mountainsCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Mountains" }
                    Section(title = "Mountains", travelcards = mountainsCards)
                    Spacer(modifier = Modifier.height(16.dp))

                    val desertCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Desert" }
                    Section(title = "Desert", travelcards = desertCards)
                    Spacer(modifier = Modifier.height(16.dp))

                    val coastlinesCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Coastlines" }
                    Section(title = "Coastlines", travelcards = coastlinesCards)
                    Spacer(modifier = Modifier.height(16.dp))

                    val historicalCitiesCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Historical Cities" }
                    Section(title = "Historical Cities", travelcards = historicalCitiesCards)
                    Spacer(modifier = Modifier.height(16.dp))

                    val cultureCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Culture" }
                    Section(title = "Culture", travelcards = cultureCards)
                    Spacer(modifier = Modifier.height(16.dp))

                    val naturalWondersCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Natural Wonders" }
                    Section(title = "Natural Wonders", travelcards = naturalWondersCards)

                    val VegetablesCards = travelcardsViewModel.travel_cards_data.filter { it.category == "Vegetables" }
                    Section(title = "Ali & Argeaa Project", travelcards = VegetablesCards)
                }
            }
        }
    )
}

@Composable
fun HeaderSection(username: String, onMenuClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "User Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .clickable { }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "Hello, $username!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Let's explore the world",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable { onMenuClick() }
        )
    }
}


@Composable
fun Section(title: String, travelcards: List<Travelcard>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
            Text(
                text = "See All",
                color = Color(0xFF0077B6),
                modifier = Modifier.clickable { }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(travelcards) { card ->
                TravelCard(
                    imageUri = card.imageUri,
                    title = card.title,
                    location = card.location,
                    rating = card.rating,
                    navController = rememberNavController()
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun TravelCard(imageUri: Int, title: String, location: String, rating: Double,navController: NavHostController) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { navController.navigate(Routes.Travel) }
            .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageUri),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = location, color = Color.Gray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
                Text(text = rating.toString(), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, email: String, password: String) {
    BottomAppBar(
        containerColor = Color.White,
        contentColor = Color.Black,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home",
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate("home") }
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "Favorite",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate("favorite") }
            )
            Icon(
                painter = painterResource(id = R.drawable.notifications),
                contentDescription = "Notifications",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.navigate("notifications") }
            )
            if (email == "Admin@gmail.com" && password == "Admin") {
                Icon(
                    painter = painterResource(id = R.drawable.ic_support),
                    contentDescription = "Support",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.navigate("admin") }
                )
            }
        }
    }
}

@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Home", style = MaterialTheme.typography.bodyLarge, modifier = Modifier
            .clickable { navController.navigate("home") }
            .padding(8.dp))
        Text(text = "Favorite", style = MaterialTheme.typography.bodyLarge, modifier = Modifier
            .clickable { navController.navigate("favorite") }
            .padding(8.dp))
        Text(text = "Notifications", style = MaterialTheme.typography.bodyLarge, modifier = Modifier
            .clickable { navController.navigate("notifications") }
            .padding(8.dp))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Logout", style = MaterialTheme.typography.bodyLarge, color = Color.Red, modifier = Modifier
            .clickable { }
            .padding(8.dp))

        }
    }






