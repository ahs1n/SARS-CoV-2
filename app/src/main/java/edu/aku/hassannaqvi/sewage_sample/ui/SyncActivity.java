package edu.aku.hassannaqvi.sewage_sample.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import edu.aku.hassannaqvi.sewage_sample.R;
import edu.aku.hassannaqvi.sewage_sample.adapters.SyncListAdapter;
import edu.aku.hassannaqvi.sewage_sample.core.MainApp;
import edu.aku.hassannaqvi.sewage_sample.database.DatabaseHelper;
import edu.aku.hassannaqvi.sewage_sample.databinding.ActivitySyncBinding;
import edu.aku.hassannaqvi.sewage_sample.models.SyncModel;
import edu.aku.hassannaqvi.sewage_sample.models.Users;
import edu.aku.hassannaqvi.sewage_sample.models.VersionApp;
import edu.aku.hassannaqvi.sewage_sample.workers.DataDownWorkerALL;
import edu.aku.hassannaqvi.sewage_sample.workers.DataUpWorkerALL;

import static edu.aku.hassannaqvi.sewage_sample.CONSTANTS.FOLLOWUP_FLAG;
import static edu.aku.hassannaqvi.sewage_sample.CONSTANTS.SECTION_A;
import static edu.aku.hassannaqvi.sewage_sample.CONSTANTS.SECTION_B;
import static edu.aku.hassannaqvi.sewage_sample.CONSTANTS.SECTION_C;
import static edu.aku.hassannaqvi.sewage_sample.utils.AndroidUtilityKt.isNetworkConnected;
import static edu.aku.hassannaqvi.sewage_sample.utils.AppUtilsKt.dbBackup;

//import static edu.aku.hassannaqvi.casi_register.core.MainApp.sdDir;


