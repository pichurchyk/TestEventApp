package com.pichurchyk.testeventapp.presentation.schedule

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.SimpleItemAnimator
import com.pichurchyk.testeventapp.R
import com.pichurchyk.testeventapp.databinding.FragmentScheduleBinding
import com.pichurchyk.testeventapp.domain.entity.schedule.ScheduleEventEntity
import com.pichurchyk.testeventapp.presentation.BaseFragment
import com.pichurchyk.testeventapp.presentation.events.viewState.LoaderBig
import com.pichurchyk.testeventapp.presentation.schedule.adapter.ScheduleAdapter
import com.pichurchyk.testeventapp.utils.visibleIf
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ScheduleFragment :
    BaseFragment<FragmentScheduleBinding>(),
    ScheduleAdapter.EventClickListener {

    private val viewModel: ScheduleViewModel by viewModel {
        parametersOf()
    }

    override fun getViewBinding(): FragmentScheduleBinding =
        FragmentScheduleBinding.inflate(layoutInflater)

    private val adapter: ScheduleAdapter by lazy {
        ScheduleAdapter(this)
    }

    override fun setUpViews() {
        super.setUpViews()

        with(binding) {
            rvEvents.adapter = adapter
            (rvEvents.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                false
        }
    }

    override fun observeData() {
        super.observeData()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                with(binding) {
                    state.loader.let { loader ->
                        when (loader) {
                            is LoaderBig -> {
                                progressLoader.visibleIf(loader.isVisible)
                            }
                        }
                    }

                    state.exception?.let {
                        showMessageWithAction(
                            it.exception.message ?: getString(R.string.default_error),
                            getString(R.string.retry),
                        ) {
                            viewModel.loadEvents()
                        }
                    }

                    state.events.let { events ->
                        tvEmpty.visibleIf(events.isEmpty() && !state.loader.isVisible)
                        adapter.submitList(events)
                    }
                }
            }
        }
    }

    override fun onEventClick(event: ScheduleEventEntity) {
        Log.d("AdapterEvent", "${event.id} clicked")
    }
}
