package com.defenseunicorns.flyaware

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.defenseunicorns.flyaware.data.local.IcaoEntity
import com.defenseunicorns.flyaware.data.local.IcaoDao
import com.defenseunicorns.flyaware.ui.IcaoInputDialog
import com.defenseunicorns.flyaware.ui.theme.FlyAwareTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var icaoDao: IcaoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlyAwareTheme {
                var showDialog by remember { mutableStateOf(false) }
                var icaoInput by remember { mutableStateOf("") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(onClick = { showDialog = true }) {
                            Icon(Icons.Default.Add, contentDescription = "Add ICAO")
                        }
                    }
                ) { innerPadding ->
                    Text(
                        text = "Welcome to FlyAware",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )

                    // Input dialog called up by the FAB
                    IcaoInputDialog(
                        showDialog = showDialog,
                        icaoInput = icaoInput,
                        onInputChange = { icaoInput = it },
                        onConfirm = {
                            lifecycleScope.launch {
                                if (icaoInput.length == 4) {
                                    Log.d("Main", "adding new ICAO $icaoInput")
                                    icaoDao.insert(IcaoEntity(icaoInput.uppercase()))
                                }
                                showDialog = false
                                icaoInput = ""
                            }
                        },
                        onDismiss = { showDialog = false }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FlyAwarePreview() {
    FlyAwareTheme {
    }
}