package edu.aku.hassannaqvi.sewage_sample.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import edu.aku.hassannaqvi.sewage_sample.R
import edu.aku.hassannaqvi.sewage_sample.databinding.EndSectionDialogBinding
import edu.aku.hassannaqvi.sewage_sample.ui.other.EndingActivity
import edu.aku.hassannaqvi.sewage_sample.utils.CreateTable.DATABASE_COPY
import edu.aku.hassannaqvi.sewage_sample.utils.CreateTable.DATABASE_NAME
import edu.aku.hassannaqvi.sewage_sample.utils.CreateTable.PROJECT_NAME
import edu.aku.hassannaqvi.sewage_sample.utils.shared.SharedStorage
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

fun dbBackup(context: Context) {
    val dt: String = SharedStorage.getBackUpDTFolder(context)
    if (dt != SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(Date())) {
        SharedStorage.setBackUpDTFolder(context, SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(Date()))
    }

    var folder: File
    folder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        File(context.getExternalFilesDir("")?.absolutePath + File.separator + PROJECT_NAME)
    else
        File(Environment.getExternalStorageDirectory().toString() + File.separator + PROJECT_NAME)

    var success = true
    if (!folder.exists()) {
        success = folder.mkdirs()
    }
    if (success) {
        val directoryName = folder.path + File.separator + dt
        folder = File(directoryName)
        if (!folder.exists()) {
            success = folder.mkdirs()
        }
        if (success) {
            val any = try {
                val dbFile = File(context.getDatabasePath(DATABASE_NAME).path)
                val fis = FileInputStream(dbFile)
                val outFileName: String = directoryName + File.separator + DATABASE_COPY
                // Open the empty db as the output stream
                val output: OutputStream = FileOutputStream(outFileName)

                // Transfer bytes from the inputfile to the outputfile
                val buffer = ByteArray(1024)
                var length: Int
                while (fis.read(buffer).also { length = it } > 0) {
                    output.write(buffer, 0, length)
                }
                // Close the streams
                output.flush()
                output.close()
                fis.close()
            } catch (e: IOException) {
                e.message?.let { Log.e("dbBackup:", it) }
            }
        }
    } else {
        Toast.makeText(context, "Not create folder", Toast.LENGTH_SHORT).show()
    }

}

fun AppCompatActivity.openSectionEndingActivity(flag: Boolean = true) {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(R.layout.item_dialog_2)
    dialog.setCancelable(false)
    val params = WindowManager.LayoutParams()
    params.copyFrom(dialog.window!!.attributes)
    params.width = WindowManager.LayoutParams.WRAP_CONTENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog.show()
    dialog.window!!.attributes = params
    dialog.findViewById<View>(R.id.btnOk).setOnClickListener { view: View? ->
        this.finish()
        this.startActivity(Intent(this, EndingActivity::class.java).putExtra("complete", flag))
    }
    dialog.findViewById<View>(R.id.btnNo).setOnClickListener { view: View? -> dialog.dismiss() }
}

fun AppCompatActivity.contextEndActivity() {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(R.layout.item_dialog_2)
    dialog.setCancelable(false)
    val params = WindowManager.LayoutParams()
    params.copyFrom(dialog.window!!.attributes)
    params.width = WindowManager.LayoutParams.WRAP_CONTENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog.show()
    dialog.window!!.attributes = params
    val endSecInterface = this as EndSectionInterface
    dialog.findViewById<View>(R.id.btnOk).setOnClickListener { view: View? -> endSecInterface.endSecActivity(true) }
    dialog.findViewById<View>(R.id.btnNo).setOnClickListener { view: View? -> dialog.dismiss() }
}

@JvmOverloads
fun AppCompatActivity.openWarningActivity(
        id: Int,
        item: Any? = null,
        title: String = "WARNING!",
        message: String = "Are you sure, you want to exit without saving?",
        btnYesTxt: String = "YES",
        btnNoTxt: String = "NO") {
    val dialog = Dialog(this)

    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val bi: EndSectionDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.end_section_dialog, null, false)
    dialog.setContentView(bi.root)
    bi.alertTitle.text = title
    bi.alertTitle.setTextColor(ContextCompat.getColor(this, R.color.redDark))
    bi.content.text = message
    bi.btnOk.text = btnYesTxt
    bi.btnOk.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
    bi.btnNo.text = btnNoTxt
    bi.btnNo.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
    dialog.setCancelable(false)
    val params = WindowManager.LayoutParams()
    params.copyFrom(dialog.window!!.attributes)
    params.width = WindowManager.LayoutParams.WRAP_CONTENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog.window!!.attributes = params
    dialog.show()
    bi.btnOk.setOnClickListener {
        dialog.dismiss()
        val warningActivity = this as WarningActivityInterface
        warningActivity.callWarningActivity(id, item)
    }
    bi.btnNo.setOnClickListener {
        dialog.dismiss()
    }
}

