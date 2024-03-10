package com.aditya.backgroundtask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class Bg extends AsyncTask<String,Void,String>{

        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("adityaApp", "onPreExecute: app is working");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("adityaApp", "onPreExecute: app is working1");
            Log.d("adityaApp", s);
        }

        @Override
        protected String doInBackground(String... urls) {
            Log.d("adityaApp", "onPreExecute: app is working1");
            String result="";
            URL url;
            HttpURLConnection conn;
            try{
                url= new URL(urls[0]);
                conn=(HttpURLConnection) url.openConnection();
                InputStream in=conn.getInputStream();
                InputStreamReader reader= new InputStreamReader(in);
                int data=reader.read();
                while(data !=-1){
                    //type-casting data to char
                    char current=(char) data;
                    result=result+current;
                    data=reader.read();
                }
            }
            catch(Exception e){
                e.printStackTrace();
                return "Something went wrong";
            }
            return result;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bg myTask=new Bg();
        myTask.execute("https//www.codewithharry.com");
    }

    public void handle(View view){
        Toast.makeText(this,"This is toast",Toast.LENGTH_SHORT).show();
    }
}


//package com.aditya.backgroundtask;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.loader.app.LoaderManager;
//import androidx.loader.content.AsyncTaskLoader;
//import androidx.loader.content.Loader;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
//
//    private static final int LOADER_ID = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Initialize the loader
//        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
//    }
//
//    @Override
//    public Loader<String> onCreateLoader(int id, Bundle args) {
//        return new BgLoader(this);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<String> loader, String data) {
//        // This method is called when the background task is complete
//        Log.d("adityaApp", "onLoadFinished: app is working1");
//        Log.d("adityaApp", data);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<String> loader) {
//        // Reset any resources if needed
//    }
//
//    public void handle(View view){
//        Toast.makeText(this, "This is toast", Toast.LENGTH_SHORT).show();
//
//        // Start the background task when the button is clicked
//        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
//    }
//
//    static class BgLoader extends AsyncTaskLoader<String> {
//
//        public BgLoader(Context context) {
//            super(context);
//        }
//
//        @Override
//        protected void onStartLoading() {
//            forceLoad();
//        }
//
//        @Override
//        public String loadInBackground() {
//            // Background task implementation goes here
//            String result = "";
//            try {
//                URL url = new URL("https://www.codewithharry.com");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                InputStream in = conn.getInputStream();
//                InputStreamReader reader = new InputStreamReader(in);
//                int data = reader.read();
//                while (data != -1) {
//                    char current = (char) data;
//                    result = result + current;
//                    data = reader.read();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "Something went wrong";
//            }
//            return result;
//        }
//    }
//}
