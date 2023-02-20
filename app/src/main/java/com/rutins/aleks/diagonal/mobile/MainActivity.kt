package com.rutins.aleks.diagonal.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rutins.aleks.diagonal.mobile.ui.theme.DiagonalMobileTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tests = arrayOf(
            "Test 1",
            "Test 2"
        )
        setContent {
            DiagonalMobileTheme {
                var currentTest by remember { mutableStateOf(tests[0]) }
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                // A surface container using the 'background' color from the theme

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(Modifier.height(12.dp))
                            tests.forEach {
                                NavigationDrawerItem(
                                    label = { Text(it) },
                                    selected = it == currentTest,
                                    onClick = {
                                        scope.launch { drawerState.close() }
                                        currentTest = it
                                    },
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                        }
                    },
                    content = {
                        Scaffold(
                            topBar = {
                                CenterAlignedTopAppBar(
                                    title = { Text(currentTest) },
                                    navigationIcon = {
                                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                                        }
                                    }
                                )
                            },
                            content = { innerPadding ->
                                LazyColumn(contentPadding = innerPadding) {
                                    
                                }
                            }
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiagonalMobileTheme {
        Greeting("World")
    }
}