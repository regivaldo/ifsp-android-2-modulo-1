package br.edu.ifsp.preferencias

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import br.edu.ifsp.preferencias.ui.theme.PreferenciasUsuarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var temaSelecionado by remember { mutableStateOf("Claro") }
            val darkTheme = temaSelecionado == "Escuro"

            PreferenciasUsuarioTheme(darkTheme = darkTheme) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPreferencias(
                        temaSelecionado = temaSelecionado,
                        onTemaChange = { temaSelecionado = it },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaPreferencias(
    temaSelecionado: String,
    onTemaChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var nome by remember { mutableStateOf("") }
    var notificacoesAtivadas by remember { mutableStateOf(true) }
    var nivelExperiencia by remember { mutableFloatStateOf(5f) }

    val context = LocalContext.current
    val opcoesTema = listOf("Claro", "Escuro")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Cadastro de Preferências",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do usuário") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Seleção de tema
        Text(
            text = "Tema do aplicativo:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.selectableGroup()) {
            opcoesTema.forEach { tema ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (temaSelecionado == tema),
                            onClick = { onTemaChange(tema) },
                            role = Role.RadioButton
                        )
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (temaSelecionado == tema),
                        onClick = null
                    )
                    Text(
                        text = tema,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Switch de notificações
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Notificações",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = notificacoesAtivadas,
                onCheckedChange = { notificacoesAtivadas = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Slider de nível de experiência
        Text(
            text = "Nível de experiência: ${nivelExperiencia.toInt()}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Slider(
            value = nivelExperiencia,
            onValueChange = { nivelExperiencia = it },
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botão Salvar
        Button(
            onClick = {
                Log.d("Preferencias", "=== Preferências Salvas ===")
                Log.d("Preferencias", "Nome: $nome")
                Log.d("Preferencias", "Tema: $temaSelecionado")
                Log.d("Preferencias", "Notificações: ${if (notificacoesAtivadas) "Ativadas" else "Desativadas"}")
                Log.d("Preferencias", "Nível de experiência: ${nivelExperiencia.toInt()}")

                Toast.makeText(context, "Preferências salvas com sucesso!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar preferências")
        }
    }
}
