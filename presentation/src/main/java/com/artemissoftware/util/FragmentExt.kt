package com.artemissoftware.util

import android.Manifest
import androidx.fragment.app.Fragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken

import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.listener.PermissionRequest

import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import android.app.AlertDialog
import android.content.Context
import com.artemissoftware.presentation.R
import android.content.DialogInterface


fun Fragment.imageOptions(galleryOption: () -> Unit, cameraOption: () -> Unit) {
    Dexter.withContext(requireContext())
        .withPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    context?.let { showImagePickerOptions(it, galleryOption, cameraOption) }
                }
                if (report.isAnyPermissionPermanentlyDenied) {
                    context?.let { showSettingsDialog(it) }
                }
            }

            override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                token.continuePermissionRequest()
            }
        }).check()
}


private fun showSettingsDialog(context: Context) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    builder.setTitle(context.getString(R.string.dialog_permission_title))
    builder.setMessage(context.getString(R.string.dialog_permission_message))
    builder.setPositiveButton(context.getString(R.string.go_to_settings)) { dialog, which ->
        dialog.cancel()
        //openSettings()
    }
    builder.setNegativeButton(context.getString(R.string.cancel)) { dialog, which -> dialog.cancel() }
    builder.show()
}

//
//private fun openSettings(context: Context) {
//    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//    val uri: Uri = Uri.fromParts("package", context.getPackageName(), null)
//    intent.data = uri
//    startActivityForResult(intent, 101)
//}


private fun showImagePickerOptions(context: Context, galleryOption: () -> Unit, cameraOption: () -> Unit) {
    // setup the alert builder
    val builder = AlertDialog.Builder(context)
    builder.setTitle(context.getString(R.string.lbl_set_profile_photo))

    // add a list
    val animals = arrayOf(
        context.getString(R.string.lbl_take_camera_picture),
        context.getString(R.string.lbl_choose_from_gallery),
        context.getString(R.string.cancel)
    )
    builder.setItems(
        animals
    ) { dialog: DialogInterface?, which: Int ->
        when (which) {
            0 -> cameraOption()
            1 -> galleryOption()
            else -> ""
        }
    }

    // create and show the alert dialog
    val dialog = builder.create()
    dialog.show()
}


