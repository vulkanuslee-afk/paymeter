package com.paymeter.app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        webView.setBackgroundColor(Color.parseColor("#0a0e12")); // 로딩 중 흰 화면 방지

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);                 // 설정/기록 저장(localStorage)
        settings.setMediaPlaybackRequiresUserGesture(false); // 알람·효과음 자동 재생
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.setWebViewClient(new WebViewClient());

        // 영수증 이미지 저장/공유용 브리지
        webView.addJavascriptInterface(new PayMeterBridge(), "PayMeterBridge");

        setContentView(webView);
        webView.loadUrl("file:///android_asset/index.html");
    }

    /** 웹(JS)에서 호출하는 네이티브 기능 */
    private class PayMeterBridge {

        /** 영수증 PNG를 갤러리에 저장 */
        @JavascriptInterface
        public boolean saveImage(String base64Png, String fileName) {
            try {
                byte[] data = Base64.decode(base64Png, Base64.DEFAULT);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // Android 10+ : MediaStore 사용 (별도 권한 불필요)
                    ContentValues cv = new ContentValues();
                    cv.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
                    cv.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
                    cv.put(MediaStore.Images.Media.RELATIVE_PATH,
                            Environment.DIRECTORY_PICTURES + "/PayMeter");
                    Uri uri = getContentResolver()
                            .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
                    if (uri == null) return false;
                    OutputStream os = getContentResolver().openOutputStream(uri);
                    if (os == null) return false;
                    os.write(data);
                    os.close();
                    return true;
                } else {
                    // Android 9 이하 : 앱 전용 폴더에 저장 후 갤러리에 알림
                    File dir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "PayMeter");
                    if (!dir.exists() && !dir.mkdirs()) return false;
                    File out = new File(dir, fileName);
                    FileOutputStream fos = new FileOutputStream(out);
                    fos.write(data);
                    fos.close();
                    Intent scan = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    scan.setData(Uri.fromFile(out));
                    sendBroadcast(scan);
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }

        /** 영수증 PNG를 다른 앱으로 공유 (인증하기) */
        @JavascriptInterface
        public void shareImage(String base64Png, String text) {
            try {
                byte[] data = Base64.decode(base64Png, Base64.DEFAULT);
                File dir = new File(getCacheDir(), "share");
                if (!dir.exists() && !dir.mkdirs()) return;
                File out = new File(dir, "paymeter_receipt.png");
                FileOutputStream fos = new FileOutputStream(out);
                fos.write(data);
                fos.close();

                Uri uri = FileProvider.getUriForFile(
                        MainActivity.this, getPackageName() + ".fileprovider", out);

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/png");
                share.putExtra(Intent.EXTRA_STREAM, uri);
                if (text != null && !text.isEmpty()) {
                    share.putExtra(Intent.EXTRA_TEXT, text);
                }
                share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(share, "영수증 인증하기"));
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) webView.destroy();
        super.onDestroy();
    }
}
