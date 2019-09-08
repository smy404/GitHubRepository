package com.example.showapplist;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

public class AppListFragment extends ListFragment {

	private ArrayList<AppInfo> appList = new ArrayList<AppInfo>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getAppList();
		AppAdapter adapter = new AppAdapter(this.getActivity(), appList);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//
		Intent appIntent = appList.get(position).appIntent;
		if (appIntent != null) {
			startActivity(appIntent);
		} else {
			Toast toast = Toast.makeText(getActivity(),"Intent is null", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	/**
	 *
	 */
	private void getAppList() {
		PackageManager pm = this.getActivity().getPackageManager();
		// Return a List of all packages that are installed on the device.
		List<PackageInfo> packages = pm.getInstalledPackages(0);
		/*foreach 循环语法：
		*　for(元素类型type  元素变量value : 遍历对象obj)
		 *  { }
		*/
		for (PackageInfo packageInfo : packages) {
			//
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)	// 判断是第三方应用
			{
				AppInfo info = new AppInfo();
				info.appName = packageInfo.applicationInfo.loadLabel(pm)
						.toString();
				info.pkgName = packageInfo.packageName;
				info.appIcon = packageInfo.applicationInfo.loadIcon(pm);
				//
				info.appIntent = pm.getLaunchIntentForPackage(packageInfo.packageName); 
				appList.add(info);
			} else {
				//
			}

		}
	}
}
