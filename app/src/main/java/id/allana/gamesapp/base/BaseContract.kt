package id.allana.gamesapp.base

interface BaseContract {

    interface BaseView {
        fun observeData()
        fun showData(isVisible: Boolean)
        fun showLoading(isLoading: Boolean)
        fun showError(isError: Boolean, message: String? = null)
    }

    interface BaseViewModel
    interface BaseRepository
}