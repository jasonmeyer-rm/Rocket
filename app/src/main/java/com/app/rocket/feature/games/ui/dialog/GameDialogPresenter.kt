package com.app.rocket.feature.games.ui.dialog

class GameDialogPresenter : GameDialogContract.Presenter {
    private var view: GameDialogContract.View? = null

    override fun attachView(view: GameDialogContract.View) {
        this.view = view
    }

    override fun onClosedButtonClicked() {
        view?.dismissDialog()
    }
}

