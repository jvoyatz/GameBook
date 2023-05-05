package gr.jvoyatz.gamebook.ui.custom.views

/**
 * Contract for the custom view [LoaderView]
 */
interface LoaderViewInterview {
    fun showLoading()

    fun showError()

    fun showNoData()

    fun setRetryListener(clickHandler: () -> Unit)
}