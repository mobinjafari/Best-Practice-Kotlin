package org.lotka.bp.presentation


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.datastore.SettingsDataStore
import org.lotka.bp.presentation.util.ConnectivityManager
import org.lotka.bp.util.isBiometricSupported
import org.lotka.bp.util.showBiometricPrompt
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity()  {

    @Inject
    lateinit var connectivityManager: ConnectivityManager
    @Inject
    lateinit var settingsDataStore: SettingsDataStore


    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(lifecycleOwner = this@MainActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(lifecycleOwner = this@MainActivity)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isBiometricSupported(this@MainActivity )) {
            showBiometricPrompt(this@MainActivity)
        } else {
            // Handle the case when biometric authentication is not supported
        }


        setContent {
            MobinApp(
                activity = this@MainActivity,
                connectivityManager = connectivityManager,
                settingsDataStore = settingsDataStore
            )
        }

    }


}


