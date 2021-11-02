package com.artemissoftware.presentation.pet

import android.Manifest
import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artemissoftware.presentation.R
import com.artemissoftware.presentation.databinding.FragmentMemeBinding
import com.artemissoftware.presentation.databinding.FragmentPetBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken

import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.PermissionRequest

import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.content.ContextCompat
import com.bumptech.glide.RequestManager
import javax.inject.Inject
import java.io.IOException
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.FileProvider.getUriForFile
import java.io.File


@AndroidEntryPoint
class PetFragment : Fragment(R.layout.fragment_pet) {


    @Inject
    lateinit var glide: RequestManager

    private var _binding: FragmentPetBinding? = null
    private val binding get() = _binding!!


    lateinit var fileName: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        _binding = FragmentPetBinding.bind(view)
        initOnClicklistener()
    }

    private fun initOnClicklistener(){

        binding.imgPet.setOnClickListener {

            Dexter.withContext(requireActivity())
                .withPermissions(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        if (report.areAllPermissionsGranted()) {
                            //--showImagePickerOptions()
                            launchGalleryIntent()
                        }
                        if (report.isAnyPermissionPermanentlyDenied) {
                            //--showSettingsDialog()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: List<PermissionRequest>,
                        token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                }).check()

        }
    }


    private fun loadProfile(url: String) {

        glide.load(url)
            .into(binding.imgPet)
        binding.imgPet.setColorFilter(ContextCompat.getColor(requireContext(), android.R.color.transparent))
    }


    private fun getCacheImagePath(fileName: String): Uri? {
        val path = File(requireActivity().externalCacheDir, "camera")
        if (!path.exists()) path.mkdirs()
        val image = File(path, fileName)
        return getUriForFile(
            requireContext(),
            requireActivity().packageName.toString() + ".provider",
            image
        )
    }



    private fun launchGalleryIntent() {
//        val intent = Intent(this@MainActivity, ImagePickerActivity::class.java)
//        intent.putExtra(
//            ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION,
//            ImagePickerActivity.REQUEST_GALLERY_IMAGE
//        )
//
//        // setting aspect ratio
//        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)
//        startActivityForResult(intent, REQUEST_IMAGE)

        chooseImageFromGallery()
    }

    private fun launchCameraIntent() {
//        val intent = Intent(this@MainActivity, ImagePickerActivity::class.java)
//        intent.putExtra(
//            ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION,
//            ImagePickerActivity.REQUEST_IMAGE_CAPTURE
//        )
//
//        // setting aspect ratio
//        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
//        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)
//
//        // setting maximum bitmap width and height
//        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true)
//        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000)
//        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000)
        takeCameraImage()
    }



    private fun chooseImageFromGallery() {
        Dexter.withContext(requireActivity())
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {

                        val pickPhoto = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                        resultLauncher.launch(pickPhoto)
                        //openSomeActivityForResult()
                        //startActivityForResult(pickPhoto, REQUEST_GALLERY_IMAGE)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }


    private fun takeCameraImage() {
        Dexter.withContext(requireActivity())
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        fileName = System.currentTimeMillis().toString() + ".jpg"

                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        takePictureIntent.putExtra(
                            MediaStore.EXTRA_OUTPUT,
                            getCacheImagePath(fileName)
                        )
                        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
                            resultLauncher.launch(takePictureIntent)
                            //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }



    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {


            result.data?.let {

                val uri: Uri? = it.data

                uri?.let {

                    try {
                        // You can update this bitmap to your server
                        val bitmap: Bitmap =
                            MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)

                        // loading profile image from local cache
                        loadProfile(uri.toString())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }


            }





            //--doSomeOperations()
        }
    }





}