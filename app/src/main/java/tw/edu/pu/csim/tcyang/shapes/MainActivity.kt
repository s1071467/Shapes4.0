package tw.edu.pu.csim.tcyang.shapes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class MainActivity : AppCompatActivity(), PermissionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Request camera permissions
        Dexter.withContext(this)
                .withPermission(android.Manifest.permission.CAMERA)
                .withListener(this)
                .check()
    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
        Toast.makeText(this, "您已允許拍照權限", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        if (p0!!.isPermanentlyDenied) {
            Toast.makeText(this, "您永久拒絕拍照權限", Toast.LENGTH_SHORT).show()
            var it: Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            var uri: Uri = Uri.fromParts("package", getPackageName(), null)
            it.setData(uri)
            startActivity(it)
        }
        else{
            Toast.makeText(this, "您拒絕拍照權限，無法使用本App",
                    Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
        p1?.continuePermissionRequest()
    }
}