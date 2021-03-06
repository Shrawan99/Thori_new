package np.gov.thorimun.www.thorimun4;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class New3Activity extends AppCompatActivity implements TabHost.OnTabChangeListener {
    WebView webView;
    TabHost tabhost;
    TabHost.TabSpec spec2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new3);
        webView = (WebView) findViewById(R.id.webView);
        tabhost=(TabHost)findViewById(R.id.tabhost);
        tabhost.setup();
        tabhost.setOnTabChangedListener(this);
       checkConnection();

        //Bajet Karyakram
        TabHost.TabSpec spec1 = tabhost.newTabSpec("home");
        spec1.setContent(R.id.home);
        spec1.setIndicator("बजेट तथा कार्यक्रम", null);
        tabhost.addTab(spec1);
        //Intent intent=new Intent(this, Bajet_Activity.class);
        //spec1.setContent(intent);

        //Yoajan Karyakarm
        spec2 = tabhost.newTabSpec("extras");
        spec2.setContent(R.id.extras);
        spec2.setIndicator("योजना तथा परियोजना", null);
        tabhost.addTab(spec2);
       //Intent intent2=new Intent(this, plans_Activity.class);
       //spec2.setContent(intent2);


    }

    @Override
    public void onTabChanged(String tabId) {
        /*if("home".equals(tabId)) {
            Intent intent2=new Intent(New3Activity.this, Bajet_Activity.class);
            startActivity(intent2);
        }*/
        if("extras".equals(tabId)) {
            Intent intent2=new Intent(New3Activity.this, plans_Activity.class);
            startActivity(intent2);
        }

    }

       protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {

            return false;
        }
    }
    public void checkConnection() {

        if (isOnline()) {

            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("http://thorimun.gov.np/budget-program_app");

        } else {
            Toast.makeText(this, "No Internet Connection, Directing to Cached Mode", Toast.LENGTH_SHORT).show();
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("http://thorimun.gov.np/budget-program_app");
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }


    }

}