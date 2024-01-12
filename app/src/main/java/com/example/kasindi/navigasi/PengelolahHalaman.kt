package com.example.kasindi.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kasindi.ui.view.screen.AddTransScreen
import com.example.kasindi.ui.view.screen.DestinasiAddTran
import com.example.kasindi.ui.view.screen.DestinasiDetailTrans
import com.example.kasindi.ui.view.screen.DestinasiHome
import com.example.kasindi.ui.view.screen.DetailTransScreen
import com.example.kasindi.ui.view.screen.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ){
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiAddTran.route) },
                onDetailClick = {
                    navController.navigate("${DestinasiDetailTrans.route}/$it")
                })
        }
        composable(DestinasiAddTran.route){
            AddTransScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            DestinasiDetailTrans.routeWithArg,
            arguments = listOf(navArgument(DestinasiDetailTrans.transIdArg){
                type = NavType.IntType
            })
        ){
            DetailTransScreen(
                navigateBack = {navController.popBackStack()},
                navigateToEditItem = {}
            )
        }
    }
}