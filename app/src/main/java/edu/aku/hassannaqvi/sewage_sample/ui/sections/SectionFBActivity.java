package edu.aku.hassannaqvi.sewage_sample.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.sewage_sample.CONSTANTS;
import edu.aku.hassannaqvi.sewage_sample.R;
import edu.aku.hassannaqvi.sewage_sample.contracts.FormsContract;
import edu.aku.hassannaqvi.sewage_sample.core.MainApp;
import edu.aku.hassannaqvi.sewage_sample.database.DatabaseHelper;
import edu.aku.hassannaqvi.sewage_sample.databinding.ActivitySectionFbBinding;
import edu.aku.hassannaqvi.sewage_sample.models.Form;
import edu.aku.hassannaqvi.sewage_sample.ui.other.EndingActivity;
import edu.aku.hassannaqvi.sewage_sample.utils.AppUtilsKt;
import edu.aku.hassannaqvi.sewage_sample.utils.FormState;
import edu.aku.hassannaqvi.sewage_sample.utils.SimpleCallback;

import static edu.aku.hassannaqvi.sewage_sample.core.MainApp.form;
import static edu.aku.hassannaqvi.sewage_sample.utils.ActivityExtKt.gotoActivityWithSerializable;

public class SectionFBActivity extends AppCompatActivity implements SimpleCallback<FormState> {

    ActivitySectionFbBinding bi;
    DatabaseHelper db;
    Form subForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_fb);
        bi.setCallback(this);
        this.setTitle(getString(R.string.sectionii_mainheading));
        setupSkips();
        setUIContent();

        //DB
        db = MainApp.appInfo.getDbHelper();
        MainApp.form = null;

        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        db = MainApp.appInfo.getDbHelper();
        long rowID = db.addForm(form);
        form.set_ID(String.valueOf(rowID));
        if (rowID != -1) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            AppUtilsKt.toast(getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), this);
            return false;
        }
    }


    private void setUIContent() {
    }


    public void f1bspecIDOnTextChanged(CharSequence s, int start, int before, int count) {
        Clear.clearAllFields(bi.fldGrpCVQRFB);
    }


    private void SaveDraft() {

        form = new Form();
        form.setAppversion(MainApp.appInfo.getAppVersion());
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        form.setUsername(MainApp.user.getUserName());

        form.setFormType(CONSTANTS.SECTION_B);

        form.setF1bspecid(bi.f1bspecID.getText().toString());

        form.setF1bsite(bi.f1bsiteA.isChecked() ? "1"
                : bi.f1bsiteB.isChecked() ? "2"
                : "-1");

        form.setF1b01(bi.f1b01.getText().toString());

        form.setF1b02(bi.f1b02.getText().toString());

        form.setF1b02a(bi.f1b02a.getText().toString());

        form.setF1b03(bi.f1b03.getText().toString());

        form.setF1b04(bi.f1b04.getText().toString());

        form.setF1b05(bi.f1b05.getText().toString());

        form.setF1b06(bi.f1b06.getText().toString());

        form.setF1b06a(bi.f1b06a.getText().toString());

        form.setF1b07(bi.f1b07.getText().toString());

        form.setsB(form.sBtoString());
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
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                String strResult = result.getContents();
                bi.f1bspecID.setText(strResult);
                if (!checkQR())
                    bi.fldGrpCVQRFB.setVisibility(View.GONE);
//                String[] arrContents = strResult.split("-");
//                bi.f1aspecID.setText("Ctry: " + arrContents[0] + " | " + "City: " + arrContents[1] + " | " + "Site: " + arrContents[2] + " | " + "ID: " + arrContents[3]);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private boolean checkQR() {
        boolean flag = db.checkSampleId_F1B_A(bi.f1bspecID.getText().toString(), this);
        if (!flag) return false;
        boolean form = db.checkSampleId_F1B_B(bi.f1bspecID.getText().toString(), this);
        if (form) {
            return false;
        } else {
            bi.fldGrpCVQRFB.setVisibility(View.VISIBLE);
            return true;
        }
    }

    public void scanQR_FB(View view) {
        // Scan QR Code
        bi.fldGrpCVQRFB.setVisibility(View.GONE);
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    public void invoke(FormState formState) {
        switch (formState) {
            case FORMA_NOT_EXIST:
                AppUtilsKt.toast("Sample Collection form is not exist", this);
                break;
            case FORMB_EXIST:
                AppUtilsKt.toast("This form is already exist", this);
                break;
            case INTERNAL_ERROR:
                AppUtilsKt.toast("Internal error while accessing the cursor", this);
                break;
        }
    }
}