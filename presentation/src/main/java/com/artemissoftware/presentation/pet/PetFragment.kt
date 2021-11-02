package com.artemissoftware.presentation.pet

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.artemissoftware.presentation.R
import com.artemissoftware.presentation.databinding.FragmentPetBinding
import com.bumptech.glide.RequestManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import javax.inject.Inject
import android.content.Context
import androidx.activity.result.contract.ActivityResultContract
import com.artemissoftware.util.imageOptions
import com.theartofdev.edmodo.cropper.CropImage


@AndroidEntryPoint
class PetFragment : Fragment(R.layout.fragment_pet) {


    @Inject
    lateinit var glide: RequestManager

    private var _binding: FragmentPetBinding? = null
    private val binding get() = _binding!!


    lateinit var fileName: String
    lateinit var imageUri: Uri

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        _binding = FragmentPetBinding.bind(view)
        initOnClicklistener()

    }

    private fun initOnClicklistener(){

        binding.imgPet.setOnClickListener {
            imageOptions ({ selectImageFromGallery() }, { takeImage() })
        }
    }

    private val selectImageFromGalleryResult = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            cropLaucher.launch(uri)
        }
    }

    private fun selectImageFromGallery() = selectImageFromGalleryResult.launch("image/*")


    private var latestTmpUri: Uri? = null
    private val takeImageResult = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if (isSuccess) {
            latestTmpUri?.let { uri ->
                cropLaucher.launch(uri)
            }
        }
    }


    private fun takeImage() {
        lifecycleScope.launchWhenStarted {
            getTmpFileUri().let { uri ->
                latestTmpUri = uri
                takeImageResult.launch(uri)
            }
        }
    }

    private fun getTmpFileUri(): Uri {
        val tmpFile = File.createTempFile("tmp_image_file", ".png", requireActivity().cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }

        return FileProvider.getUriForFile(requireContext(), "${requireActivity().packageName.toString()}.provider", tmpFile)
    }



    private fun loadProfile(url: Uri) {

        glide.load(url)
            .into(binding.imgPet)
        binding.imgPet.setColorFilter(ContextCompat.getColor(requireContext(), android.R.color.transparent))
    }



    private val cropActivityContract = object : ActivityResultContract<Any?, Uri?>(){
        override fun createIntent(context: Context, input: Any?): Intent {
            return CropImage.activity(input as Uri)
                .setAspectRatio(16, 16)
                .getIntent(requireActivity());
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return CropImage.getActivityResult(intent)?.uri
        }
    }

    private val cropLaucher = registerForActivityResult(cropActivityContract) {

        it?.let { uri ->
            loadProfile(uri)
        }

    }
}