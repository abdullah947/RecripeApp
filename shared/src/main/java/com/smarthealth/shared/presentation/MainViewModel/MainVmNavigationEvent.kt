package com.smarthealth.shared.presentation.MainViewModel

sealed class MainVmNavigationEvent {
    sealed class MakeRecipe {
        data object Feature : MainVmNavigationEvent()
    }
    sealed class ModifyRecipe {
        data object Feature : MainVmNavigationEvent()
    }
}