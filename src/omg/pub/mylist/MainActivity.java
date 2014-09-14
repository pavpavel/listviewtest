package omg.pub.mylist;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends Activity
{
    // Progress Dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
    ArrayList<ListItem> values = new ArrayList<ListItem>();
    ListView listview = null;
    private ProgressDialog prgDialog;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        String fName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/qlog.log";
        setContentView(R.layout.main);
        listview = (ListView) findViewById(R.id.listview);
        new OpenFile().execute(fName);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
                prgDialog = new ProgressDialog(this);
                prgDialog.setMessage("loading logcat file. \nPlease wait...");
                prgDialog.setIndeterminate(false);
                prgDialog.setMax(100);
                prgDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                prgDialog.setCancelable(false);
                prgDialog.show();
                return prgDialog;
            default:
                return null;
        }
    }

    private void parseValues(ArrayList<ListItem> values) {
        Log.e("parseValues", "a jsme tu");

        if (values != null) {
            MyCustomAdapter adapter = new MyCustomAdapter(this, R.layout.listview_item_row, values);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, final View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class OpenFile extends AsyncTask<String, String, String> {
        // Progress Dialog type (0 - for Horizontal progress bar)
        public static final int progress_bar_type = 0;
        long total;
        long lenghtOfFile;
        private ArrayList<ListItem> data;
        private String str = ".";

        // Show Progress bar before downloading Music
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Shows Progress Bar Dialog and then call doInBackground method
            showDialog(progress_bar_type);
        }

        // Download Music File from Internet
        @Override
        protected String doInBackground(String... fName) {
            data = new ArrayList<ListItem>();
            int count;
            Log.e("doInBackground", fName[0].toString());
            File f = new File(fName[0]);

            lenghtOfFile = f.length();
            Log.i("doInBackground", String.valueOf(lenghtOfFile));
            // input stream to read file - with 8k buffer
            if (f.canRead()) {
                try {
                    FileInputStream fis = new FileInputStream(f);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    DataInputStream dis = new DataInputStream(bis);
                    String line;
                    total = 0;
                    final Set<String> FILTER = new HashSet<String>(Arrays.asList(
                            new String[]{"302", "909"}
                    ));

                    while ((line = dis.readLine()) != null) {
                        total += line.getBytes().length;
                        publishProgress(String.valueOf((int) ((total * 100) / lenghtOfFile)));
                        String[] st = line.split(" ");
                        if (!FILTER.contains(st[3])) {
                            this.data.add(new ListItem(st[3], st[4], st[2], st[0] + " " + st[1], line));
                            //Log.i("New line loaded:", line );
                        }
                    }
                    dis.close();


                } catch (FileNotFoundException e1) {
                    Log.e("pdub FileLoader", e1.getStackTrace().toString());
                } catch (IOException e1) {
                    Log.e("pdub FileLoader", e1.getStackTrace().toString());
                }


            } else {
                Log.e("pdub FileLoader", "Can't read the file");
            }

            return null;
        }

        // While Downloading Music File
        protected void onProgressUpdate(String... progress) {
            // Set progress percentage

            prgDialog.setMessage("Please wait(loading file)\n\nloaded:" + total / 1024 + " Kb z " + lenghtOfFile / 1024 + " KB");

            prgDialog.setProgress(Integer.valueOf(progress[0]));
        }

        // Once Music File is downloaded
        @Override
        protected void onPostExecute(String fName) {
            // Dismiss the dialog after the Music file was downloaded
            Log.e("onPOSTEXECUTE", "a jsme tu");

            dismissDialog(progress_bar_type);
            parseValues(this.data);

        }
    }


}
