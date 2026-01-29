package com.survivalcoding.legacy.p04_webview.step01_regacy

import android.content.Context
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.running.domain.domain.User
import com.survivalcoding.running.domain.repository.UserRepository

class WebViewLegacyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_legacy)

        val webView = findViewById<WebView>(R.id.my_web_view)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
//        webView.webChromeClient = WebChromeClient()
        webView.addJavascriptInterface(WebAppInterface(this), "Android")

//        webView.loadUrl("https://naver.com")
        webView.loadUrl("file:///android_asset/index.html")
//        webView.loadUrl("file:///android_asset/address.html")
    }
}

class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page.  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}

class UserRepositoryImpl : UserRepository {
    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

}