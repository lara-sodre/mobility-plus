package com.github.lara.sodre.mobilityplus_gs.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.lara.sodre.mobilityplus_gs.data.Local
import com.github.lara.sodre.mobilityplus_gs.viewmodel.LocalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(
    viewModel: LocalViewModel,
    id: Int?,
    onVoltar: () -> Unit
) {

    var nome by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    var rampa by remember { mutableStateOf(false) }
    var piso by remember { mutableStateOf(false) }
    var sonora by remember { mutableStateOf(false) }
    var visual by remember { mutableStateOf(false) }
    var favorito by remember { mutableStateOf(false) }

    var erro by remember { mutableStateOf("") }
    var carregado by remember { mutableStateOf(id == null) }

    LaunchedEffect(id) {

        if (id != null) {

            viewModel.buscar(id) { local ->

                local?.let {
                    nome = it.nome
                    endereco = it.endereco
                    descricao = it.descricao
                    rampa = it.rampa
                    piso = it.pisoTatil
                    sonora = it.sinalizacaoSonora
                    visual = it.sinalizacaoVisual
                    favorito = it.favorito
                }

                carregado = true
            }
        }
    }

    Scaffold(
        containerColor = Color(0xFFF5F6FA),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (id == null) "Novo estabelecimento"
                        else "Editar estabelecimento"
                    )
                },
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

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {

                Text(
                    "Informações do local",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = nome,
                    onValueChange = {
                        nome = it
                        erro = ""
                    },
                    label = { Text("Nome do estabelecimento *") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(Modifier.height(10.dp))

                OutlinedTextField(
                    value = endereco,
                    onValueChange = {
                        endereco = it
                        erro = ""
                    },
                    label = { Text("Endereço *") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(Modifier.height(10.dp))

                OutlinedTextField(
                    value = descricao,
                    onValueChange = { descricao = it },
                    label = { Text("Descrição do local") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    minLines = 3
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    "Condições de acessibilidade",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    "Selecione os recursos disponíveis no local.",
                    color = Color.Gray
                )

                Spacer(Modifier.height(12.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        LinhaCheckbox(
                            "Possui rampa",
                            rampa
                        ) { rampa = it }

                        LinhaCheckbox(
                            "Possui piso tátil",
                            piso
                        ) { piso = it }

                        LinhaCheckbox(
                            "Possui sinalização sonora",
                            sonora
                        ) { sonora = it }

                        LinhaCheckbox(
                            "Possui sinalização visual",
                            visual
                        ) { visual = it }
                    }
                }

                Spacer(Modifier.height(12.dp))

                if (id != null) {
                    LinhaCheckbox(
                        "Manter como favorito",
                        favorito
                    ) { favorito = it }
                }

                if (erro.isNotEmpty()) {

                    Text(
                        erro,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = {

                        if (nome.isBlank() || endereco.isBlank()) {

                            erro = "Nome e endereço são obrigatórios."

                        } else {

                            val local = Local(
                                id = id ?: 0,
                                nome = nome.trim(),
                                endereco = endereco.trim(),
                                descricao = descricao.trim(),
                                rampa = rampa,
                                pisoTatil = piso,
                                sinalizacaoSonora = sonora,
                                sinalizacaoVisual = visual,
                                favorito = favorito
                            )

                            if (id == null) {
                                viewModel.cadastrar(local)
                            } else {
                                viewModel.editar(local)
                            }

                            onVoltar()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5369B5)
                    )
                ) {
                    Text("Salvar estabelecimento")
                }
            }
        }
    }
}

@Composable
fun LinhaCheckbox(
    texto: String,
    selecionado: Boolean,
    onSelecionar: (Boolean) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {

        Checkbox(
            checked = selecionado,
            onCheckedChange = onSelecionar
        )

        Text(
            texto,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}