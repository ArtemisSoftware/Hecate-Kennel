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
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import androidx.core.app.ActivityCompat


fun Fragment.imageOptions() {
    Dexter.withContext(requireContext())
        .withPermissions(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    context?.let { showImagePickerOptions(it) }
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