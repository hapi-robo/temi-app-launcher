// Reference: https://www.androidhive.info/2016/01/android-working-with-recycler-view/

package com.hapirobo.applauncher;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "App Launcher";

    private List<Package> packageList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PackageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mAdapter = new PackageAdapter(packageList);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Package pkg = packageList.get(position);
//                Toast.makeText(getApplicationContext(), pkg.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                launchApp(pkg.getName());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        preparePackageData();
    }

    private void preparePackageData() {
        // get a list of installed apps
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        // collect all package names
        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package:" + packageInfo.packageName);
            Log.d(TAG, "Source dir:" + packageInfo.sourceDir);
            Log.d(TAG, "Launch Activity: " + pm.getLaunchIntentForPackage(packageInfo.packageName));
            Package pkg = new Package(packageInfo.packageName, packageInfo.sourceDir);
            packageList.add(pkg);
        }

        mAdapter.notifyDataSetChanged();
    }

    protected void launchApp(String packageName) {
        Intent mIntent = getPackageManager().getLaunchIntentForPackage(packageName);

        if (mIntent != null) {
            try {
                startActivity(mIntent);
            } catch (ActivityNotFoundException err) {
                Toast t = Toast.makeText(getApplicationContext(), R.string.app_not_found, Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }
}
