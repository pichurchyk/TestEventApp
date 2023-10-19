package com.pichurchyk.testeventapp.presentation.video

import android.net.Uri
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pichurchyk.testeventapp.databinding.FragmentVideoBinding
import com.pichurchyk.testeventapp.presentation.BaseFragment

class VideoFragment :
    BaseFragment<FragmentVideoBinding>() {

    override fun getViewBinding(): FragmentVideoBinding =
        FragmentVideoBinding.inflate(layoutInflater)

    private val args by navArgs<VideoFragmentArgs>()
    private val videoUrl by lazy {
        args.videoUrl
    }

    override fun setUpViews() {
        super.setUpViews()

        with(binding) {
            btnClose.setOnClickListener {
                video.setMediaController(null)
                video.stopPlayback()
                findNavController().popBackStack()
            }

            val mediaController = CustomMediaController(root.context)
            video.setMediaController(mediaController)
            mediaController.setAnchorView(video)

            val videoUri = Uri.parse(videoUrl)
            video.setVideoURI(videoUri)
            video.start()
        }
    }
}
