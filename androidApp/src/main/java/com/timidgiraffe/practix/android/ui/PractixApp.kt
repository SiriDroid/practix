package com.timidgiraffe.practix.android.ui

import android.graphics.drawable.Icon
import android.widget.EditText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timidgiraffe.practix.AppDatabase
import com.timidgiraffe.practix.data.PracticeRepositoryImpl
import com.timidgiraffe.practix.domain.Practice
import com.timidgiraffe.practix.domain.PracticeRepository
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject


@Composable
fun PractixApp(practiceRepository: PracticeRepository) {
    var showHistory by remember { mutableStateOf(false) }

    if (showHistory) {
        HistoryScreen(practiceRepository, onShowHistory = { showHistory = false })
    } else {
        PracticeScreen(practiceRepository, onShowHistory = { showHistory = true })
    }
}

@Composable
fun PracticeScreen(practiceRepository: PracticeRepository,
                   onShowHistory: () -> Unit = {}) {
    var count by remember { mutableIntStateOf(0) }
    var saveRequested by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var showHistory by remember { mutableStateOf(false) }

    if(saveRequested) {
        saveRequested = false
        LaunchedEffect(count) {
            if (practiceRepository.sessionExists(text)) {
                practiceRepository.updateSessionCount(text, count.toLong())
            } else {
                practiceRepository.insertSession(text, count.toLong(), "now")
            }
        }
    }


    if(showHistory) {
        showHistory = false
        LaunchedEffect(Unit) {
            onShowHistory()
        }
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Practice",
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(bottom = 50.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Session Name") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row {
            Button(
                onClick = {
                    count++
                }
            ) {
                Text(text = "+")
            }

            Text(
                text = count.toString(),
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically)
            )

            Button(
                onClick = {
                    count--
                }
            ) {
                Text(text = "-")
            }
        }

        Row {
            Button(
                onClick = {
                    count = 0
                }
            ) {
                Text(text = "Reset")
            }

            Button(
                onClick = {
                    saveRequested = true
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = "Save")
            }

            Button(
                onClick = {
                    showHistory = true
                }
            ) {
                Text(text = "History")
            }
        }

    }
}

@Composable
fun HistoryScreen(practixRepository: PracticeRepository, onShowHistory: () -> Unit) {
    var sessions by remember { mutableStateOf(emptyList<Practice>()) }
    var removeRequested by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        sessions = practixRepository.getAllSessions()
        println(sessions)
    }

    if(removeRequested) {
        removeRequested = false
        LaunchedEffect(Unit) {
            practixRepository.removeAllSessions()
            sessions = emptyList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            Button(
                onClick = {
                    onShowHistory()
                }
            ) {
                Text(text = "Back")
            }

            Text(text = "History",
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically))

            Button(
                onClick = {
                   removeRequested = true
                }
            ) {
                Text(text = "Clear")
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            items(sessions.size) { session ->
                Text(text = sessions[session].name + ": " + sessions[session].count.toString() + " times",
                    color = Color.White)
            }
        }
    }

}
