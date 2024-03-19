package fr.arrows.leaguepicker.common.viewmodel

import androidx.lifecycle.ViewModel
import fr.arrows.leaguepicker.common.model.snackbar.LeaguepickerSnackbarData
import fr.arrows.leaguepicker.common.model.snackbar.SnackbarType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel : ViewModel() {

    private val _snackbarState = MutableStateFlow<LeaguepickerSnackbarData?>(null)
    val snackbarState = _snackbarState.asStateFlow()

    protected fun showSnackBar(message: String, type: SnackbarType = SnackbarType.Info) {
        _snackbarState.value = LeaguepickerSnackbarData(message, type)
    }

    fun snackbarDisplayed() {
        _snackbarState.value = null
    }

}
