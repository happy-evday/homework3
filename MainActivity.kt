package com.example.diceroller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceWithButtonAndImage(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DiceWithButtonAndImage(name: String, modifier:
Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)) {
    Column(modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally){
        var result by remember { mutableStateOf(1) }
        var img = when(result){
            1 ->R.drawable.img1
            2 ->R.drawable.img2
            3 ->R.drawable.img3
            4 ->R.drawable.img4
            5 ->R.drawable.img5
            else  -> R.drawable.img6
        }


        val description = when(result){
            1 -> stringResource(R.string.image_1_description)
            2 -> stringResource(R.string.image_2_description)
            3 -> stringResource(R.string.image_3_description)
            4 -> stringResource(R.string.image_4_description)
            5 -> stringResource(R.string.image_5_description)
            else -> stringResource(R.string.image_6_description)
        }

        Image(
            painter = painterResource(id = img),
            contentDescription = result.toString()
            
        )
        Spacer(modifier= Modifier.height(16.dp))

        // 使用Row来水平排列两个按钮
        Row {
            // Previous按钮
            Button(
                onClick = {
                    result = if (result == 1) 6 else result - 1
                }
            ) {
                Text(stringResource(R.string.previous_button))
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Next按钮
            Button(
                onClick = {
                    result = if (result == 6) 1 else result + 1
                }
            ) {
                Text(stringResource(R.string.next_button))
            }
        }

        // 修改TextField，显示图片编号和描述
        TextField(
            value = "$result，$description",
            onValueChange = {},
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceRollerTheme {
        DiceWithButtonAndImage("Android")
    }
}