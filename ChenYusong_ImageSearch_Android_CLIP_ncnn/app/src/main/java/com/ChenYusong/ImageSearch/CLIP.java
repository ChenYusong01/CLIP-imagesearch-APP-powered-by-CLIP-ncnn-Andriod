package com.ChenYusong.ImageSearch;

import android.content.res.AssetManager;
import android.graphics.Bitmap;

public class CLIP
{
    public native boolean loadCLIP(AssetManager mgr, String vocab);
    public native boolean set(int num);
    public native boolean encodeImage(Bitmap bitmap, int cnt);
    public native boolean encodeText(String in);
    public native int go();
    public native boolean clear();

    static {
        System.loadLibrary("clipis");
    }
}
