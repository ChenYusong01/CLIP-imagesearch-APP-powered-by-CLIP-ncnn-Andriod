package com.ChenYusong.ImageSearch;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements SurfaceHolder.Callback
{
    private CLIP clip = new CLIP();

    int currentMode = 0;

    private Button scanGalleryButton;
    private Button extractYearButton;
    private Button extractTotalButton;
    private Button goButton;

    private TextView showYearNum;
    private TextView showTotalNum;

    private ImageView showImage;
    private Bitmap showBitmap;

    private EditText describeText;

    List<String> yearImage;
    List<String> totalImage;

    private int ii = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        scanGalleryButton = (Button) findViewById(R.id.scanGalleryBtn);
        extractYearButton = (Button) findViewById(R.id.extractYearBtn);
        extractTotalButton = (Button) findViewById(R.id.extractTotalBtn);
        goButton = (Button) findViewById(R.id.goBtn);
        extractYearButton.setEnabled(false);
        extractTotalButton.setEnabled(false);
        goButton.setEnabled(false);

        showYearNum = (TextView) findViewById(R.id.yearNum);
        showTotalNum = (TextView) findViewById(R.id.totalNum);

        showImage = (ImageView) findViewById(R.id.showImage);

        describeText = (EditText) findViewById(R.id.getText);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }

        reload();

        scanGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // 扫描相册
                yearImage = getSystemPhotoList(MainActivity.this,System.currentTimeMillis()/1000-365*24*60*60);
                totalImage = getSystemPhotoList(MainActivity.this,0);
                // 显示分类信息
                showYearNum.setText(yearImage.size()+"张");
                showTotalNum.setText(totalImage.size()+"张");
                // 刷新按键
                extractYearButton.setEnabled(true);
                extractTotalButton.setEnabled(true);
            }
        });

        extractYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                currentMode = 2;
                // 提取一年内的图片特征
                clip.clear();
                clip.set(yearImage.size());
                for(int i = 0; i < yearImage.size(); i++){
                    clip.encodeImage(BitmapFactory.decodeFile(yearImage.get(i)),i);
                }
                // 刷新按键
                goButton.setEnabled(true);
            }
        });
        extractTotalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                currentMode = 1;
                // 提取所有图片特征
                clip.clear();
                clip.set(totalImage.size());
                for(int i = 0; i < totalImage.size(); i++){
                    clip.encodeImage(BitmapFactory.decodeFile(totalImage.get(i)),i);
                }
                // 刷新按键
                goButton.setEnabled(true);
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // 提取文本特侦
                clip.encodeText(describeText.getText().toString());
                // 获取匹配图片的下标
                int result = clip.go();
                // 按照模式和下标索引图片并显示
                if(currentMode == 2){
                    showBitmap = BitmapFactory.decodeFile(yearImage.get(result));
                } else if(currentMode == 1){
                    showBitmap = BitmapFactory.decodeFile(totalImage.get(result));
                }
                showImage.setImageBitmap(showBitmap);
            }
        });

    }

    public static List<String> getSystemPhotoList(Context context, long threshTimestamp)
    {
        List<String> result = new ArrayList<String>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String order = MediaStore.MediaColumns.DATE_ADDED+" DESC ";
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, order);
        if (cursor == null || cursor.getCount() <= 0) return result; // 没有图片
        while (cursor.moveToNext())
        {
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(index); // 文件地址
            File file = new File(path);
            long fileTimestamp = Long.parseLong(cursor.getString(cursor.getColumnIndex("date_added")));
            if (file.exists() && fileTimestamp>threshTimestamp)
            {
                result.add(path);
            }
        }

        return result ;
    }

    private void reload() {

        String path = MainActivity.this.getFilesDir().getAbsolutePath();
        String name = "vocab.txt";
        copy(MainActivity.this, name, path, name);
        String file = path+File.separator+name;

        boolean ret_init = clip.loadCLIP(getAssets(), file);
        if (!ret_init) {
            Log.e("MainActivity", "loadModel failed");
        }
    }

    private void copy(Context myContext, String ASSETS_NAME, String savePath, String saveName) {
        String filename = savePath + "/" + saveName;
        File dir = new File(savePath);
        if (!dir.exists())
            dir.mkdir();
        try {
            if (!(new File(filename)).exists()) {
                InputStream is = myContext.getResources().getAssets().open(ASSETS_NAME);
                FileOutputStream fos = new FileOutputStream(filename);
                byte[] buffer = new byte[7168];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }
}
