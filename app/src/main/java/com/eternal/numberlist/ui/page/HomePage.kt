package com.eternal.numberlist.ui.page

import android.os.CombinedVibration
import android.os.VibrationEffect
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eternal.numberlist.App.Companion.vibrator
import com.eternal.numberlist.R
import com.eternal.numberlist.database.entity.NumberItemData
import com.eternal.numberlist.ui.utils.brushList
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val viewModel: HomeViewModel = viewModel()
    val listState = viewModel.listState.collectAsState()
    val numberList =
        listState.value.collectAsState(initial = mutableListOf())

    var showDialog by remember {
        mutableStateOf(false)
    }

    rememberSystemUiController().apply {
        setNavigationBarColor(Color.Transparent, darkIcons = !isSystemInDarkTheme())
        setStatusBarColor(MaterialTheme.colorScheme.primary, darkIcons = !isSystemInDarkTheme())
    }
    Scaffold(topBar = { TopBar(modifier = Modifier.statusBarsPadding()) }, floatingActionButton = {
        FloatingButton(modifier = Modifier.navigationBarsPadding()) {
            showDialog = true
        }
    }) { innerPadding ->
        println(innerPadding)
        NumberList(
            numbers = numberList, modifier = Modifier.padding(innerPadding)
        ) { id ->
            viewModel.removeById(id)
        }
        if (showDialog) {
            AddNumberDialog(onConfirm = { inputString, brushIndex ->
                val number = try {
                    inputString.toInt()
                } catch (_: Exception) {
                    114514
                }
                viewModel.insert(NumberItemData(number = number, brushIndex = brushIndex))
                showDialog = false
            }, onDismiss = {
                showDialog = false
            })
        }
    }
}


@Composable
private fun TopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 15.dp),
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}


@Composable
private fun FloatingButton(modifier: Modifier = Modifier, onClicked: () -> Unit) {
    FloatingActionButton(
        onClick = {
            onClicked()
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.size(64.dp),
        shape = RoundedCornerShape(size = 50.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_a_number),
        )
    }
}


@Composable
private fun NumberList(
    numbers: State<List<NumberItemData>>, modifier: Modifier = Modifier, onItemLongPress: (Long) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items = numbers.value, key = {
            it.id
        }) { number ->
            NumberCard(
                number = number, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                onItemLongPress(number.id)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NumberCard(
    number: NumberItemData,
    modifier: Modifier = Modifier,
    onLongPress: () -> Unit
) {
    Card(
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures(onLongPress = {
                onLongPress()
                vibrator.vibrate(
                    CombinedVibration.createParallel(
                        VibrationEffect.createOneShot(
                            100, 10
                        )
                    )
                )
            })
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .background(brush = brushList[number.brushIndex])
        ) {
            Text(
                text = number.number.toString(),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 20.dp)
            )
        }
    }
}


@Composable
fun AddNumberDialog(
    modifier: Modifier = Modifier,
    onConfirm: (String, Int) -> Unit,
    onDismiss: () -> Unit
) {
    var inputString by remember {
        mutableStateOf("")
    }
    val (brushIndex, onSelectBrush) = remember {
        mutableStateOf(0)
    }
    AlertDialog(modifier = modifier,
        onDismissRequest = { /*TODO*/ },
        properties = DialogProperties(),
        icon = {
            Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
        },
        title = { Text(text = stringResource(id = R.string.add_a_number)) },
        confirmButton = {
            TextButton(onClick = { onConfirm(inputString, brushIndex) }) {
                Text(text = stringResource(id = R.string.add))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        text = {
            Column {
                OutlinedTextField(
                    value = inputString, onValueChange = {
                        inputString = it
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.number),
                            color = LocalContentColor.current.copy(0.6f)
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    for ((index, brush) in brushList.withIndex()) {
                        Column {
                            Icon(
                                painter = BrushPainter(brush), contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(RoundedCornerShape(30.dp))
                                    .clickable { onSelectBrush(index) },
                                tint = Color.Unspecified
                            )
                            if (index == brushIndex) {
                                Icon(
                                    imageVector = Icons.Filled.Check, contentDescription = null,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }
                }

            }
        }
    )
}


@Preview
@Composable
fun DefaultPreview() {
    HomePage()
}