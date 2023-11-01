package com.example.invex

import Models.mEntries
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.util.ArrayList

class entradalink(private var aURL: String) : DialogFragment() {
    private var webViewUrl: WebView? = null
    private var CerrarWebView: AppCompatImageView? = null
    private var progressWebView: ConstraintLayout? = null
    private var ProgressBarWebView: CircularProgressIndicator? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.activity_entradalink, container, false)
        webViewUrl = rootView.findViewById<WebView>(R.id.webViewUrl)
        CerrarWebView = rootView.findViewById<AppCompatImageView>(R.id.CerrarWebView)
        progressWebView = rootView.findViewById<ConstraintLayout>(R.id.progressWebView)
        ProgressBarWebView = rootView.findViewById<CircularProgressIndicator>(R.id.ProgressBarWebView)

        webViewUrl?.getSettings()!!.setLoadsImagesAutomatically(true)
        webViewUrl?.getSettings()!!.setJavaScriptEnabled(true)
        webViewUrl?.settings!!.setJavaScriptEnabled(true)
        webViewUrl?.settings!!.javaScriptEnabled = true
        webViewUrl?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressWebView?.visibility = View.VISIBLE
                ProgressBarWebView?.incrementProgressBy(newProgress)
                if (newProgress == 100)
                    progressWebView?.visibility = View.GONE

            }
        }

        webViewUrl?.loadUrl(aURL)

        CerrarWebView?.setOnClickListener{
            dialog?.dismiss()
        }

        return rootView
    }
}


//    override fun onCreateView(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_entradalink)
//
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//
//    }
//}