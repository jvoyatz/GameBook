package gr.jvoyatz.gamebook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import gr.jvoyatz.gamebook.libraries.ui.R.style.Theme_GameBook as AppTheme


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(AppTheme)
        setContentView(R.layout.activity_main)
    }
}