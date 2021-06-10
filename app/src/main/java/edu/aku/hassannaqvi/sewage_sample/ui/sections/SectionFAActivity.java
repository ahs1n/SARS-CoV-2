package edu.aku.hassannaqvi.sewage_sample.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.sewage_sample.CONSTANTS;
import edu.aku.hassannaqvi.sewage_sample.R;
import edu.aku.hassannaqvi.sewage_sample.contracts.FormsContract;
import edu.aku.hassannaqvi.sewage_sample.core.MainApp;
import edu.aku.hassannaqvi.sewage_sample.database.DatabaseHelper;
import edu.aku.hassannaqvi.sewage_sample.databinding.ActivitySectionFaBinding;
import edu.aku.hassannaqvi.sewage_sample.models.Form;
import edu.aku.hassannaqvi.sewage_sample.ui.other.EndingActivity;
import edu.aku.hassannaqvi.sewage_sample.utils.AppUtilsKt;
import edu.aku.hassannaqvi.sewage_sample.utils.EndSectionInterface;
import edu.aku.hassannaqvi.sewage_sample.utils.WarningActivityInterface;

import static edu.aku.hassannaqvi.sewage_sample.core.MainApp.form;
import static edu.aku.hassannaqvi.sewage_sample.utils.ActivityExtKt.gotoActivityWithSerializable;

public class SectionFAActivity extends AppCompatActivity implements EndSectionInterface, WarningActivityInterface {

    ActivitySectionFaBinding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_fa);
        bi.setCallback(this);
        this.setTitle(getString(R.string.sectiona_mainheading));
        setListeners();
        setUIContent();

        //DB
        db = MainApp.appInfo.getDbHelper();

        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    /*
     * Save functions
     * */
    private boolean updateDB() {
        long rowID = db.addForm(form);
        form.set_ID(String.valueOf(rowID));
        if (rowID != -1) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        SaveDraft();
        if (updateDB()) {
            finish();
            gotoActivityWithSerializable(this, EndingActivity.class, "complete", true);
        } else {
            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show();
        }
    }


    private void SaveDraft() {

        form = new Form();

        form.setAppversion(MainApp.appInfo.getAppVersion());
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        form.setUsername(MainApp.user.getUserName());

        form.setFormType(CONSTANTS.SECTION_A);

        form.setSpecimenID(bi.f1aspecID.getText().toString());
        form.setSiteID(bi.f1asiteA.isChecked() ? "1"
                : bi.f1asiteB.isChecked() ? "2"
                : "-1");

        form.setF1aspecid(bi.f1aspecID.getText().toString());

        form.setF1asite(bi.f1asiteA.isChecked() ? "1"
                : bi.f1asiteB.isChecked() ? "2"
                : "-1");

        form.setF1a01(bi.f1a01.getText().toString());

        form.setF1a02(bi.f1a02.getText().toString());

        form.setF1a03(bi.f1a03.getText().toString());

        form.setF1a04(bi.f1a04.getText().toString());

        form.setF1a05(bi.f1a051.isChecked() ? "1"
                : bi.f1a052.isChecked() ? "2"
                : "-1");

        form.setF1a06(bi.f1a061.isChecked() ? "1"
                : bi.f1a062.isChecked() ? "2"
                : "-1");

        form.setF1a07(bi.f1a07.getText().toString());

        form.setF1a08(bi.f1a08.getText().toString());

        form.setF1a09(bi.f1a09.getText().toString());

        form.setF1a10(bi.f1a10.getText().toString());

        form.setF1a11(bi.f1a11.getText().toString());

        form.setsA(form.sAtoString());

    }


    private boolean formValidation() {
        if (!Validator.emptyCheckingContainer(this, bi.GrpName))
            return false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
//        Toast.makeText(this, bi.f1b02a.getText().toString() + " | " + bi.f1b06a.getText().toString(), Toast.LENGTH_SHORT).show();

        Date date1, date2;
        long difference = 0;
        try {
            date1 = simpleDateFormat.parse(bi.f1a02.getText().toString());
            date2 = simpleDateFormat.parse(bi.f1a09.getText().toString());
            difference = date2.getTime() - date1.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, String.valueOf(difference), Toast.LENGTH_SHORT).show();
        if (difference < 0) {
            return Validator.emptyCustomTextBox(this, bi.f1a09, "Time Collected cannot be less than Arrival time");
        }

        return true;
    }


    private void setUIContent() {

    }

    public void f1aspecIDOnTextChanged(CharSequence s, int start, int before, int count) {
        Clear.clearAllFields(bi.fldGrpCVQR);
    }


    /*
     * Watch listeners
     * */
    private void setListeners() {
    }


    public void BtnEnd() {
        AppUtilsKt.contextEndActivity(this);
    }


    private void routeToNextActivity() {
        if (updateDB()) {
            finish();
            startActivity(new Intent(this, SectionFBActivity.class));
        } else {
//            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void endSecActivity(boolean flag) {
        finish();
    }

    @Override
    public void callWarningActivity(int id, @Nullable Object item) {
        routeToNextActivity();
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
                bi.f1aspecID.setText(strResult);
                if (!checkQR())
                    bi.fldGrpCVQR.setVisibility(View.GONE);

                try {
                    String[] arrContents = strResult.split("-");
                    bi.f1asiteA.setChecked(arrContents[2].equals("S1"));
                    bi.f1asiteB.setChecked(arrContents[2].equals("S2"));
                } catch (Exception e) {
                    Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
                    bi.fldGrpCVQR.setVisibility(View.GONE);
                }
//                bi.f1aspecID.setText("Ctry: " + arrContents[0] + " | " + "City: " + arrContents[1] + " | " + "Site: " + arrContents[2] + " | " + "ID: " + arrContents[3]);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private boolean checkQR() {
        if (db.checkSampleId_F1A(bi.f1aspecID.getText().toString())) {
            Toast.makeText(this, "Already Exist", Toast.LENGTH_SHORT).show();
            return false;
        } else {
//            Toast.makeText(this, "Not Exist", Toast.LENGTH_SHORT).show();
            bi.fldGrpCVQR.setVisibility(View.VISIBLE);
            return true;
        }
    }

/*    private boolean validateQR() {
        if (db.getSampleID(bi.f1aspecID.getText().toString())) {
            Toast.makeText(this, "Already Exist", Toast.LENGTH_SHORT).show();
            bi.fldGrpCVQR.setVisibility(View.GONE);
            return false;
        } else {
            Toast.makeText(this, "Not Exist", Toast.LENGTH_SHORT).show();
            bi.fldGrpCVQR.setVisibility(View.VISIBLE);
            return true;
        }
    }*/

    public void scanQR(View view) {
        // Scan QR Code
        bi.fldGrpCVQR.setVisibility(View.GONE);
        new IntentIntegrator(this).initiateScan();
    }
}