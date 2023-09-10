package com.example.videoexoplayer.ui.allow

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.videoexoplayer.R
import com.example.videoexoplayer.databinding.ActivityAllowBinding
import com.example.videoexoplayer.ui.allow.main.MainActivity
import com.example.videoexoplayer.ui.base.BaseActivity

class AllowActivity : BaseActivity<ActivityAllowBinding, AllowViewModel>() {

    companion object {
        const val REQUEST_CODE_PERMISSION = 100
    }

    override fun layoutRes(): Int = R.layout.activity_allow

    override fun viewModelClass(): Class<AllowViewModel> = AllowViewModel::class.java

    override fun handleViewState() {}

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        binding.viewModel = viewModel
        //content
        binding.btnAllowAccess.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED) {
                MainActivity.startActivity(this)
                finish()
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_MEDIA_VIDEO),
                    REQUEST_CODE_PERMISSION
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            for (i in permissions.indices) {
                val per: String = permissions[i]
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    val showRationale = shouldShowRequestPermissionRationale(per)
                    if (!showRationale) {
                        //user clicked on never ask again
                        val alertDialog = AlertDialog.Builder(this)
                        alertDialog.setTitle("App Permission")
                            .setMessage("For playing videos, you must allow this app to access video files on your device \n\n Open Setting from below button")
                            .setPositiveButton("Open Setting") { dialog, _ ->
                                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                val uri = Uri.fromParts("package", packageName, null)
                                intent.data = uri
                                startActivity(intent)
                                dialog.dismiss()
                            }
                        alertDialog.show()
                    } else {
                        requestPermissions(
                            arrayOf(Manifest.permission.READ_MEDIA_VIDEO),
                            REQUEST_CODE_PERMISSION
                        )
                    }
                } else {
                    MainActivity.startActivity(this)
                    finish()
                }

            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        if (checkSelfPermission(Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED) {
            MainActivity.startActivity(this)
            finish()
        }
    }


}