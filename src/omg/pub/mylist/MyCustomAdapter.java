package omg.pub.mylist;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.app.*;
import java.util.zip.*;

public class MyCustomAdapter extends ArrayAdapter<MyArrayList>
{

		Context context; 
		int layoutResourceId;    
		MyArrayList data = null;
		
		public MyCustomAdapter(Context context, int layoutResourceId, MyArrayList data) {
			super(context,layoutResourceId,data.getLines());
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
				LayoutInflater inflater = ((Activity)context).getLayoutInflater()-;			
				holder = new DataHolder();
				if (data.getType(position).equals("E")){
					tst="E";
					row = inflater.inflate(R.layout.listview_item_row,null);
					holder.txtTitle = (TextView)row.findViewById(R.id.txttext);
					holder.txttext = (TextView)row.findViewById(R.id.txttext);
				}else if (data.getType(position).equals("W")){
					tst ="W";
					row = inflater.inflate(R.layout.listview_item_row,null);
					holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
					holder.txttext = (TextView)row.findViewById(R.id.txttext);
				}else{
					tst="<o>";
					row = inflater.inflate(layoutResourceId, null);
					holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
					holder.txttext = (TextView)row.findViewById(R.id.txttext);
				}
				
				row.setTag(holder);
			}
			else
			{
				
				tst=convertView.toString();
				holder = (DataHolder)row.getTag();
				
			}

			
			holder.txtTitle.setText("["+tst+"] Process ID:"+data.getPID(position)+" || Process Name:" + data.getProcessName(position) + " || Message type: [" + data.getType(position)+"]");
			holder.txttext.setText(data.getLine(position));

			return row;
		}

		static class DataHolder
		{
			TextView txttext;
			TextView txtTitle;
		}
	
	
}