public class SyncActivity extends AppCompatActivity {
    private static final String TAG = "SyncActivity";
    DatabaseHelper db;
    SyncListAdapter syncListAdapter;
    ActivitySyncBinding bi;
    List<SyncModel> uploadTables;
    List<SyncModel> downloadTables;
    Boolean listActivityCreated;
    Boolean uploadlistActivityCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_sync);
        bi.setCallback(this);

        db = MainApp.appInfo.getDbHelper();
        uploadTables = new ArrayList<>();
        downloadTables = new ArrayList<>();
        MainApp.uploadData = new ArrayList<>();

        bi.noDataItem.setVisibility(View.VISIBLE);
        listActivityCreated = true;
        uploadlistActivityCreated = true;

        db = new DatabaseHelper(this);
        dbBackup(this);

    }


    void setAdapter(List<SyncModel> tables) {
        syncListAdapter = new SyncListAdapter(tables);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        bi.rvUploadList.setLayoutManager(mLayoutManager2);
        bi.rvUploadList.setItemAnimator(new DefaultItemAnimator());
        bi.rvUploadList.setAdapter(syncListAdapter);
        syncListAdapter.notifyDataSetChanged();
        if (syncListAdapter.getItemCount() > 0) {
            bi.noDataItem.setVisibility(View.GONE);
        } else {
            bi.noDataItem.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        finish();
    }


    @SuppressLint("NonConstantResourceId")
    public void ProcessStart(View view) {

        if (!isNetworkConnected(this))
            return;

        switch (view.getId()) {

            case R.id.btnUpload:
                bi.dataLayout.setVisibility(View.VISIBLE);
                //bi.photoLayout.setVisibility(View.GONE);
                bi.mTextViewS.setVisibility(View.GONE);
                bi.pBar.setVisibility(View.GONE);
                uploadTables.clear();
                MainApp.uploadData.clear();
                // Set tables to UPLOAD

                String[][] tables = {{"SectionA", SECTION_A}, {"SectionB", SECTION_B}, {"SectionC", SECTION_C}};

                for (String[] table : tables) {
                    uploadTables.add(new SyncModel(table[0]));
                    MainApp.uploadData.add(db.getUnsyncedForms(table[1]));
                }

                MainApp.downloadData = new String[uploadTables.size()];
                setAdapter(uploadTables);
                BeginUpload();
                break;
            case R.id.btnSync:

                MainApp.downloadData = new String[0];
                bi.dataLayout.setVisibility(View.VISIBLE);
                bi.photoLayout.setVisibility(View.GONE);
                bi.mTextViewS.setVisibility(View.GONE);
                bi.pBar.setVisibility(View.GONE);
                downloadTables.clear();

                if (getIntent().getBooleanExtra(FOLLOWUP_FLAG, false)) {
                    /*downloadTables.add(new SyncModel(ChildFollowup.ChildTable.TABLE_NAME));
                    downloadTables.add(new SyncModel(WraFollowup.WraTable.TABLE_NAME));*/
                } else {
                    downloadTables.add(new SyncModel(Users.UsersTable.TABLE_NAME));
                    downloadTables.add(new SyncModel(VersionApp.VersionAppTable.TABLE_NAME));
                }

                MainApp.downloadData = new String[downloadTables.size()];
                setAdapter(downloadTables);
                BeginDownload();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }


    private void BeginDownload() {

        List<OneTimeWorkRequest> workRequests = new ArrayList<>();
        for (int i = 0; i < downloadTables.size(); i++) {
            String downloadTable = downloadTables.get(i).gettableName();
            Data.Builder data = new Data.Builder()
                    .putString("table", downloadTable)
                    .putInt("position", i)
                    //.putString("columns", "_id, sysdate")
                    // .putString("where", where)
                    ;
           /* if (downloadTable.equals(ChildFollowup.ChildTable.TABLE_NAME))
                data.putString("where",
                        ChildFollowup.ChildTable.COLUMN_CS01 + "='" + SharedStorage.INSTANCE.getCountryCode(this) + "' AND " +
                                ChildFollowup.ChildTable.COLUMN_CS04 + "='" + MainApp.mainInfo.getUc_code() + "'"
                );
            else if (downloadTable.equals(WraFollowup.WraTable.TABLE_NAME))
                data.putString("where",
                        WraFollowup.WraTable.COLUMN_WS01 + "='" + SharedStorage.INSTANCE.getCountryCode(this) + "' AND " +
                                WraFollowup.WraTable.COLUMN_WS04 + "='" + MainApp.mainInfo.getUc_code() + "'"
                );
            else
                data.putString("where", Users.UsersTable.COLUMN_COUNTRY_CODE + "='" + SharedStorage.INSTANCE.getCountryCode(this) + "'");*/

            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(DataDownWorkerALL.class)
                    .addTag(String.valueOf(i))
                    .setInputData(data.build()).build();
            workRequests.add(workRequest);

        }

        // FOR SIMULTANEOUS WORKREQUESTS (ALL TABLES DOWNLOAD AT THE SAME TIME)
        WorkManager wm = WorkManager.getInstance();
        WorkContinuation wc = wm.beginWith(workRequests);
        wc.enqueue();


        /*
         * Getting result back
         * */
        wc.getWorkInfosLiveData().observe(this, workInfo -> {
            Log.d(TAG, "workInfo: " + workInfo.size());
            for (WorkInfo workItem : workInfo) {
                Log.d(TAG, "workInfo: getState " + workItem.getState());
                Log.d(TAG, "workInfo: data " + workItem.getOutputData().getString("data"));
                Log.d(TAG, "workInfo: error " + workItem.getOutputData().getString("error"));
                Log.d(TAG, "workInfo: position " + workItem.getOutputData().getInt("position", 0));
            }
            for (WorkInfo workItem : workInfo) {
                int position = workItem.getOutputData().getInt("position", 0);
                String tableName = downloadTables.get(position).gettableName();

                /*String progress = workInfo.getProgress().getString("progress");
                bi.wmError.setText("Progress: " + progress);*/

                if (workItem.getState() == WorkInfo.State.SUCCEEDED) {
                    //String result = workInfo.getOutputData().getString("data");
                    String result = MainApp.downloadData[position];
                    //Do something with the JSON string
                    if (result != null) {
                        if (result.length() > 0) {
                            Log.d(TAG, "onChanged: result " + result);
                            System.out.println("SYSTEM onChanged: result" + result);
                            DatabaseHelper db = new DatabaseHelper(SyncActivity.this);
                            try {
                                JSONArray jsonArray = new JSONArray();
                                int insertCount = 0;

                                switch (tableName) {
                                    case Users.UsersTable.TABLE_NAME:
                                        jsonArray = new JSONArray(result);
                                        insertCount = db.syncUser(jsonArray);
                                        break;
                                    case VersionApp.VersionAppTable.TABLE_NAME:
                                        insertCount = db.syncVersionApp(new JSONObject(result));
                                        if (insertCount == 1) jsonArray.put("1");
                                        break;
/*                                    case Villages.VillagesTable.TABLE_NAME:
                                        jsonArray = new JSONArray(result);
                                        insertCount = db.syncVillages(jsonArray);
                                        Log.d(TAG, "onChanged: " + tableName + " " + workItem.getOutputData().getInt("position", 0));
                                        break;
                                    case HealthFacility.HealthFacilityTable.TABLE_NAME:
                                        jsonArray = new JSONArray(result);
                                        insertCount = db.syncHF(jsonArray);
                                        Log.d(TAG, "onChanged: " + tableName + " " + workItem.getOutputData().getInt("position", 0));
                                        break;*/
                                    /*case ChildFollowup.ChildTable.TABLE_NAME:
                                        jsonArray = new JSONArray(result);
                                        insertCount = db.syncChildFollowups(jsonArray);
                                        Log.d(TAG, "onChanged: " + tableName + " " + workItem.getOutputData().getInt("position", 0));
                                        break;
                                    case WraFollowup.WraTable.TABLE_NAME:
                                        jsonArray = new JSONArray(result);
                                        insertCount = db.wraFollowups(jsonArray);
                                        Log.d(TAG, "onChanged: " + tableName + " " + workItem.getOutputData().getInt("position", 0));
                                        break;*/
                                }

                                downloadTables.get(position).setmessage("Received: " + jsonArray.length() + ", Saved: " + insertCount);
                                downloadTables.get(position).setstatus(insertCount == 0 ? "Unsuccessful" : "Successful");
                                downloadTables.get(position).setstatusID(insertCount == 0 ? 1 : 3);
                                syncListAdapter.updatesyncList(downloadTables);


//                    pd.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                downloadTables.get(position).setstatus("Process Failed");
                                downloadTables.get(position).setstatusID(1);
                                downloadTables.get(position).setmessage(result);
                                syncListAdapter.updatesyncList(downloadTables);
                            }
                        } else {
                            downloadTables.get(position).setmessage("Received: " + result.length() + "");
                            downloadTables.get(position).setstatus("Successful");
                            downloadTables.get(position).setstatusID(3);
                            syncListAdapter.updatesyncList(downloadTables);
//                pd.show();
                        }
                    } else {
                        downloadTables.get(position).setstatus("Process Failed");
                        downloadTables.get(position).setstatusID(1);
                        downloadTables.get(position).setmessage("Server not found!");
                        syncListAdapter.updatesyncList(downloadTables);
//            pd.show();
                    }
                }
                //mTextView1.append("\n" + workInfo.getState().name());
                if (workItem.getState() != null &&
                        workItem.getState() == WorkInfo.State.FAILED) {
                    String message = workItem.getOutputData().getString("error");
                    downloadTables.get(position).setstatus("Process Failed");
                    downloadTables.get(position).setstatusID(1);
                    downloadTables.get(position).setmessage(message);
                    syncListAdapter.updatesyncList(downloadTables);

                }
            }
        });
    }


    private void BeginUpload() {

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        List<OneTimeWorkRequest> workRequests = new ArrayList<>();

        for (int i = 0; i < uploadTables.size(); i++) {
            Data data = new Data.Builder()
                    .putString("table", uploadTables.get(i).gettableName())
                    .putInt("position", i)
                    //    .putString("data", uploadData.get(i).toString())

                    //.putString("columns", "_id, sysdate")
                    // .putString("where", where)
                    .build();
            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(DataUpWorkerALL.class)
                    .addTag(String.valueOf(i))
                    .setInputData(data).build();
            workRequests.add(workRequest);

        }

        // FOR SIMULTANEOUS WORKREQUESTS (ALL TABLES DOWNLOAD AT THE SAME TIME)
        WorkManager wm = WorkManager.getInstance();
        WorkContinuation wc = wm.beginWith(workRequests);
        wc.enqueue();

        // FOR WORKREQUESTS CHAIN (ONE TABLE DOWNLOADS AT A TIME)
/*        WorkManager wm = WorkManager.getInstance();
        WorkContinuation wc = wm.beginWith(workRequests.get(0));
        for (int i=1; i < workRequests.size(); i++ ) {
            wc = wc.then(workRequests.get(i));
        }
        wc.enqueue();*/


        wc.getWorkInfosLiveData().observe(this, new Observer<List<WorkInfo>>() {


            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                Log.d(TAG, "workInfos: " + workInfos.size());
                for (WorkInfo workInfo : workInfos) {
                    Log.d(TAG, "workInfo: getState " + workInfo.getState());
                    Log.d(TAG, "workInfo: data " + workInfo.getTags());
                    Log.d(TAG, "workInfo: data " + workInfo.getOutputData().getString("message"));
                    Log.d(TAG, "workInfo: error " + workInfo.getOutputData().getString("error"));
                    Log.d(TAG, "workInfo: position " + workInfo.getOutputData().getInt("position", 0));
                }
                for (WorkInfo workInfo : workInfos) {
                    int position = workInfo.getOutputData().getInt("position", 0);
                    String tableName = uploadTables.get(position).gettableName();

                            /*String progress = workInfo.getProgress().getString("progress");
                            bi.wmError.setText("Progress: " + progress);*/

                    if (workInfo.getState() != null &&
                            workInfo.getState() == WorkInfo.State.SUCCEEDED) {

                        String result = workInfo.getOutputData().getString("message");

                        int sSynced = 0;
                        int sDuplicate = 0;
                        StringBuilder sSyncedError = new StringBuilder();
                        JSONArray json;
                        result = MainApp.downloadData[position];
                        if (result != null) {
                            if (result.length() > 0) {
                                try {
                                    Log.d(TAG, "onPostExecute: " + result);
                                    json = new JSONArray(result);

                                    // DatabaseHelper db = new DatabaseHelper(SyncActivity.this); // Database Helper

                                    Method method = null;
                                    for (Method method1 : db.getClass().getDeclaredMethods()) {

                                        Log.d(TAG, "onChanged Methods: " + method1.getName());
                                        /**
                                         * MAKE SURE TABLE_NAME = <table> IS SAME AS updateSynced<table> :
                                         *
                                         *      -   public static final String TABLE_NAME = "<table>";  // in Contract
                                         *      -   public JSONArray updateSynced<table>() {              // in DatabaseHelper
                                         *
                                         *      e.g: Forms and updateSyncedForms
                                         *
                                         */
                                        Log.d(TAG, "onChanged Names: updateSynced" + tableName);
                                        Log.d(TAG, "onChanged Compare: " + method1.getName().equals("updateSynced" + tableName));
                                        if (method1.getName().equals("updateSynced" + tableName)) {
                                            method = method1;
                                            Toast.makeText(SyncActivity.this, "updateSynced not found: updateSynced" + tableName, Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }
                                    if (method != null) {
                                        for (int i = 0; i < json.length(); i++) {
                                            JSONObject jsonObject = new JSONObject(json.getString(i));
                                            Log.d(TAG, "onChanged: " + json.getString(i));
                                            if (jsonObject.getString("status").equals("1") && jsonObject.getString("error").equals("0")) {
                                                method.invoke(db, jsonObject.getString("id"));
                                                sSynced++;
                                            } else if (jsonObject.getString("status").equals("2") && jsonObject.getString("error").equals("0")) {
                                                method.invoke(db, jsonObject.getString("id"));
                                                sDuplicate++;
                                            } else {
                                                sSyncedError.append("\nError: ").append(jsonObject.getString("message"));
                                            }
                                        }
                                        Toast.makeText(SyncActivity.this, tableName + " synced: " + sSynced + "\r\n\r\n Errors: " + sSyncedError, Toast.LENGTH_SHORT).show();

                                        if (sSyncedError.toString().equals("")) {
                                            uploadTables.get(position).setmessage(tableName + " synced: " + sSynced + "\r\n\r\n Duplicates: " + sDuplicate + "\r\n\r\n Errors: " + sSyncedError);
                                            uploadTables.get(position).setstatus("Completed");
                                            uploadTables.get(position).setstatusID(3);
                                            syncListAdapter.updatesyncList(uploadTables);
                                        } else {
                                            uploadTables.get(position).setmessage(tableName + " synced: " + sSynced + "\r\n\r\n Duplicates: " + sDuplicate + "\r\n\r\n Errors: " + sSyncedError);
                                            uploadTables.get(position).setstatus("Process Failed");
                                            uploadTables.get(position).setstatusID(1);
                                            syncListAdapter.updatesyncList(uploadTables);
                                        }
                                    } else {
                                        uploadTables.get(position).setmessage("Method not found: updateSynced" + tableName);
                                        uploadTables.get(position).setstatus("Process Failed");
                                        uploadTables.get(position).setstatusID(1);
                                        syncListAdapter.updatesyncList(uploadTables);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(SyncActivity.this, "Sync Result:  " + result, Toast.LENGTH_SHORT).show();

                                    if (result.equals("No new records to sync.")) {
                                        uploadTables.get(position).setmessage(result /*+ " Open Forms" + String.format("%02d", unclosedForms.size())*/);
                                        uploadTables.get(position).setstatus("Not processed");
                                        uploadTables.get(position).setstatusID(4);
                                        syncListAdapter.updatesyncList(uploadTables);
                                    } else {
                                        uploadTables.get(position).setmessage(result);
                                        uploadTables.get(position).setstatus("Process Failed");
                                        uploadTables.get(position).setstatusID(1);
                                        syncListAdapter.updatesyncList(uploadTables);
                                    }
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                    uploadTables.get(position).setstatus("Process Failed");
                                    uploadTables.get(position).setstatusID(1);
                                    uploadTables.get(position).setmessage(e.getMessage());
                                    syncListAdapter.updatesyncList(uploadTables);
                                }
                            } else {
                                uploadTables.get(position).setmessage("Received: " + result.length() + "");
                                uploadTables.get(position).setstatus("Successful");
                                uploadTables.get(position).setstatusID(3);
                                syncListAdapter.updatesyncList(uploadTables);
//                pd.show();
                            }
                        } else {
                            uploadTables.get(position).setstatus("Process Failed");
                            uploadTables.get(position).setstatusID(1);
                            uploadTables.get(position).setmessage("Server not found!");
                            syncListAdapter.updatesyncList(uploadTables);
//            pd.show();
                        }
                    }
                    //mTextView1.append("\n" + workInfo.getState().name());
                    if (workInfo.getState() == WorkInfo.State.FAILED) {
                        String message = workInfo.getOutputData().getString("error");
                        uploadTables.get(position).setstatus("Process Failed");
                        uploadTables.get(position).setstatusID(1);
                        uploadTables.get(position).setmessage(message);
                        syncListAdapter.updatesyncList(uploadTables);

                    }
                }
            }
        });

    }
}
