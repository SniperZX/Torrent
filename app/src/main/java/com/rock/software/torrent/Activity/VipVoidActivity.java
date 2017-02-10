package com.rock.software.torrent.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.rock.software.torrent.R;


public class VipVoidActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    private AutoCompleteTextView url;
    private Button search, close;
    private ContentLoadingProgressBar progressBar;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_void);
        initView();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        initWebView(webView);
        url = (AutoCompleteTextView) findViewById(R.id.url);
        String [] arr={"www.iqiyi.com","www.youku.com","www.le.com","www.mgtv.com","v.qq.com"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        url.setAdapter(arrayAdapter);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
        progressBar = (ContentLoadingProgressBar) findViewById(R.id.progress);
        close = (Button) findViewById(R.id.close);
        close.setOnClickListener(this);
    }

    public void initWebView(WebView webView) {
        //设置支持JavaScript脚本
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //去掉放缩的图标
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);


        //设置WebViewClient
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("http")) {
                    view.loadUrl(url);
                }

//                Intent i = new Intent(WebViewActivity.this, WebViewActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("url",url);
//                i.putExtras(bundle);
//                startActivity(i);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                    String url = view.getUrl();
                    if (url.contains("/video/id_")||url.contains("vplay_")||url.contains("v_show/id")||url.contains("iqiyi.com/v")||url.contains("/cover/")||url.contains("x/cover/")||url.contains("ptv/vplay")||url.contains("mgtv.com")) {
                        showDialog(view.getTitle(),url);
                    }
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }


            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {


                return super.onJsAlert(view, url, message, result);
            }


        });


    }

    AlertDialog alertDialog = null;

    public void showDialog(String message, final String url) {
        if(alertDialog!=null) {
            if(!alertDialog.isShowing()) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(message);

                builder.setPositiveButton("观看", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(VipVoidActivity.this, ParserActivity.class);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();

            }else{
                alertDialog.dismiss();
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(message);

                builder.setPositiveButton("观看", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(VipVoidActivity.this, ParserActivity.class);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        }else{
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(message);

            builder.setPositiveButton("观看", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(VipVoidActivity.this, ParserActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog = builder.create();
            alertDialog.show();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search) {
            String searchUrl = url.getText().toString().trim();
            if (searchUrl.startsWith("http")) {
                webView.loadUrl(searchUrl);
            } else {
                webView.loadUrl("http://" + searchUrl);
            }
        } else if (v.getId() == R.id.close) {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack()) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                webView.goBack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
