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
        public TextView name, directory;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            directory = (TextView) view.findViewById(R.id.directory);
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
        holder.name.setText(pkg.getName());
        holder.directory.setText(pkg.getDirectory());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return packageList.size();
    }
}