@JvmOverloads
fun AppCompatActivity.openWarningActivity02(
        id: Int,
        item: Any? = null,
        title: String = "WARNING!",
        message: String = "Are you sure, you want to exit without saving?",
        btnYesTxt: String = "YES",
        btnNoTxt: String = "NO") {
    val dialog = Dialog(this)

    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val bi: EndSectionDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.end_section_dialog, null, false)
    dialog.setContentView(bi.root)
    bi.alertTitle.text = title
    bi.alertTitle.setTextColor(ContextCompat.getColor(this, R.color.redDark))
    bi.content.text = message
    bi.btnOk.text = btnYesTxt
    bi.btnOk.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
    bi.btnNo.text = btnNoTxt
    bi.btnNo.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
    dialog.setCancelable(false)
    val params = WindowManager.LayoutParams()
    params.copyFrom(dialog.window!!.attributes)
    params.width = WindowManager.LayoutParams.WRAP_CONTENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog.window!!.attributes = params
    dialog.show()
    bi.btnOk.setOnClickListener {
        dialog.dismiss()
        val warningActivity = this as WarningActivityInterface
        warningActivity.callWarningActivity(id, item)
    }
    bi.btnNo.setOnClickListener {
        dialog.dismiss()
        val warningActivity = this as WarningActivityInterface
        warningActivity.callWarningActivity(id, null)
    }
}

@JvmOverloads
fun Fragment.openWarningFragment(
        id: Int,
        item: Any? = null,
        title: String = "WARNING!",
        message: String = "Are you sure, you want to exit without saving?",
        btnYesTxt: String = "YES",
        btnNoTxt: String = "NO") {

    activity?.let { activityFrag ->
        val dialog = Dialog(activityFrag)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val bi: EndSectionDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(activityFrag), R.layout.end_section_dialog, null, false)
        dialog.setContentView(bi.root)
        bi.alertTitle.text = title
        bi.alertTitle.setTextColor(ContextCompat.getColor(activityFrag, R.color.green))
        bi.content.text = message
        bi.btnOk.text = btnYesTxt
        bi.btnOk.setBackgroundColor(ContextCompat.getColor(activityFrag, R.color.green))
        bi.btnNo.text = btnNoTxt
        bi.btnNo.setBackgroundColor(ContextCompat.getColor(activityFrag, R.color.gray))
        dialog.setCancelable(false)
        val params = WindowManager.LayoutParams()
        params.copyFrom(dialog.window!!.attributes)
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = params
        dialog.show()
        bi.btnOk.setOnClickListener {
            val warningActivity = activity as WarningActivityInterface
            warningActivity.callWarningActivity(id, item)
            dialog.dismiss()
        }
        bi.btnNo.setOnClickListener {
            dialog.dismiss()
        }
    }
}

@JvmOverloads
fun AppCompatActivity.openWarningDialog(title: String, message: String, btnYesTxt: String = "OK") {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val bi: EndSectionDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.end_section_dialog, null, false)
    dialog.setContentView(bi.root)
    bi.alertTitle.text = title
    bi.alertTitle.setTextColor(ContextCompat.getColor(this, R.color.green))
    bi.content.text = message
    bi.btnOk.text = btnYesTxt
    bi.btnOk.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
    bi.btnNo.visibility = View.GONE
    val params = WindowManager.LayoutParams()
    params.copyFrom(dialog.window!!.attributes)
    params.width = WindowManager.LayoutParams.WRAP_CONTENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog.window!!.attributes = params
    dialog.show()
    bi.btnOk.setOnClickListener {
        dialog.dismiss()
    }
}

interface EndSectionInterface {
    fun endSecActivity(flag: Boolean)
}

interface WarningActivityInterface {
    fun callWarningActivity(id: Int, item: Any? = null)
}

fun String.convertStringToUpperCase(): String {
    /*
     * Program that first convert all uper case into lower case then
     * convert fist letter into uppercase
     */
    val calStr = this.split(" ").map { it.toLowerCase(Locale.ENGLISH).capitalize(Locale.ENGLISH) }
    return calStr.joinToString(separator = " ")
}

fun String.shortStringLength(): String {
    /*
     * Program that first convert all uper case into lower case then
     * convert fist letter into uppercase
     */
    var calStr = this
    if (this.length > 20)
        calStr = this.substring(0, 20).plus("...")
    return calStr
}


/*
* Toast dealing
* */
fun Any.toast(context: Context): Toast {
    return Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).apply { show() }
}


@FunctionalInterface
interface SimpleCallback<T> {
    fun invoke(msg: T)
}

/*
* Form exist checking
* */
enum class FormState {
    FORMA_NOT_EXIST,
    FORMA_EXIST,
    FORMB_NOT_EXIST,
    FORMB_EXIST,
    FORMC_NOT_EXIST,
    FORMC_EXIST,
    INTERNAL_ERROR
}




