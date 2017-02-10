package com.rock.software.torrent.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rock.software.torrent.App;
import com.rock.software.torrent.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserActivity extends AppCompatActivity {

    String url, type;
    TextView tv;
    private WebView webView;
    String[] vlist, regular;
    int index ;
    Button button,change;
    String playUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser);
        tv = (TextView) findViewById(R.id.result);
        url = getIntent().getStringExtra("url");
        regular = getResources().getStringArray(R.array.regular);
        button = (Button) findViewById(R.id.get);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParserActivity.this, VideoViewPlayingActivity.class);
                intent.putExtra("url", playUrl);
                startActivity(intent);
                finish();

            }
        });

        change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
//                    if (index < 2) {
//                        webView.loadUrl(vlist[index] + url);
//                    }
                if (index < regular.length) {
                    webView.post(new Runnable() {
                        @Override
                        public void run() {
                            if(vlist[index].contains("play1.ufanw.com")){
                                HashMap<String,String> hashMap = new HashMap<String, String>();
                                hashMap.put("Referer","http://vip.ufanw.com/play/play.html?url="+ Base64.encode(url.getBytes(),Base64.DEFAULT));
                                webView.loadUrl(vlist[index] + url,hashMap);
                            }else {
                                webView.loadUrl(vlist[index] + url);
                            }
                        }
                    });
                } else {
                    addText("所有解析失败");
                }
            }
        });

        //    crackUrl1();
//        getUrlContent();
        index=0;
        tv.setText("开始解析==》》》》");
        webView = (WebView) findViewById(R.id.webView);
        initWebView(webView);
        vlist = getResources().getStringArray(R.array.vodList);
        webView.loadUrl(vlist[index] + url);
        addText("加载网页源码");
    }

    public void addText(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder(tv.getText());
                sb.append("\n");
                sb.append(str);
                tv.setText(sb.toString());
            }
        });

    }

    String tmp = "";

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
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");



        //设置WebViewClient
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(!tmp.equals(url)) {
                    tmp = url;
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
            public void onPageFinished(final WebView view, String url) {
                if (!ParserActivity.this.isFinishing()&&!hasRight) {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.loadUrl("javascript:window.local_obj.getPlayUrl('<head>'+"
                                    + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                        }
                    },2000);


                    //video src="(.*?)"
                    addText("加载完成");

                }

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                super.onProgressChanged(view, newProgress);

            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {


                return super.onJsAlert(view, url, message, result);
            }


        });


    }

    String htmlcontent = "";
    boolean hasRight = false;

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            System.out.println("====>html=" + html);
            htmlcontent = html;

            Pattern pattern = Pattern.compile(regular[index]);
            Matcher matcher = pattern.matcher(htmlcontent);

            //      addText("正在匹配播放地址");
            if (matcher.find()) {
                String tmp = matcher.group(1);
                tmp = tmp.replace("&amp;", "&");
                //          addText("播放地址：==》" + tmp);

                Intent intent = new Intent(ParserActivity.this, VideoViewPlayingActivity.class);
                intent.putExtra("url", tmp);
                startActivity(intent);
                finish();

            } else {
                addText("匹配播放地址失败");
                addText("切换下一个解析地址");

//                    if (index < 2) {
//                        webView.loadUrl(vlist[index] + url);
//                    }


            }

        }

        @JavascriptInterface
        public void getPlayUrl(String html) {
            System.out.println("====>html=" + html);
            htmlcontent = html;

            Pattern pattern = Pattern.compile(regular[index]);
            Matcher matcher = pattern.matcher(htmlcontent);

            //      addText("正在匹配播放地址");
            if (matcher.find()) {
                String tmp = matcher.group(1);
                tmp = tmp.replace("&amp;", "&");
                //          addText("播放地址：==》" + tmp);

                if (tmp.length() < 20) {
                    addText("匹配播放地址失败");
                    addText("切换下一个解析地址");
                    index++;
                    if (index < regular.length) {
                        webView.post(new Runnable() {
                            @Override
                            public void run() {
                                if(vlist[index].contains("play1.ufanw.com")){
                                    HashMap<String,String> hashMap = new HashMap<String, String>();
                                    hashMap.put("Referer","http://vip.ufanw.com/play/play.html?url="+ Base64.encode(url.getBytes(),Base64.DEFAULT));
                                    webView.loadUrl(vlist[index] + url,hashMap);
                                }else {
                                    webView.loadUrl(vlist[index] + url);
                                }
                            }
                        });
                    }

                }else{
                    playUrl = tmp;
                    addText("播放地址-->>>"+playUrl);
                    hasRight = true;
                }
            } else {
                addText("匹配播放地址失败");
                addText("切换下一个解析地址");
                index++;
//                    if (index < 2) {
//                        webView.loadUrl(vlist[index] + url);
//                    }
                if (index < regular.length) {
                    webView.post(new Runnable() {
                        @Override
                        public void run() {
                            if(vlist[index].contains("play1.ufanw.com")){
                                HashMap<String,String> hashMap = new HashMap<String, String>();
                                hashMap.put("Referer","http://vip.ufanw.com/play/play.html?url="+ Base64.encode(url.getBytes(),Base64.DEFAULT));
                                webView.loadUrl(vlist[index] + url,hashMap);
                            }else {
                                webView.loadUrl(vlist[index] + url);
                            }
                        }
                    });
                } else {
                    addText("所有解析失败");
                }

            }

        }

    }


    private void crackUrl1(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("aaa", s);
                Pattern pattern = Pattern.compile("<video><file><(.*?)]");
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    String tmp = matcher.group(0);
                    tmp = tmp.substring(tmp.indexOf("http"), tmp.length() - 1);
                    Log.e("aaa", tmp);
                    Intent intent = new Intent(ParserActivity.this, VideoViewPlayingActivity.class);
                    intent.putExtra("url", tmp);
                    startActivity(intent);
                } else {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("aaa", volleyError.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError, JSONException {
                Map<String, String> map = new HashMap<>();
                map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.0.1121.13 Safari/537.36");
                return map;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 3, 1));
        App.getInstance().getRequestQueue().add(stringRequest);

    }


    public void getUrlContent() {

        StringRequest baseRequest = new StringRequest(Request.Method.GET, "http://jx.71ki.com/index.php?url=" + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //{"id":(.*?)"}

                Pattern pattern = Pattern.compile("\\{\"id\":(.*?)\"\\}");
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    String tmp = matcher.group(0);
                    try {
                        JSONObject json = new JSONObject(tmp);
                        getParser1(json);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError, JSONException {
                Map<String, String> map = new HashMap<>();
                map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.0.1121.13 Safari/537.36");
                return map;
            }

        };
        baseRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 3, 1));
        App.getInstance().getRequestQueue().add(baseRequest);

    }


    public void getParser1(final JSONObject jsonObject) {

        StringRequest baseRequest = new StringRequest(Request.Method.POST, "http://jx.71ki.com/url1.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //{"id":(.*?)"}
                try {
                    JSONObject jsonObject1 = new JSONObject(s);
                    String ext = jsonObject1.optString("ext");
                    String url = jsonObject1.optString("url");
                    if (ext.equals("xml")) {
                        crackUrl1(url);
                    } else {
                        Intent intent = new Intent(ParserActivity.this, VideoViewPlayingActivity.class);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError, JSONException {
                Map<String, String> map = new HashMap<>();
                map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 UBrowser/6.0.1121.13 Safari/537.36");
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", jsonObject.optString("id"));
                hashMap.put("type", jsonObject.optString("type"));
                hashMap.put("siteuser", jsonObject.optString("siteuser"));
                hashMap.put("md5", jsonObject.optString("md5"));
                return hashMap;
            }
        };
        baseRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 3, 1));
        App.getInstance().getRequestQueue().add(baseRequest);

    }


    private void crackUrl2() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://api.aikantv.cc/?url=" + url + "&hd=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("aaa", s);
                Pattern pattern = Pattern.compile("\"url\":(.*?),");
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    String tmp = matcher.group(1);
                    String tmmurl = "http://api.aikantv.cc/api.php?url=" + tmp.substring(1, tmp.length() - 2);
                    getResult(tmmurl);

