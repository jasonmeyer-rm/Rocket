package com.app.rocket.feature.games.ui.dialog

interface GameDialogContract {

    interface View {
        fun dismissDialog()
    }

    interface Presenter {
        fun attachView(view: View)
        fun onClosedButtonClicked()
    }
}
