package com.defenseunicorns.flyaware

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.defenseunicorns.flyaware.data.local.IcaoDao
import com.defenseunicorns.flyaware.data.local.IcaoEntity
import com.defenseunicorns.flyaware.ui.FlyAwareViewModel
import com.defenseunicorns.flyaware.ui.IcaoInputDialog
import com.defenseunicorns.flyaware.ui.MainColumn
import com.defenseunicorns.flyaware.ui.theme.FlyAwareTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var icaoDao: IcaoDao

    private val viewModel: FlyAwareViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlyAwareTheme {
                var showDialog by remember { mutableStateOf(false) }
                var icaoInput by remember { mutableStateOf("") }

                val onDeleteConfirmed: (String) -> Unit = { icao ->
                    lifecycleScope.launch {
                        icaoDao.delete(IcaoEntity(icao))
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(onClick = { showDialog = true }) {
                            Icon(Icons.Default.Add, contentDescription = "Add ICAO")
                        }
                    }
                ) { innerPadding ->
                    val icaoList by viewModel.icaoCodes.collectAsState(emptyList())

                    MainColumn(
                        padding = innerPadding,
                        icaoList = icaoList,
                        onDeleteConfirmed = onDeleteConfirmed
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