package com.rock.software.torrent.Activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rock.software.torrent.R;

import java.util.List;

public class AppListActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        mTextView = (TextView) findViewById(R.id.text);
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> list = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);
        StringBuilder stringBuilder = new StringBuilder();
        for (PackageInfo packageInfo : list) {
            stringBuilder
                    .append("package name:"
                            + packageInfo.packageName
                            + "\n");
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            stringBuilder
                    .append("应用名称:"
                            + applicationInfo
                            .loadLabel(packageManager)
                            + "\n");
//            if (packageInfo.permissions != null) {
//                for (PermissionInfo p : packageInfo.permissions) {
//                    stringBuilder
//                            .append("权限包括:"
//                                    + p.name
//                                    + "\n");
//                }
//            }
//            stringBuilder.append("\n");
        }
        mTextView.setText(stringBuilder
                .toString());
    }
}
