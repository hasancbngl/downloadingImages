package com.cobanogluhasan.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImage;
    
    public void downloadImage(View view) {

        //https://upload.wikimedia.org/wikipedia/en/1/18/Venom_%282018_film_poster%29.png

        ImageDownloader task = new ImageDownloader();

        Bitmap imageBitMap = null;

        try {
            imageBitMap = task.execute("https:upload.wikimedia.org/wikipedia/en/1/18/Venom_%282018_film_poster%29.png").get();

            downloadedImage.setImageBitmap(imageBitMap);

        } catch (Exception e) {

            e.printStackTrace();

        }
        
     //   downloadedImage.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         downloadedImage = (ImageView) findViewById(R.id.imageView);

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> //it'll return the image itself.
     {
         @Override
         protected Bitmap doInBackground(String... urls) {

             try {

                 URL url = new URL(urls[0]);

                 HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection() ;

                 urlConnection.connect();

                 InputStream inputStream = urlConnection.getInputStream();

                 Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                 return bitmap;

             }catch (Exception e) {
                 e.printStackTrace();
             }
             return null;
         }
     }





}
