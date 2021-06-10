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
import edu.aku.hassannaqvi.sewage_sample.databinding.ActivitySectionFcBinding;
import edu.aku.hassannaqvi.sewage_sample.models.Form;
import edu.aku.hassannaqvi.sewage_sample.ui.other.EndingActivity;
import edu.aku.hassannaqvi.sewage_sample.utils.AppUtilsKt;
import edu.aku.hassannaqvi.sewage_sample.utils.FormState;
import edu.aku.hassannaqvi.sewage_sample.utils.SimpleCallback;

import static edu.aku.hassannaqvi.sewage_sample.core.MainApp.form;
import static edu.aku.hassannaqvi.sewage_sample.utils.ActivityExtKt.gotoActivityWithSerializable;

public class SectionFCActivity extends AppCompatActivity implements SimpleCallback<FormState> {

    ActivitySectionFcBinding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_fc);
        bi.setCallback(this);
        this.setTitle(getString(R.string.sectioniii_mainheading));

        form = new Form();
        setListeners();
        setUIContent();

        //DB
        db = MainApp.appInfo.getDbHelper();

        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setUIContent() {
    }

    public void f1cspecIDOnTextChanged(CharSequence s, int start, int before, int count) {
        Clear.clearAllFields(bi.fldGrpCVQRFC);
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


    private void SaveDraft() {

        form = new Form();

        form.setAppversion(MainApp.appInfo.getAppVersion());
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        form.setUsername(MainApp.user.getUserName());

        form.setFormType(CONSTANTS.SECTION_C);
        form.setSpecimenID(bi.f1cspecID.getText().toString());
        form.setSiteID(bi.f1csiteA.isChecked() ? "1"
                : bi.f1csiteB.isChecked() ? "2"
                : "-1");

        form.setF1cspecid(bi.f1cspecID.getText().toString());

        form.setF1csite(bi.f1csiteA.isChecked() ? "1"
                : bi.f1csiteB.isChecked() ? "2"
                : "-1");

        form.setF1c01(bi.f1c01.getText().toString());

        form.setF1c02(bi.f1c02.getText().toString());

        form.setF1c03(bi.f1c03.getText().toString());

        form.setF1c04(bi.f1c041.isChecked() ? "1"
                : bi.f1c042.isChecked() ? "2"
                : "-1");

        form.setF1c05(bi.f1c051.isChecked() ? "1"
                : bi.f1c052.isChecked() ? "2"
                : "-1");

        form.setF1c06(bi.f1c06.getText().toString());

        form.setF1c07(bi.f1c071.isChecked() ? "1"
                : bi.f1c072.isChecked() ? "2"
                : "-1");

        form.setF1c08(bi.f1c08.getText().toString());

        form.setsC(form.sCtoString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openSectionEndingActivity(this, false);
    }


    private void setListeners() {
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                AppUtilsKt.toast("Cancelled", this);
            } else {
                String strResult = result.getContents();
                bi.f1cspecID.setText(strResult);
                if (!checkQR())
                    bi.fldGrpCVQRFC.setVisibility(View.GONE);

                try {
                    String[] arrContents = strResult.split("-");
                    bi.f1csiteA.setChecked(arrContents[2].equals("S1"));
                    bi.f1csiteB.setChecked(arrContents[2].equals("S2"));
                } catch (Exception e) {
                    Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
                    bi.fldGrpCVQRFC.setVisibility(View.GONE);
                }

//                bi.f1cspecID.setText("Ctry: " + arrContents[0] + " | " + "City: " + arrContents[1] + " | " + "Site: " + arrContents[2] + " | " + "ID: " + arrContents[3]);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private boolean checkQR() {
        boolean flag = db.checkSampleId_F1C_A(bi.f1cspecID.getText().toString(), this);
        if (!flag) return false;
        boolean flag_b = db.checkSampleId_F1C_B(bi.f1cspecID.getText().toString(), this);
        if (!flag_b) return false;

        boolean form = db.checkSampleId_F1C_C(bi.f1cspecID.getText().toString(), this);
        if (form) {
            return false;
        } else {
            bi.fldGrpCVQRFC.setVisibility(View.VISIBLE);
            return true;
        }
    }


    public void scanQR_FC(View view) {
        // Scan QR Code
        bi.fldGrpCVQRFC.setVisibility(View.GONE);
        new IntentIntegrator(this).initiateScan();
    }


    @Override
    public void invoke(FormState formState) {
        switch (formState) {
            case FORMA_NOT_EXIST:
                AppUtilsKt.toast("Sample Collection form is not exist", this);
                break;
            case FORMB_NOT_EXIST:
                AppUtilsKt.toast("Please Enter the Filtration form first", this);
                break;
            case FORMC_EXIST:
                AppUtilsKt.toast("This form is already exist", this);
                break;
            case INTERNAL_ERROR:
                AppUtilsKt.toast("Internal error while accessing the cursor", this);
                break;
        }
    }
}