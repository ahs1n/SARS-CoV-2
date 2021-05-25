package edu.aku.hassannaqvi.sewage_sample.ui.list_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.aku.hassannaqvi.sewage_sample.R;
import edu.aku.hassannaqvi.sewage_sample.adapters.FormsAdapter;
import edu.aku.hassannaqvi.sewage_sample.database.DatabaseHelper;
import edu.aku.hassannaqvi.sewage_sample.models.Form;

public class FormsReportCluster extends AppCompatActivity {
    DatabaseHelper db;
    Collection<Form> form;
    String sysdateToday = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH).format(new Date());
    TextView dtFilter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter formsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_report_cluster);
        recyclerView = findViewById(R.id.fc_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dtFilter = findViewById(R.id.dtFilter);
        db = new DatabaseHelper(this);

        //TODO: commented in TESTING
        /* form = db.getFormsByCluster("0000000");*/

        // specify an adapter (see also next example)
        formsAdapter = new FormsAdapter((List<Form>) form, this);
        recyclerView.setAdapter(formsAdapter);
    }

    public void filterForms(View view) {
        Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();

        //TODO: commented in TESTING
        /* form = db.getFormsByCluster(dtFilter.getText().toString());
           formsAdapter = new FormsAdapter((List<Form>) form, this);
        */
        formsAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(formsAdapter);

    }
}

