package aida.inteli.webextend;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide the action bar
        getSupportActionBar().hide();

        // Show splash screen initially
        findViewById(R.id.splashScreen).setVisibility(View.VISIBLE);

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


        // Before loading the website, check network connection
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Load the website
            webView.loadUrl("https://fimobile-extend.onrender.com");

            // Hide splash screen after a delay (e.g., 5 seconds)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    findViewById(R.id.splashScreen).setVisibility(View.GONE);
                }
            }, 5000);
        } else {
            // Toast long message
             Toast.makeText(this, "No internet connection, Please check your network  and try again", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        // Check if the WebView can go back in history
        if (webView.canGoBack()) {
            webView.goBack();  // Go back in WebView history
        } else {
            super.onBackPressed();  // If no history, proceed with the default back button behavior (close the app)
        }
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
