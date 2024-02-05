package aida.inteli.webextend;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide the action bar
        getSupportActionBar().hide();

        webView = findViewById(R.id.webView);

        // Enable JavaScript (optional)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set the user agent to emulate a mobile device
        webSettings.setUserAgentString(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36"
        );

        // Set up WebViewClient to handle link clicks within the WebView
        webView.setWebViewClient(new MyWebViewClient());

        // Load a website
        webView.loadUrl("https://fimobile-extend.onrender.com");

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            // Load all links within the WebView itself
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
}
