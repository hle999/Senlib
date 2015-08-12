package com.sen.lib.tool.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Senny on 2015/8/12.
 */
public class DiskSenCache {

    public final static String STR_DEFAULT_DIRCTORY_NAME = "bitmap";
    public final static int INT_DEFAULT_MAX_SIZE = 10 * 1024 * 1024;
    public final static int INT_DEFAULT_VALUE_COUNT = 1;

    private static DiskSenCache diskSenCache;

    private DiskLruCache mDiskLruCache;

    public static DiskSenCache getInstance() {
        if (diskSenCache == null) {
            diskSenCache = new DiskSenCache();
        }
        return diskSenCache;
    }

    DiskSenCache() {

    }

    /**
     * Default
     * @param context
     * @return
     */
    public boolean reset(Context context) {
        return reset(context, STR_DEFAULT_DIRCTORY_NAME,
                getAppVersion(context), INT_DEFAULT_VALUE_COUNT, INT_DEFAULT_MAX_SIZE);
    }

    public boolean reset(Context context, String dirName, int appVersion, int valueCount, int maxSize) {
        closeCache();
        File cacheDir = getDiskCacheDir(context, dirName);
        if (cacheDir != null) {
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            try {
                mDiskLruCache = DiskLruCache.open(cacheDir, appVersion, valueCount, maxSize);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void closeCache() {
        if (mDiskLruCache != null && !mDiskLruCache.isClosed()) {
            try {
                mDiskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete() {
        if (mDiskLruCache != null && !mDiskLruCache.isClosed()) {
            try {
                mDiskLruCache.delete();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBitmapToMemoryCache(String bitmapName, Bitmap bitmap) {
        if (mDiskLruCache != null && !mDiskLruCache.isClosed()) {
            try {
                String key = hashKeyForDisk(bitmapName);
                DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                if (editor != null) {
                    OutputStream outputStream = editor.newOutputStream(0);
                    if (addBitmapToStream(bitmap, outputStream)) {
                        editor.commit();
                    } else {
                        editor.abort();
                    }
                }
                mDiskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap getBitmapFromMemoryCache(String bitmapName) {
        if (mDiskLruCache != null && bitmapName != null && !mDiskLruCache.isClosed()) {
            try {
                String key = hashKeyForDisk(bitmapName);
                DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
                if (snapShot != null) {
                    InputStream is = snapShot.getInputStream(0);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    return bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if ((Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || Environment.isExternalStorageRemovable()) && context.getExternalCacheDir() != null) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private boolean addBitmapToStream(Bitmap bitmap, OutputStream outputStream) {
        try {
            if (bitmap != null && !bitmap.isRecycled()) {
                outputStream.write(Bitmap2Bytes(bitmap));
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

}
