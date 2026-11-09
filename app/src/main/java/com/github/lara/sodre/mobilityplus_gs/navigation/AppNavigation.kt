package com.github.lara.sodre.mobilityplus_gs.navigation

import com.github.lara.sodre.mobilityplus_gs.viewmodel.LocalViewModelFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.github.lara.sodre.mobilityplus_gs.data.LocalRepository
import com.github.lara.sodre.mobilityplus_gs.ui.screens.CadastroScreen
import com.github.lara.sodre.mobilityplus_gs.ui.screens.DetalhesScreen
import com.github.lara.sodre.mobilityplus_gs.ui.screens.HomeScreen
import com.github.lara.sodre.mobilityplus_gs.ui.screens.LocaisScreen
import com.github.lara.sodre.mobilityplus_gs.ui.screens.PerfilScreen
import com.github.lara.sodre.mobilityplus_gs.viewmodel.LocalViewModel


@Composable
fun AppNavigation(
    repository: LocalRepository
) {

    val navController = rememberNavController()

    val viewModel: LocalViewModel = viewModel(
        factory = LocalViewModelFactory(repository)
    )

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.mensagens.collect { texto ->
            snackbarHostState.showSnackbar(texto)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        NavHost(
            navController = navController,
            startDestination = "inicio"
        ) {

            // TELA INICIAL
            composable("inicio") {

                HomeScreen(

                    onLocais = {
                        navController.navigate("locais")
                    },

                    onCadastro = {
                        navController.navigate("cadastro")
                    },

                    onPerfil = {
                        navController.navigate("perfil")
                    }
                )
            }

            // LISTAGEM DE LOCAIS
            composable("locais") {

                LocaisScreen(

                    viewModel = viewModel,

                    onCadastro = {
                        navController.navigate("cadastro")
                    },

                    onDetalhes = { id ->
                        navController.navigate("detalhes/$id")
                    },

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            // CADASTRO
            composable("cadastro") {

                CadastroScreen(

                    viewModel = viewModel,

                    id = null,

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            // EDIÇÃO
            composable(
                route = "cadastro/{id}",

                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )

            ) { entrada ->

                val id = entrada.arguments?.getInt("id")

                CadastroScreen(

                    viewModel = viewModel,

                    id = id,

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            // DETALHES
            composable(
                route = "detalhes/{id}",

                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )

            ) { entrada ->

                val id = entrada.arguments?.getInt("id") ?: 0

                DetalhesScreen(

                    id = id,

                    viewModel = viewModel,

                    onEditar = {
                        navController.navigate("cadastro/$id")
                    },

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            // PERFIL DE ACESSIBILIDADE
            composable(route = "perfil") {
                PerfilScreen(
                    viewModel = viewModel,
                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // MENSAGENS (Snackbar) exibidas sobre qualquer tela
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}