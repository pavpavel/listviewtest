package omg.pub.mylist;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		final ListView listview = (ListView) findViewById(R.id.listview);
		try
		{
			MyArrayList values = readFile();
			if(values != null){
			MyCustomAdapter adapter = new MyCustomAdapter(this,R.layout.listview_item_row,values);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView parent,final View view,int position,long id){
					view.animate().setDuration(1000).alpha(50);
					Toast.makeText(getApplicationContext(),Integer.toString(position),Toast.LENGTH_SHORT).show();
				}
			});
			}
		}
		catch (IOException e)
		{}
    }

	private MyArrayList readFile() throws IOException
	{
		MyArrayList file = new MyArrayList();
		String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/qlog.log";
		fileName.trim();
		File f;
		f = new File(fileName);
		if (f.canRead()){
			Toast.makeText(getApplicationContext(),"exostuje",Toast.LENGTH_SHORT).show();
		}
		try
		{
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);
			String line;
			while ((line = dis.readLine()) != null){
				String[] st = line.split(" ");
				//Toast.makeText(getApplicationContext(),";"+st[2]+";",Toast.LENGTH_LONG).show();
				
				if (!st[3].equals("302")){
					file.add(line,st[3],st[2],st[4]);
				}
			} 
			dis.close();
		}
		catch (FileNotFoundException e)
		{
			Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
			
		}
		return file;
	}
}