//                    tmp = tmp.substring(tmp.indexOf("http"),tmp.length()-1);
//                    Log.e("aaa",tmp);
//                    Intent intent = new Intent(ParserActivity.this,VideoViewPlayingActivity.class);
//                    intent.putExtra("url",tmp);
//                    startActivity(intent);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("aaa", volleyError.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError, JSONException {
                Map<String, String> map = new HashMap<>();
                map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
                map.put("X-Requested-With", "XMLHttpRequest");
                return map;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 3, 1));
        App.getInstance().getRequestQueue().add(stringRequest);

    }


    public void getResult(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("aaa", s);
                Pattern pattern = Pattern.compile("<video><file><(.*?)]]");
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    String tmp = matcher.group(0);

                    final String tmp1 = tmp.substring(tmp.indexOf("CDATA") + 6, tmp.length() - 1);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            redirectPath(tmp1, "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
                        }
                    }).start();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("aaa", volleyError.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError, JSONException {
                Map<String, String> map = new HashMap<>();
                map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
                map.put("X-Requested-With", "XMLHttpRequest");
                return map;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000, 3, 1));
        App.getInstance().getRequestQueue().add(stringRequest);


    }


    public String redirectPath(final String str, String userAgent) {
        URL url = null;
        String realURL = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(str);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("User-Agent", userAgent);
            conn.setInstanceFollowRedirects(true);
            conn.getResponseCode();// trigger server redirect
            realURL = conn.getURL().toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        Log.e("aaa", realURL);
        Intent intent = new Intent(ParserActivity.this, VideoViewPlayingActivity.class);
        intent.putExtra("url", realURL);
        startActivity(intent);
        return realURL;
    }


}
