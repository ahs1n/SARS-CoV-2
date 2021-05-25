package edu.aku.hassannaqvi.sewage_sample.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import edu.aku.hassannaqvi.sewage_sample.R;
import edu.aku.hassannaqvi.sewage_sample.database.DatabaseHelper;
import edu.aku.hassannaqvi.sewage_sample.models.Form;

/**
 * @author hassan.naqvi on 8/1/2016.
 */
public class FormsAdapter extends RecyclerView.Adapter<FormsAdapter.ViewHolder> {
    Context c;
    DatabaseHelper db;
    private List<Form> form = Collections.emptyList();

    // Provide a suitable constructor (depends on the kind of dataset)
    public FormsAdapter(List<Form> form, Context c) {
        this.form = form;
        this.c = c;
        Log.d("TAG:count", String.valueOf(getItemCount()));
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forms_list_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        db = new DatabaseHelper(c);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        int childCount = 0;
        //childCount = db.getChildrenByUUID(form.get(position).get_UID());
        int photoChild = 0;
        //photoChild = db.getChildrenPhotoCheck(form.get(position).get_UID());
        int cardChild = 0;
        //cardChild = db.getChildrenCardCheck(form.get(position).get_UID());


        String iStatus = "Status  Unknown";
        int iColor = 0;
        switch (form.get(position).getIstatus()) {
            case "1":
                iStatus = "Complete";
                iColor = Color.GREEN;
                break;
            case "2":
                iStatus = "Incomplete";
                iColor = Color.RED;
                break;
            case "3":
                iStatus = "Refused";
                iColor = Color.RED;
                break;
            case "96":
                iStatus = "Other Reason";
                iColor = Color.RED;
                break;
            default:
                iStatus = "Open Form";
                iColor = Color.RED;
                break;

        }

//        holder.hhno.setText(form.get(position).getMp101());
        holder.istatus.setText(iStatus);
        holder.sysdate.setText(form.get(position).getSysdate());
        holder.istatus.setTextColor(iColor);
//        holder.cluster.setText(form.get(position).getMp102());


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return form.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {


        public RecyclerView rv;
        public TextView sysdate;
        public TextView cluster;
        public TextView hhno;
        public TextView istatus;
        // each data item is just a string in this case

        public ViewHolder(View v) {
            super(v);
//            rv = v.findViewById(R.id.FormsList);
            sysdate = v.findViewById(R.id.sysdate);
            cluster = v.findViewById(R.id.cluster);
            hhno = v.findViewById(R.id.hhno);
            istatus = v.findViewById(R.id.istatus);

        }


    }
}