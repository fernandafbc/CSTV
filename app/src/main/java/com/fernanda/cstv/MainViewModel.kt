package com.fernanda.cstv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.fernanda.navigation.core.NavigationCommand
import com.fernanda.navigation.core.NavigationManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {
    private val navigationManager: NavigationManager by inject()
    var showSplashScreen = true

    init {
        viewModelScope.launch {
            delay(2_000)
            showSplashScreen = false
        }
    }

    fun initNavigation(
        navHostController: NavHostController
    ) {
        viewModelScope.launch {
            navigationManager.commands.collect { command ->
                when (command) {
                    NavigationCommand.NavigateUp -> navHostController.navigateUp()
                    NavigationCommand.PopStackBack -> navHostController.popBackStack()
                    is NavigationCommand.Navigate -> navHostController.navigate(
                        route = command.destination,
                        navOptions = command.navOptions
                    )
                }
            }
        }
    }
}