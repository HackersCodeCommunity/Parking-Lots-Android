package io.parkinglots.android

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            UI()
        }
    }
}

@Composable
fun UI() {
    var showBottomSheet by remember { mutableStateOf(false) }

//    BottomSheet {
//        showBottomSheet = false
//    }

    Box(
        modifier = with (Modifier){
            fillMaxSize()
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.mapsicle_map),
                    contentScale = ContentScale.Crop
                )
                .clickable {
                    showBottomSheet = true
                }
        })
    {
        var expanded by remember { mutableStateOf(false) }
        // Add padding around our message
        Row(modifier = Modifier.padding(all = 30.dp)) {
            Image(
                painter = painterResource(R.drawable.menu),
                contentDescription = "Menu",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    .clickable {
                        expanded = true
                    }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Saved") },
                    onClick = { /* Handle refresh! */ }
                )
               Divider()
                DropdownMenuItem(
                    text = { Text("History") },
                    onClick = { /* Handle settings! */ }
                )
            }
        }
    }

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BottomSheet(onDismiss: () -> Unit) {
//    val modalBottomSheetState = rememberModalBottomSheetState()
//
//    ModalBottomSheet(
//        onDismissRequest = { onDismiss() },
//        sheetState = modalBottomSheetState,
//        dragHandle = { BottomSheetDefaults.DragHandle() },
//    ) {
//
//    }
//}

@Preview(name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UI()
}