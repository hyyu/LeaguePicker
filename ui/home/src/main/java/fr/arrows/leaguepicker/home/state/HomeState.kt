package fr.arrows.leaguepicker.home.state

data class HomeState(
    val isLoading: Boolean = false
) {
    fun build(block: Builder.() -> Unit) = Builder(HomeState()).apply(block).build()

    class Builder(uiModel: HomeState) {
        var isLoading = uiModel.isLoading

        fun build(): HomeState =
            HomeState(
                isLoading = isLoading
            )
    }
}
