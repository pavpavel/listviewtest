package omg.pub.mylist;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.app.*;
import java.util.zip.*;
import android.graphics.*;

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
				LayoutInflater inflater = ((Activity)context).getLayoutInflater();			
				holder = new DataHolder();
				tst="<o>";
				row = inflater.inflate(layoutResourceId, null);
				holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
				holder.txttext = (TextView)row.findViewById(R.id.txttext);
				holder.linearView = (LinearLayout)row.findViewById(R.id.linearView);
				
				
				row.setTag(holder);
			}
			else
			{
				
				tst=convertView.toString();
				holder = (DataHolder)row.getTag();
				
			}

			
			holder.txtTitle.setText("["+tst+"] Process ID:"+data.getPID(position)+" || Process Name:" + data.getProcessName(position) + " || Message type: [" + data.getType(position)+"]");
			holder.txttext.setText(data.getLine(position));
			String t = data.getType(position);
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
			TextView txtTitle;
			LinearLayout linearView;
		}
	
	
}
