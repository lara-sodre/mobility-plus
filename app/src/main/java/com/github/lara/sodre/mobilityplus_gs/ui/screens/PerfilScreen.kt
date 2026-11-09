package com.github.lara.sodre.mobilityplus_gs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.lara.sodre.mobilityplus_gs.data.PreferenciasAcessibilidade
import com.github.lara.sodre.mobilityplus_gs.viewmodel.LocalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    viewModel: LocalViewModel,
    onVoltar: () -> Unit
) {

    val preferenciasSalvas by viewModel.preferencias.collectAsState()

    var fisica by rememberSaveable { mutableStateOf(false) }
    var visual by rememberSaveable { mutableStateOf(false) }
    var auditiva by rememberSaveable { mutableStateOf(false) }
    var intelectual by rememberSaveable { mutableStateOf(false) }

    var cadeiraRodas by rememberSaveable { mutableStateOf(false) }
    var bengala by rememberSaveable { mutableStateOf(false) }
    var mobilidadeReduzida by rememberSaveable { mutableStateOf(false) }

    var salvando by remember { mutableStateOf(false) }
    var mensagem by remember { mutableStateOf<String?>(null) }
    var sucesso by remember { mutableStateOf(false) }

    // Atualiza os campos quando o Room disponibiliza os dados.
    LaunchedEffect(preferenciasSalvas) {

        fisica = preferenciasSalvas.fisica
        visual = preferenciasSalvas.visual
        auditiva = preferenciasSalvas.auditiva
        intelectual = preferenciasSalvas.intelectual

        cadeiraRodas = preferenciasSalvas.cadeiraRodas
        bengala = preferenciasSalvas.bengala
        mobilidadeReduzida = preferenciasSalvas.mobilidadeReduzida
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Meu perfil")
                },
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
                text = "Perfil de acessibilidade",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Personalize suas necessidades para encontrar locais mais adequados."
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Tipo de deficiência",
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
                        mensagem = null
                    }

                    PerfilOpcao("Deficiência visual", visual) {
                        visual = it
                        mensagem = null
                    }

                    PerfilOpcao("Deficiência auditiva", auditiva) {
                        auditiva = it
                        mensagem = null
                    }

                    PerfilOpcao("Deficiência intelectual", intelectual) {
                        intelectual = it
                        mensagem = null
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Dispositivos e necessidades",
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
                        mensagem = null
                    }

                    PerfilOpcao(
                        "Utilizo bengala ou muletas",
                        bengala
                    ) {
                        bengala = it
                        mensagem = null
                    }

                    PerfilOpcao(
                        "Tenho mobilidade reduzida",
                        mobilidadeReduzida
                    ) {
                        mobilidadeReduzida = it
                        mensagem = null
                    }
                }
            }

            mensagem?.let { texto ->

                Spacer(Modifier.height(12.dp))

                Text(
                    text = texto,
                    color = if (sucesso) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {

                    salvando = true
                    mensagem = null

                    val preferencias = PreferenciasAcessibilidade(
                        id = 1,
                        fisica = fisica,
                        visual = visual,
                        auditiva = auditiva,
                        intelectual = intelectual,
                        cadeiraRodas = cadeiraRodas,
                        bengala = bengala,
                        mobilidadeReduzida = mobilidadeReduzida
                    )

                    viewModel.salvarPreferencias(preferencias) { resultado ->

                        salvando = false
                        sucesso = resultado

                        mensagem = if (resultado) {
                            "Preferências salvas com sucesso!"
                        } else {
                            "Não foi possível salvar as preferências. Tente novamente."
                        }
                    }
                },
                enabled = !salvando,
                modifier = Modifier.fillMaxWidth()
            ) {

                if (salvando) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )

                } else {

                    Text("Salvar preferências")
                }
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = titulo,
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
