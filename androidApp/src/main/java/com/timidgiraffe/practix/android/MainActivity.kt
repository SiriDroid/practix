package com.timidgiraffe.practix.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.timidgiraffe.practix.AppDatabase
import com.timidgiraffe.practix.android.ui.PracticeScreen
import com.timidgiraffe.practix.android.ui.PractixApp
import com.timidgiraffe.practix.data.PracticeRepositoryImpl
import com.timidgiraffe.practix.domain.PracticeRepository
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val practiceRepository: PracticeRepository by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                PractixApp(practiceRepository)
            }
        }
    }
}
