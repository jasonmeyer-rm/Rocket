package com.app.rocket.feature.games.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.app.rocket.R
import com.app.rocket.databinding.DialogFragmentGameBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class GameDialogFragment : DialogFragment(), GameDialogContract.View {
    private lateinit var presenter: GameDialogContract.Presenter
    private lateinit var dialog: AlertDialog
    private var _binding: DialogFragmentGameBinding? = null
    private var gameImageUrlArgs: String? = null
    private var gameNameArgs: String? = null
    private var gameDescArgs: String? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogFragmentGameBinding.inflate(LayoutInflater.from(context))

        dialog = AlertDialog.Builder(requireActivity()).setView(binding.root).create()

        gameImageUrlArgs = requireArguments().getString(GAME_IMAGE_URL_ARGS).orEmpty()
        gameNameArgs = requireArguments().getString(GAME_NAME_ARGS).orEmpty()
        gameDescArgs = requireArguments().getString(GAME_DESC_ARGS).orEmpty()

        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = GameDialogPresenter().also { it.attachView(this) }

        initBinding()
    }

    private fun initBinding() {

        with(binding) {

          Glide.with(requireActivity())
                .load(gameImageUrlArgs)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_loading)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)

            name.text = gameNameArgs?.ifEmpty { getString(R.string.game_no_title) }
            description.text = gameDescArgs?.ifEmpty { getString(R.string.game_no_desc) }

            closeBtn.setOnClickListener {
                presenter.onClosedButtonClicked()
            }
        }
    }

    override fun dismissDialog() {
        dialog.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val GAME_IMAGE_URL_ARGS: String = "gameImageUrlArgs"
        const val GAME_NAME_ARGS: String = "gameNameArgs"
        const val GAME_DESC_ARGS: String = "gameDescArgs"

        fun getInstance() = GameDialogFragment()
    }

}

