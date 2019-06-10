package com.ibt.complaintmanagement.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ibt.complaintmanagement.R;
import com.ibt.complaintmanagement.modal.complaint_category.SubCategory;

import java.util.List;

public class SpinnerSubCategoryAdapter extends ArrayAdapter<SubCategory> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<SubCategory> items;
    private final int mResource;

    public SpinnerSubCategoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<SubCategory> objects) {
        super(context, 0, objects);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(mResource, parent, false);

        TextView txtMchId = view.findViewById(R.id.tvCategory);

        SubCategory urlModal = items.get(position);
        txtMchId.setText(urlModal.getCOMPTYPE());
        return view;
    }
}