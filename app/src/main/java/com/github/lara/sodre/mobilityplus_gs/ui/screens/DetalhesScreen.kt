package com.github.lara.sodre.mobilityplus_gs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.lara.sodre.mobilityplus_gs.data.Local
import com.github.lara.sodre.mobilityplus_gs.viewmodel.LocalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesScreen(
    id: Int,
    viewModel: LocalViewModel,
    onEditar: () -> Unit,
    onVoltar: () -> Unit
) {

    var local by remember { mutableStateOf<Local?>(null) }
    var excluir by remember { mutableStateOf(false) }
    var carregado by remember { mutableStateOf(false) }

    LaunchedEffect(id) {

        viewModel.buscar(id) {
            local = it
            carregado = true
        }
    }

    if (excluir) {

        AlertDialog(
            onDismissRequest = { excluir = false },

            title = {
                Text("Excluir estabelecimento?")
            },

            text = {
                Text("Deseja realmente excluir ${local?.nome}?")
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        local?.let { viewModel.excluir(it) }
                        excluir = false
                        onVoltar()
                    }
                ) {
                    Text("Excluir")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = { excluir = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        containerColor = Color(0xFFF5F6FA),
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do local") },
                navigationIcon = {
                    TextButton(onClick = onVoltar) {
                        Text("Voltar")
                    }
                }
            )
        }
    ) { padding ->

        if (!carregado) {

            CircularProgressIndicator(
                modifier = Modifier.padding(24.dp)
            )

        } else if (local == null) {

            Text(
                "Estabelecimento não encontrado.",
                modifier = Modifier.padding(24.dp)
            )

        } else {

            val item = local!!

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
            ) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Text(
                            item.nome,
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(
                            item.endereco,
                            color = Color.Gray
                        )

                        Spacer(Modifier.height(16.dp))

                        Text(
                            "Sobre o estabelecimento",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(item.descricao)

                        Spacer(Modifier.height(20.dp))

                        Text(
                            "Recursos de acessibilidade",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            "Rampas: ${if (item.rampa) "Disponível" else "Não informado como disponível"}"
                        )

                        Text(
                            "Piso tátil: ${if (item.pisoTatil) "Disponível" else "Não informado como disponível"}"
                        )

                        Text(
                            "Sinalização sonora: ${if (item.sinalizacaoSonora) "Disponível" else "Não informado como disponível"}"
                        )

                        Text(
                            "Sinalização visual: ${if (item.sinalizacaoVisual) "Disponível" else "Não informado como disponível"}"
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {

                        val atualizado = item.copy(
                            favorito = !item.favorito
                        )

                        viewModel.editar(atualizado)
                        local = atualizado
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (item.favorito)
                            "Remover dos favoritos"
                        else
                            "Adicionar aos favoritos"
                    )
                }

                OutlinedButton(
                    onClick = onEditar,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Editar estabelecimento")
                }

                OutlinedButton(
                    onClick = { excluir = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Excluir estabelecimento")
                }
            }
        }
    }
}