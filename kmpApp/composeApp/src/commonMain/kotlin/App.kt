
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.PostNewLink
import data.models.LinkModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    var s by remember {
        mutableStateOf("")
    }
    var text by remember {
        mutableStateOf("")
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier){
        Card(modifier = Modifier.padding(15.dp)) {
            Column {
                Row{
                    OutlinedTextField(value = text, onValueChange = {
                        text = it
                    },
                        placeholder = {
                            Text("Ссылка")
                        })
                    val q = rememberCoroutineScope()
                    Button(onClick = {
                        q.launch {
                            s = PostNewLink.post(LinkModel(text)).link
                        }
                    }){
                        Text("Сократить")
                    }
                }
                Text(text = s)
            }


        }


    }

}
