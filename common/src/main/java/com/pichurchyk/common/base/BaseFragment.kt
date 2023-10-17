package com.pichurchyk.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.pichurchyk.common.R

abstract class BaseFragment<VBinding : ViewBinding> : Fragment() {

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding

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

    open fun observeData() {}

    private fun init() {
        binding = getViewBinding()
    }

    fun showMessageWithAction(
        messageResId: Int = R.string.default_error,
        actionBtnText: Int = R.string.ok,
        onActionBtnClick: () -> Unit,
    ) {
        Snackbar.make(
            binding.root.context,
            binding.root,
            getString(messageResId),
            Snackbar.LENGTH_INDEFINITE,
        )
            .setAction(getString(actionBtnText)) {
                onActionBtnClick.invoke()
            }
            .show()
    }

    fun showMessageWithAction(
        messageResId: String = getString(R.string.default_error),
        actionBtnText: String = getString(R.string.ok),
        onActionBtnClick: () -> Unit,
    ) {
        Snackbar.make(
            binding.root.context,
            binding.root,
            messageResId,
            Snackbar.LENGTH_INDEFINITE,
        )
            .setAction(actionBtnText) {
                onActionBtnClick.invoke()
            }
            .show()
    }
}
