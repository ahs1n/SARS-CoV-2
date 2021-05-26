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
import edu.aku.hassannaqvi.sewage_sample.databinding.ActivitySectionFcBinding;
import edu.aku.hassannaqvi.sewage_sample.ui.other.EndingActivity;
import edu.aku.hassannaqvi.sewage_sample.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.sewage_sample.core.MainApp.form;
import static edu.aku.hassannaqvi.sewage_sample.utils.ActivityExtKt.gotoActivityWithSerializable;

public class SectionFCActivity extends AppCompatActivity {

    ActivitySectionFcBinding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_fc);
        bi.setCallback(this);
        this.setTitle(getString(R.string.sectioniii_mainheading));
        setListeners();
        setUIContent();

        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
    }

    private void setUIContent() {
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

        long count = db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
        if (count > 0) {
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_SC, form.getsC());
            return true;
        } else {
            Toast.makeText(this, getString(R.string.failedUpdateDb), Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() {

        //form = new Form();

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
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();


                String strResult = result.getContents();
                bi.f1cspecID.setText(strResult);

//                String[] arrContents = strResult.split("-");
//                bi.f1cspecID.setText("Ctry: " + arrContents[0] + " | " + "City: " + arrContents[1] + " | " + "Site: " + arrContents[2] + " | " + "ID: " + arrContents[3]);

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}