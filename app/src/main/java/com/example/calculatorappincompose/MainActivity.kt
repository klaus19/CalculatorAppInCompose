package com.example.calculatorappincompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorappincompose.components.InputField
import com.example.calculatorappincompose.ui.theme.CalculatorAppInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             MyApp {
                // TopHeader()
                 MainContent()
             }

        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){

        CalculatorAppInComposeTheme {
            Surface(color = MaterialTheme.colors.background){
                content()
            }
        }

    }



@Composable
fun TopHeader(totalPerPerson:Double=134.0){

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(top = 10.dp)
        .clip(
            shape = CircleShape.copy(
                all = CornerSize(12.dp)
            )
        ),
        color = Color(0xFF546DC5)
      //  .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))))
    )  {
      Column(
          modifier=Modifier.padding(12.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
      ) {
          val total = "%2f".format(totalPerPerson)
          Text(text = "Total Per Person",
              style = MaterialTheme.typography.h5
          )
          Text(text = "$$total",
          style = MaterialTheme.typography.h4,
          fontWeight = FontWeight.ExtraBold)
      }    
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainContent(){
    val totalBillState = remember {
        mutableStateOf("")
    }

    val validstate = remember(totalBillState.value) {
               totalBillState.value.trim().isNotEmpty()
    }

    val keyboaardcontoler = LocalSoftwareKeyboardController.current
   Surface(
       modifier = Modifier
           .padding(2.dp)
           .fillMaxWidth(),
         shape = RoundedCornerShape(corner = CornerSize(8.dp)),
         border = BorderStroke(width = 1.dp,color=Color.LightGray)
         ) {
        Column() {
           InputField(valueState = totalBillState,
               labelId = "Enter Bill",
               enabled = true ,
               isSingleLine =true,
           onAction = KeyboardActions {
                   if(!validstate) return@KeyboardActions
               //TODO @onValueChange

               keyboaardcontoler?.hide()
           }
           )
        }
   }

}







@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorAppInComposeTheme {
           MyApp {
               TopHeader()
           }
    }
}