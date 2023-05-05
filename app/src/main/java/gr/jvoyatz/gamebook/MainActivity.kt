package gr.jvoyatz.gamebook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import gr.jvoyatz.gamebook.ui.R.style.Theme_GameBook as AppTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(AppTheme)
        setContentView(R.layout.activity_main)
    }
}