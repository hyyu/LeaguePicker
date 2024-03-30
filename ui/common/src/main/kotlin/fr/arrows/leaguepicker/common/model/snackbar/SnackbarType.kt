package fr.arrows.leaguepicker.common.model.snackbar

sealed class SnackbarType(val level: SnackbarLevel) {
    data object Validation: SnackbarType(SnackbarLevel.VALIDATION)
    data object Error: SnackbarType(SnackbarLevel.ERROR)
    data object Info: SnackbarType(SnackbarLevel.INFO)

    companion object {
        fun mapLevelToSnackbarType(level: String?) = level?.let {
            when (it) {
                SnackbarLevel.VALIDATION.name -> Validation
                SnackbarLevel.ERROR.name -> Error
                else -> Info
            }
        } ?: Info
    }

}

enum class SnackbarLevel {
    VALIDATION, ERROR, INFO
}
