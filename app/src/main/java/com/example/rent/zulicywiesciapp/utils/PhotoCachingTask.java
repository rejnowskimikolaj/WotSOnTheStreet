package com.example.rent.zulicywiesciapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by User on 2017-04-02.
 */

public class PhotoCachingTask extends AsyncTask<Bitmap,Void,File> {

    private Context context;
    private OnPictureCachedListener listener;


    public PhotoCachingTask(Context context, OnPictureCachedListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected File doInBackground(Bitmap... params) {
        Bitmap bitmap = params[0];
        File file = null;
        try {
            file =  getCachedFileFromBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private File getCachedFileFromBitmap(Bitmap bitmap) throws IOException {

        File f = new File(context.getCacheDir(), "imageToUpload");
        f.createNewFile();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();
        return f;
    }

    @Override
    protected void onPostExecute(File file) {

        listener.onPictureCached(file);
    }

    public interface OnPictureCachedListener{
        void onPictureCached(File file);
    }
}
