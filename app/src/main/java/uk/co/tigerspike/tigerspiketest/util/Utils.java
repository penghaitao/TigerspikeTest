package uk.co.tigerspike.tigerspiketest.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import uk.co.tigerspike.tigerspiketest.App;

import static uk.co.tigerspike.tigerspiketest.imagedetail.ImageDetailActivity.REQUEST_CODE_ASK_PERMISSIONS;

/**
 * Created by haitao on 02/06/2017.
 */

public class Utils {
    public static void init() {
        mkDirs(getImageDirectory());
    }

    public static void mkDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String getImageDirectory() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory() + "/Tigerspike/";
        } else {
            return App.context.getCacheDir() + "/Tigerspike/";
        }
    }

    public static Uri saveImage(ImageView itemView, String filename) {
        BitmapDrawable bitmapDrawable = ((BitmapDrawable) itemView.getDrawable());
        if (bitmapDrawable != null) {
            Bitmap bitmap = bitmapDrawable.getBitmap();
            Uri contentUri = getImageUri(bitmap, filename);
            savetoAlbum(contentUri);
            return contentUri;
        } else {
            return null;
        }
    }

    public static void savetoAlbum(Uri contentUri) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(contentUri);
        App.context.sendBroadcast(mediaScanIntent);
    }

    public static Uri getImageUri(Bitmap bitmap, String filename) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        String path = Utils.getImageDirectory();
        File file = new File(path, filename);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(file);
    }

    public static boolean verifyStoragePermissions(Activity activity) {
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(App.context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return false;
        } else {
            Utils.init();
            return true;
        }
    }
}
