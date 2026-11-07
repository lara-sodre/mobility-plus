package com.github.lara.sodre.mobilityplus_gs.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    onVoltar: () -> Unit
) {

    var fisica by rememberSaveable { mutableStateOf(false) }
    var visual by rememberSaveable { mutableStateOf(false) }
    var auditiva by rememberSaveable { mutableStateOf(false) }
    var intelectual by rememberSaveable { mutableStateOf(false) }

    var cadeiraRodas by rememberSaveable { mutableStateOf(false) }
    var bengala by rememberSaveable { mutableStateOf(false) }
    var mobilidadeReduzida by rememberSaveable { mutableStateOf(false) }

    var mensagem by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meu perfil") },
                navigationIcon = {
                    TextButton(onClick = onVoltar) {
                        Text("Voltar")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Text(
                "Perfil de acessibilidade",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(8.dp))

            Text(
                "Personalize suas necessidades para encontrar locais mais adequados."
            )

            Spacer(Modifier.height(24.dp))

            Text(
                "Tipo de deficiência",
                style = MaterialTheme.typography.titleMedium
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {

                    PerfilOpcao("Deficiência física", fisica) {
                        fisica = it
                        mensagem = false
                    }

                    PerfilOpcao("Deficiência visual", visual) {
                        visual = it
                        mensagem = false
                    }

                    PerfilOpcao("Deficiência auditiva", auditiva) {
                        auditiva = it
                        mensagem = false
                    }

                    PerfilOpcao("Deficiência intelectual", intelectual) {
                        intelectual = it
                        mensagem = false
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "Dispositivos e necessidades",
                style = MaterialTheme.typography.titleMedium
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {

                    PerfilOpcao(
                        "Utilizo cadeira de rodas",
                        cadeiraRodas
                    ) {
                        cadeiraRodas = it
                        mensagem = false
                    }

                    PerfilOpcao(
                        "Utilizo bengala ou muletas",
                        bengala
                    ) {
                        bengala = it
                        mensagem = false
                    }

                    PerfilOpcao(
                        "Tenho mobilidade reduzida",
                        mobilidadeReduzida
                    ) {
                        mobilidadeReduzida = it
                        mensagem = false
                    }
                }
            }

            if (mensagem) {
                Text(
                    "Preferências registradas nesta sessão!",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    mensagem = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar preferências")
            }
        }
    }
}

@Composable
fun PerfilOpcao(
    titulo: String,
    selecionado: Boolean,
    onSelecionar: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            titulo,
            modifier = Modifier
                .weight(1f)
                .padding(top = 12.dp)
        )

        Checkbox(
            checked = selecionado,
            onCheckedChange = onSelecionar
        )
    }
}

