package com.example.showapplist;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ViewHolder {
	public TextView appName;
	public TextView pkgName;
	public ImageView appIcon;
}

public class AppAdapter extends BaseAdapter {

	List<AppInfo> appList = null;
    LayoutInflater infater = null;
	
    public AppAdapter(Context context, List<AppInfo> apps)
    {
    	this.infater = LayoutInflater.from(context);
    	this.appList = apps;
    }
    
	@Override
	public int getCount() {
		return appList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return appList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = infater.inflate(R.layout.app_item, null);
			holder.appIcon = (ImageView) convertView
					.findViewById(R.id.app_icon);
			holder.appName = (TextView) convertView
					.findViewById(R.id.app_name);
			holder.pkgName = (TextView) convertView
					.findViewById(R.id.pkg_name);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
			/*在第一次创建itemView的时候，完成对控件的绑定，同时吧控件作为一个object--holder，
			把它通过setTag()存到itemView中，再第二次使用的时候就可以通过getTag()把holder取出来直接使用，
			也就是说，在list中itemView相同的情况下，我们只进行了一次的控件资源绑定。
			是不是就省了很多？*/
		}
		
		AppInfo appInfo = (AppInfo) getItem(position);
		
		holder.appIcon.setImageDrawable(appInfo.appIcon);
        holder.appName.setText(appInfo.appName);
        holder.pkgName.setText(appInfo.pkgName);
        
        return convertView;
	}

}
