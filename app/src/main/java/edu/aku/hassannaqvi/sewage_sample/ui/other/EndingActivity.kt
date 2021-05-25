package edu.aku.hassannaqvi.sewage_sample.ui.other

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.validatorcrawler.aliazaz.Validator
import edu.aku.hassannaqvi.sewage_sample.R
import edu.aku.hassannaqvi.sewage_sample.core.MainApp.appInfo
import edu.aku.hassannaqvi.sewage_sample.core.MainApp.form
import edu.aku.hassannaqvi.sewage_sample.databinding.ActivityEndingBinding
import java.text.SimpleDateFormat
import java.util.*

class EndingActivity : AppCompatActivity() {

    lateinit var bi: ActivityEndingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_ending)
        bi.callback = this
        this.title = getString(R.string.app_name)

        val check = intent.getBooleanExtra("complete", false)
        if (check) {
            bi.istatusa.isEnabled = true
            bi.istatusb.isEnabled = false
            bi.istatus96.isEnabled = false
        } else {
            bi.istatusa.isEnabled = false
            bi.istatusb.isEnabled = true
            bi.istatus96.isEnabled = true
        }
    }

    fun BtnEnd() {
        if (!formValidation()) return
        saveDraft()
        if (updateDB()) {
            finish()
        } else {
            Toast.makeText(this, getString(R.string.updatingError), Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveDraft() {
        val statusValue = when {
            bi.istatusa.isChecked -> "1"
            bi.istatusb.isChecked -> "2"
            bi.istatus96.isChecked -> "96"
            else -> "-1"
        }
        form.istatus = statusValue
        form.istatus96x = if (bi.istatus96x.text.toString().trim().isEmpty()) "-1" else bi.istatus96x.text.toString()
        form.endingdatetime = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH).format(Date().time)

    }


    private fun updateDB(): Boolean {
        val db = appInfo.dbHelper
        val updcount = db.updateEnding()
        return if (updcount == 1) {
            true
        } else {
            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun formValidation(): Boolean {
        return Validator.emptyCheckingContainer(this, bi.fldGrpEnd)
    }

    override fun onBackPressed() {
        Toast.makeText(this, getString(R.string.bckBtn), Toast.LENGTH_LONG).show()
    }
}