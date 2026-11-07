package com.github.lara.sodre.mobilityplus_gs.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.lara.sodre.mobilityplus_gs.viewmodel.LocalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocaisScreen(
    viewModel: LocalViewModel,
    onCadastro: () -> Unit,
    onDetalhes: (Int) -> Unit,
    onVoltar: () -> Unit
) {

    val locais by viewModel.locais.collectAsState()

    var pesquisa by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf("Todos") }

    val filtrados = locais.filter { local ->

        val correspondeNome =
            local.nome.contains(pesquisa, ignoreCase = true)

        val correspondeFiltro = when (filtro) {
            "Física" -> local.rampa
            "Visual" -> local.pisoTatil || local.sinalizacaoVisual
            "Auditiva" -> local.sinalizacaoSonora
            "Favoritos" -> local.favorito
            else -> true
        }

        correspondeNome && correspondeFiltro
    }

    Scaffold(
        containerColor = Color(0xFFF5F6FA),
        topBar = {
            TopAppBar(
                title = { Text("Locais acessíveis") },
                navigationIcon = {
                    TextButton(onClick = onVoltar) {
                        Text("Voltar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCadastro,
                containerColor = Color(0xFF5369B5),
                contentColor = Color.White
            ) {
                Text("+")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = pesquisa,
                onValueChange = { pesquisa = it },
                label = { Text("Pesquisar estabelecimento") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                listOf(
                    "Todos",
                    "Física",
                    "Visual",
                    "Auditiva",
                    "Favoritos"
                ).forEach { opcao ->

                    FilterChip(
                        selected = filtro == opcao,
                        onClick = { filtro = opcao },
                        label = { Text(opcao) }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            if (filtrados.isEmpty()) {

                Text(
                    if (locais.isEmpty())
                        "Nenhum estabelecimento cadastrado."
                    else
                        "Nenhum local encontrado para essa pesquisa."
                )

            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(filtrados, key = { it.id }) { local ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onDetalhes(local.id)
                                },
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    local.nome,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    local.endereco,
                                    color = Color.Gray
                                )

                                Spacer(Modifier.height(8.dp))

                                Text(
                                    "Rampa: ${if (local.rampa) "Sim" else "Não"}"
                                )

                                Text(
                                    "Piso tátil: ${if (local.pisoTatil) "Sim" else "Não"}"
                                )

                                Text(
                                    if (local.favorito)
                                        "★ Favorito"
                                    else
                                        "☆ Não favoritado"
                                )

                                Text(
                                    "Ver detalhes",
                                    color = Color(0xFF5369B5)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}