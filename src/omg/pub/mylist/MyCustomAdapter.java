package omg.pub.mylist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter<ListItem> {

		Context context; 
		int layoutResourceId;    
		ArrayList<ListItem> data = null;
		
		public MyCustomAdapter(Context context, int layoutResourceId, ArrayList<ListItem> data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
			this.data = data;
			
			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			DataHolder holder = null;
			String tst = null;
			if(row == null)
			{
				LayoutInflater inflater = ((Activity)context).getLayoutInflater();			
				holder = new DataHolder();
				tst="<o>";
				row = inflater.inflate(layoutResourceId, null);
				holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
				holder.txttext = (TextView)row.findViewById(R.id.txttext);
				holder.datetime = (TextView)row.findViewById(R.id.txtdatetime);
				holder.linearView = (LinearLayout)row.findViewById(R.id.linearView);
				
				
				row.setTag(holder);
			}
			else
			{
				
				tst=convertView.toString();
				holder = (DataHolder)row.getTag();
				
			}

			
			holder.txtTitle.setText("("+data.get(position).getPid()+") --" + data.get(position).getPn()+ " [" + data.get(position).getType()+"]");
			holder.txttext.setText(data.get(position).getT());
			holder.datetime.setText(data.get(position).getDt());
			String t = data.get(position).getType();
			if (t.equals("E")){
				holder.linearView.setBackgroundColor(Color.RED);
			}else if (t.equals("W")){
				holder.linearView.setBackgroundColor(Color.YELLOW);
			}else{
				holder.linearView.setBackgroundColor(Color.WHITE);
			}
			return row;
		}

		static class DataHolder
		{
			TextView txttext;
			TextView datetime;
			TextView txtTitle;
			LinearLayout linearView;
		}
	
	
}
