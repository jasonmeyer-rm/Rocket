package com.app.rocket.feature.games.ui.dialog

/*
 * MVP here is a bit much just to close a dialog which will increase codebase & app size.
 * My intention here is to demonstrate different application architectures.
 */
class GameDialogPresenter : GameDialogContract.Presenter {
    private var view: GameDialogContract.View? = null

    override fun attachView(view: GameDialogContract.View) {
        this.view = view
    }

    override fun onClosedButtonClicked() {
        view?.dismissDialog()
    }
}

