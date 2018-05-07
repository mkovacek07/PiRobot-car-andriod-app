package com.example.mateo.pirobotcar;


import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private WebView Joystick;
    private Button btnOsvjezi;
    private FrameLayout customViewContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private View mCustomView;
    private myWebChromeClient mWebChromeClient;
    private myWebViewClient mWebViewClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customViewContainer = (FrameLayout) findViewById(R.id.customViewContainer);
        webView = (WebView) findViewById(R.id.webView);
        Joystick =(WebView) findViewById(R.id.Jostick);


        mWebViewClient = new myWebViewClient(); //Postavlja se WebViewClient koji će primati različite obavijesti i zahtjeve.
        webView.setWebViewClient(mWebViewClient);
        Joystick.setWebViewClient(mWebViewClient);

        btnOsvjezi=(Button) findViewById(R.id.btnVideoRefresh);


        btnOsvjezi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                osvjeziVideo();
            }
        });

        mWebChromeClient = new myWebChromeClient(); //Poziva se kada se dogodi nešto što bi moglo utjecati na UI preglednika
        webView.setWebChromeClient(mWebChromeClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl("http://192.168.43.183:5000/");

        Joystick.setWebChromeClient(mWebChromeClient);
        Joystick.getSettings().setJavaScriptEnabled(true);
        Joystick.getSettings().setAppCacheEnabled(true);
        Joystick.getSettings().setBuiltInZoomControls(true);
        Joystick.loadUrl("http://192.168.43.183:3000/");

    }

    private void osvjeziVideo() {
        webView.reload();
        Joystick.reload();
    }

    public boolean inCustomView() {
        return (mCustomView != null);
    }

    public void hideCustomView() {mWebChromeClient.onHideCustomView();} //Obavijestiti aplikaciju hosta da je trenutna stranica izašla iz cijelog zaslona


    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
        Joystick.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        Joystick.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (inCustomView()) {
            hideCustomView();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //Zamjenjuje defaultnu Back tipku
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (inCustomView()) {
                hideCustomView();
                return true;
            }

            if ((mCustomView == null) && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    class myWebChromeClient extends WebChromeClient {
        private View mVideoProgressView;

        @Override
        public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
            onShowCustomView(view, callback);    //Koristimo za full screen
        }

        @Override
        public void onShowCustomView(View view,CustomViewCallback callback) {

            if (mCustomView != null) {
                callback.onCustomViewHidden(); //Poziva se kada aplikacija nije u mogućnsoti izvesti prilagođeni prikaz
                return;
            }
            mCustomView = view;
            webView.setVisibility(View.GONE);
            Joystick.setVisibility(View.GONE);
            customViewContainer.setVisibility(View.VISIBLE);
            customViewContainer.addView(view);
            customViewCallback = callback;
        }

        @Override
        public View getVideoLoadingProgressView() {

            if (mVideoProgressView == null) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                mVideoProgressView = inflater.inflate(R.layout.video_progress, null);
            }
            return mVideoProgressView;
        }

        @Override
        public void onHideCustomView() { // Obaviještava se korinsik da je aplikacija izašla iz full screen moda
            super.onHideCustomView();
            if (mCustomView == null)
                return;

            webView.setVisibility(View.VISIBLE);
            Joystick.setVisibility(View.VISIBLE);
            customViewContainer.setVisibility(View.GONE);

            // Sakriji custom view
            mCustomView.setVisibility(View.GONE);

            // Izbriši custom
            customViewContainer.removeView(mCustomView);
            customViewCallback.onCustomViewHidden();

            mCustomView = null;
        }
    }

    class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { //Aplikacija želi napustiti renutni WebView i upravljati samim URL-om
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
