package com.hapirobo.applauncher;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.MyViewHolder> {
    private List<Package> packageList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView label, package_name;

        public MyViewHolder(View view) {
            super(view);
            label = (TextView) view.findViewById(R.id.label);
            package_name = (TextView) view.findViewById(R.id.package_name);
        }
    }

    public PackageAdapter(List<Package> packageList) {
        this.packageList = packageList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Package pkg = packageList.get(position);
        holder.label.setText(pkg.getLabel());
        holder.package_name.setText(pkg.getPackageName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return packageList.size();
    }
}
