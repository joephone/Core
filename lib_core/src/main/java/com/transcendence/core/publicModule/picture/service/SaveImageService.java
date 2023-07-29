package com.transcendence.core.publicModule.picture.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import com.transcendence.core.R;
import com.transcendence.core.utils.file.FileNameUtils;
import com.transcendence.core.utils.file.FileTypeUtils;
import com.transcendence.core.utils.file.FileUtils;
import com.transcendence.core.utils.toast.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author Joephone on 2022/6/21 0021 下午 4:45
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class SaveImageService extends IntentService {

    private static final String KEY_PREFIX = SaveImageService.class.getName() + '.';

    private static final String EXTRA_URI = KEY_PREFIX + "uri";
    private static final String EXTRA_FILE = KEY_PREFIX + "file";

    private Handler mServiceHandler;

    public static void start(Uri uri, File file, Context context) {
        Intent intent = new Intent(context, SaveImageService.class)
                .putExtra(EXTRA_URI, uri)
                .putExtra(EXTRA_FILE, file);
        context.startService(intent);
    }

    public SaveImageService() {
        super(SaveImageService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mServiceHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent == null) {
            return;
        }

        Uri uri = intent.getParcelableExtra(EXTRA_URI);
        File file = (File) intent.getSerializableExtra(EXTRA_FILE);
        saveImage(uri, file);
    }

    private void saveImage(Uri uri, File file) {
        try {
            String outputFileName = uri.getLastPathSegment();
            if (TextUtils.isEmpty(outputFileName)) {
                outputFileName = file.getName();
            }
            // Douban stores GIF images with .jpg extension, so we need to correct this.
            String mimeType;
            try (InputStream inputStream = new FileInputStream(file)) {
                mimeType = FileTypeUtils.getImageMimeType(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                mimeType = null;
            }
            if (!TextUtils.isEmpty(mimeType)) {
                String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
                outputFileName = FileNameUtils.replaceExtension(outputFileName, extension);
            }
            File outputFile = FileUtils.makeSaveImageOutputFile(outputFileName);
            FileUtils.copy(file, outputFile);
            MediaScannerConnection.scanFile(this, new String[] { outputFile.getPath() }, null,
                    null);
            postToast(R.string.gallery_save_successful);
        } catch (Exception e) {
            e.printStackTrace();
            postToast(R.string.gallery_save_failed);
        }
    }

    private void postToast(final int resId) {
        mServiceHandler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show(resId, SaveImageService.this);
            }
        });
    }
}
