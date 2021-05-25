package edu.aku.hassannaqvi.sewage_sample.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.aku.hassannaqvi.sewage_sample.R;
import edu.aku.hassannaqvi.sewage_sample.databinding.SyncListAdapterBinding;
import edu.aku.hassannaqvi.sewage_sample.models.SyncModel;


public class UploadListAdapter extends RecyclerView.Adapter<UploadListAdapter.UploadListViewHolder> {
    List<SyncModel> uploadlist;
    UploadListViewHolder holder;

    public UploadListAdapter(List<SyncModel> uploadlist) {
        this.uploadlist = uploadlist;
        this.setHasStableIds(true);
    }

    private int checkStatus(int i) {
        switch (i) {
            case 1:
                return Color.RED;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.GRAY;
            default:
                return Color.BLACK;
        }
    }

    public void updatesyncList(List<SyncModel> uploadlist) {
        this.uploadlist = uploadlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UploadListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sync_list_adapter, parent, false);
        return new UploadListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadListViewHolder holder, int position) {
        this.holder = holder;
        this.holder.bindUser(this.uploadlist.get(position));
    }

    @Override
    public int getItemCount() {
        return uploadlist.size() > 0 ? uploadlist.size() : 0;
    }

    public class UploadListViewHolder extends RecyclerView.ViewHolder {
        SyncListAdapterBinding binding;

        public UploadListViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindUser(SyncModel model) {
            binding.statusColor.setBackgroundColor(checkStatus(model.getstatusID()));
            binding.tvTableName.setText(model.gettableName());
            binding.tvStatus.setText(model.getstatus());
            binding.tvMsg.setText(model.getmessage());
            if (model.getstatusID() == 1 || model.getstatusID() == 3 || model.getstatusID() == 4) {
                binding.pb.setVisibility(View.GONE);
            } else {
                binding.pb.setVisibility(View.VISIBLE);
            }
        }
    }
}
