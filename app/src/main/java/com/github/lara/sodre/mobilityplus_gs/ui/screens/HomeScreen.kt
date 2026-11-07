package com.github.lara.sodre.mobilityplus_gs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.lara.sodre.mobilityplus_gs.ui.theme.AzulEscuro
import com.github.lara.sodre.mobilityplus_gs.ui.theme.AzulPrincipal
import com.github.lara.sodre.mobilityplus_gs.ui.theme.Branco
import com.github.lara.sodre.mobilityplus_gs.ui.theme.Fundo
import com.github.lara.sodre.mobilityplus_gs.ui.theme.TextoSecundario

@Composable
fun HomeScreen(
    onLocais: () -> Unit,
    onCadastro: () -> Unit,
    onPerfil: () -> Unit
) {

    Scaffold(
        containerColor = Fundo
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {

            Text(
                "Mobility+",
                style = MaterialTheme.typography.headlineLarge,
                color = AzulEscuro
            )

            Text(
                "MOBILIDADE SEM BARREIRAS",
                style = MaterialTheme.typography.labelSmall,
                color = TextoSecundario
            )

            Spacer(Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AzulPrincipal
                ),
                shape = RoundedCornerShape(18.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        "Explore sem barreiras!",
                        color = Branco,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "Encontre lugares que respeitam suas necessidades de acessibilidade.",
                        color = Branco
                    )

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = onLocais,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Branco,
                            contentColor = AzulEscuro
                        )
                    ) {
                        Text("Explorar locais")
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                "Categorias de acessibilidade",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(12.dp))

            CategoriaCard(
                "Acessibilidade física",
                "Rampas e elevadores"
            )

            CategoriaCard(
                "Acessibilidade visual",
                "Piso tátil e sinalização"
            )

            CategoriaCard(
                "Acessibilidade auditiva",
                "Sinalização e recursos visuais"
            )

            CategoriaCard(
                "Outras necessidades",
                "Adaptações específicas"
            )

            Spacer(Modifier.height(16.dp))

            OutlinedButton(
                onClick = onCadastro,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cadastrar estabelecimento")
            }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = onPerfil,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Meu perfil de acessibilidade")
            }
        }
    }
}

@Composable
fun CategoriaCard(
    titulo: String,
    descricao: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Branco
        ),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            Text(
                titulo,
                color = AzulEscuro,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                descricao,
                color = TextoSecundario,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
