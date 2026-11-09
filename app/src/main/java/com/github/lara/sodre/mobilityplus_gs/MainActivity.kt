package com.github.lara.sodre.mobilityplus_gs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.lara.sodre.mobilityplus_gs.data.AppDatabase
import com.github.lara.sodre.mobilityplus_gs.data.LocalRepository
import com.github.lara.sodre.mobilityplus_gs.navigation.AppNavigation
import com.github.lara.sodre.mobilityplus_gs.ui.theme.MobilityPlusTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)

        val repository = LocalRepository(
            dao = database.localDao(),
            preferenciasDao = database.preferenciasDao()
        )

        setContent {

            MobilityPlusTheme {

                AppNavigation(repository)
            }
        }
    }
}
