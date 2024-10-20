import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToe()
        }
    }
}

@Composable
fun TicTacToe() {
    var board by remember { mutableStateOf(Array(3) { Array(3) { "" } }) }
    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0..2) {
            Row {
                for (j in 0..2) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.LightGray)
                            .padding(4.dp)
                            .clickable(enabled = board[i][j].isEmpty() && winner == null) {
                                board[i][j] = currentPlayer
                                currentPlayer = if (currentPlayer == "X") "O" else "X"
                                winner = checkWinner(board)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        BasicText(
                            text = board[i][j],
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
        if (winner != null) {
            BasicText(
                text = "Winner: $winner",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 24.sp),
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

fun checkWinner(board: Array<Array<String>>): String? {
    for (i in 0..2) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0].isNotEmpty())
            return board[i][0]
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i].isNotEmpty())
            return board[0][i]
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0].isNotEmpty())
        return board[0][0]
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2].isNotEmpty())
        return board[0][2]
    return null
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToe()
}
