package com.pichurchyk.testeventapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.pichurchyk.testeventapp.R
import com.pichurchyk.testeventapp.utils.network.NetworkConnectivityObserver
import com.pichurchyk.testeventapp.utils.network.NetworkListener
import kotlinx.coroutines.launch

abstract class BaseFragment<VBinding : ViewBinding> : Fragment() {

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding

    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    open fun setUpViews() {}

    open fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            NetworkConnectivityObserver(requireContext())
                .observe()
                .collect { status ->
                    when (status) {
                        NetworkListener.Status.Losing,
                        NetworkListener.Status.Lost,
                        NetworkListener.Status.Unavailable,
                        -> {
                            showMessage(R.string.error_no_connection)
                        }

                        NetworkListener.Status.Available -> {
                            snackbar?.dismiss()
                        }
                    }
                }
        }
    }

    private fun init() {
        binding = getViewBinding()
    }

    fun showMessage(
        messageResId: Int = R.string.default_error,
    ) {
        snackbar = Snackbar.make(
            binding.root.context,
            binding.root,
            getString(messageResId),
            Snackbar.LENGTH_INDEFINITE,
        ).also {
            it.show()
        }
    }

    fun showMessage(
        messageText: String = getString(R.string.default_error),
    ) {
        snackbar = Snackbar.make(
            binding.root.context,
            binding.root,
            messageText,
            Snackbar.LENGTH_INDEFINITE,
        ).also {
            it.show()
        }
    }

    fun showMessageWithAction(
        messageResId: Int = R.string.default_error,
        actionBtnText: Int = R.string.ok,
        onActionBtnClick: () -> Unit,
    ) {
        snackbar = Snackbar.make(
            binding.root.context,
            binding.root,
            getString(messageResId),
            Snackbar.LENGTH_INDEFINITE,
        )
            .setAction(getString(actionBtnText)) {
                onActionBtnClick.invoke()
            }.also {
                it.show()
            }
    }

    fun showMessageWithAction(
        messageString: String = getString(R.string.default_error),
        actionBtnText: String = getString(R.string.ok),
        onActionBtnClick: () -> Unit,
    ) {
        snackbar = Snackbar.make(
            requireView(),
            messageString,
            Snackbar.LENGTH_INDEFINITE,
        )
            .setAction(actionBtnText) {
                onActionBtnClick.invoke()
            }.also {
                it.show()
            }
    }
}
