package id.allana.gamesapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GenericViewModelFactory(private val viewModel: ViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel::class.java)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}