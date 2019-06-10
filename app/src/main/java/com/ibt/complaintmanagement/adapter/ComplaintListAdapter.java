package com.ibt.complaintmanagement.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.modal.complaint_list.ComplaintList;
import com.ibt.complaintmanagement.ui.activities.ComplaintDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ComplaintListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<ComplaintList> orderProductArrayList;

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;
    private String errorMsg;
    private Activity activity;

    public ComplaintListAdapter(Context mContext, Activity activity) {
        this.mContext = mContext;
        orderProductArrayList = new ArrayList<>();
        this.activity = activity;
    }

    public List<ComplaintList> getTripList() {
        return orderProductArrayList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (i) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.row_complaint_list, viewGroup, false);
                viewHolder = new ItemViewHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, viewGroup, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {
        switch (getItemViewType(i)) {
            case ITEM:
                final ItemViewHolder viewHolder = (ItemViewHolder) holder;
                final ComplaintList complaint = orderProductArrayList.get(i);
                viewHolder.txtDate.setText(complaint.getCOMPDATE());
                viewHolder.txtComplaint.setText(complaint.getCOMPID());
                viewHolder.txtDescription.setText(complaint.getCOMPDESC());
                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, ComplaintDetailActivity.class);
                        intent.putExtra("data", (Parcelable) complaint);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;
                if (retryPageLoad) {
                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
                    loadingVH.mProgressBar.setVisibility(View.GONE);
                    loadingVH.mErrorTxt.setText(errorMsg != null ? errorMsg : mContext.getString(R.string.error_msg_unknown));
                } else {
                    loadingVH.mErrorLayout.setVisibility(View.GONE);
                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    @Override
    public int getItemViewType(int position) {
        if (orderProductArrayList.size() > 0) {
            return (position == orderProductArrayList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
        } else {
            return position;
        }
    }

    @Override
    public int getItemCount() {
        return orderProductArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDate, txtDescription, txtComplaint;
        private CardView cardView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtComplaint = itemView.findViewById(R.id.txtComplaint);
        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgressBar mProgressBar;
        private ImageButton mRetryBtn;
        private TextView mErrorTxt;
        private LinearLayout mErrorLayout;

        public LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.loadmore_progress);
            mRetryBtn = itemView.findViewById(R.id.loadmore_retry);
            mErrorTxt = itemView.findViewById(R.id.loadmore_errortxt);
            mErrorLayout = itemView.findViewById(R.id.loadmore_errorlayout);

            mRetryBtn.setOnClickListener(this);
            mErrorLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loadmore_retry:
                case R.id.loadmore_errorlayout:
                    showRetry(false, null);
                    break;
            }
        }
    }

    public void add(ComplaintList r) {
        orderProductArrayList.add(r);
        // notifyItemInserted(orderProductArrayList.size() - 1);
        notifyDataSetChanged();
    }

    public void addAll(List<ComplaintList> moveResults) {
        for (ComplaintList result : moveResults) {
            add(result);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new ComplaintList());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = orderProductArrayList.size() - 1;
        ComplaintList result = getItem(position);

        if (result != null) {
            orderProductArrayList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ComplaintList getItem(int position) {
        return orderProductArrayList.get(position);
    }

    public void showRetry(boolean show, @Nullable String errorMsg) {
        retryPageLoad = show;
        notifyItemChanged(orderProductArrayList.size() - 1);

        if (errorMsg != null) this.errorMsg = errorMsg;
    }

}
