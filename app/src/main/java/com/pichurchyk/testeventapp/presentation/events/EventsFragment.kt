package com.pichurchyk.testeventapp.presentation.events

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.pichurchyk.testeventapp.R
import com.pichurchyk.testeventapp.databinding.FragmentEventsBinding
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.presentation.BaseFragment
import com.pichurchyk.testeventapp.presentation.events.adapter.EventsAdapter
import com.pichurchyk.testeventapp.utils.visibleIf
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EventsFragment : BaseFragment<FragmentEventsBinding>(), EventsAdapter.EventClickListener {

    private val viewModel: EventsViewModel by viewModel {
        parametersOf()
    }

    override fun getViewBinding(): FragmentEventsBinding =
        FragmentEventsBinding.inflate(layoutInflater)

    private val adapter: EventsAdapter by lazy {
        EventsAdapter(this)
    }

    override fun setUpViews() {
        super.setUpViews()

        binding.rvEvents.adapter = adapter

        viewModel.loadEvents()
    }

    override fun observeData() {
        super.observeData()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                with(binding) {
                    progressLoader.visibleIf(state.isLoading)

                    state.exception?.let {
                        showMessageWithAction(
                            it.exception.message ?: getString(R.string.default_error),
                            getString(R.string.retry),
                        ) {
                            viewModel.loadEvents()
                        }
                    }

                    state.events.let { events ->
                        adapter.submitList(events)
                    }
                }
            }
        }
    }

    override fun onEventClick(event: EventEntity) {
        Log.d("AdapterEvent", "${event.id} clicked")
    }
}
