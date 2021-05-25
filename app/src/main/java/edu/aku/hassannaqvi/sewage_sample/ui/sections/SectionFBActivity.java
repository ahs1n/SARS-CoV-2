package edu.aku.hassannaqvi.sewage_sample.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.sewage_sample.R;
import edu.aku.hassannaqvi.sewage_sample.contracts.FormsContract;
import edu.aku.hassannaqvi.sewage_sample.core.MainApp;
import edu.aku.hassannaqvi.sewage_sample.database.DatabaseHelper;
import edu.aku.hassannaqvi.sewage_sample.databinding.ActivitySectionFbBinding;
import edu.aku.hassannaqvi.sewage_sample.models.Form;
import edu.aku.hassannaqvi.sewage_sample.ui.other.EndingActivity;
import edu.aku.hassannaqvi.sewage_sample.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.sewage_sample.core.MainApp.form;
import static edu.aku.hassannaqvi.sewage_sample.utils.ActivityExtKt.gotoActivityWithSerializable;

public class SectionFBActivity extends AppCompatActivity {

    ActivitySectionFbBinding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_fb);
        bi.setCallback(this);
        this.setTitle(getString(R.string.sectionii_mainheading));
        setupSkips();
        setUIContent();
//        int country = SharedStorage.INSTANCE.getCountryCode(this);
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
    }


    private void setupSkips() {
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        SaveDraft();
        if (UpdateDB()) {
            finish();
            gotoActivityWithSerializable(this, EndingActivity.class, "complete", true);
        } else {
//            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long rowid = db.addForm(form);
        form.set_ID(String.valueOf(rowid));
        if (rowid > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            long count = db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            if (count > 0) {
                db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_SB, form.sBtoString());
                return true;
            } else {
                Toast.makeText(this, getString(R.string.failedUpdateDb), Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void setUIContent() {
    }


    private void SaveDraft() {

        form = new Form();
        form.setF1b01(bi.f1b01.getText().toString());
        form.setF1b02(bi.f1b02.getText().toString());
        form.setF1b03(bi.f1b03.getText().toString());
        form.setF1b04(bi.f1b04.getText().toString());
        form.setF1b05(bi.f1b05.getText().toString());
        form.setF1b06(bi.f1b06.getText().toString());
        form.setF1b07(bi.f1b07.getText().toString());
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openSectionEndingActivity(this, false);
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();


                String strResult = result.getContents();
                String[] arrContents = strResult.split("-");

                bi.f1bspecID.setText("Ctry: " + arrContents[0] + " | " + "City: " + arrContents[1] + " | " + "Site: " + arrContents[2] + " | " + "ID: " + arrContents[3]);

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

}