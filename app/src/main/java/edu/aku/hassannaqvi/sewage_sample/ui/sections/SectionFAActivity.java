package edu.aku.hassannaqvi.sewage_sample.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.validatorcrawler.aliazaz.Validator;

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
//            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show();
        }
    }

    private void SaveDraft() {

        form = new Form();

        form.setF1specid(bi.specID.getText().toString());

        form.setF1site(bi.siteA.isChecked() ? "1"
                : bi.siteB.isChecked() ? "2"
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
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    private void setUIContent() {

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
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();


                String strResult = result.getContents();
                String[] arrContents = strResult.split("-");

                bi.specID.setText("Ctry: " + arrContents[0] + " | " + "City: " + arrContents[1] + " | " + "Site: " + arrContents[2] + " | " + "ID: " + arrContents[3]);

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}