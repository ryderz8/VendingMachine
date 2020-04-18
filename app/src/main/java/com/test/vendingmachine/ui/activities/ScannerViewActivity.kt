package com.test.vendingmachine.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.media.MediaPlayer
import com.test.vendingmachine.utilities.Constants


class ScannerViewActivity : Activity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)

        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()         // Start camera
    }

    override fun onPause() {
        super.onPause()
        this.cleanResult()
    }


    override fun onBackPressed() { this.cleanResult() }

    private fun cleanResult() {
        try {
            mScannerView!!.stopCamera() // Stop camera on pause
        } catch (e: Exception) {
            Log.e(Constants.ERROR, e.message)
        }

        val resultIntent = Intent()
        resultIntent.putExtra(Constants.BAR_CODE, "")
        setResult(2, resultIntent)
        finish()
    }

    override fun handleResult(rawResult: Result) {
        // Do something with the result here

        Log.e(Constants.HANDLER, rawResult.text) // Prints scan results
        Log.e(Constants.HANDLER, rawResult.barcodeFormat.toString()) // Prints the scan format (qrcode)

        try {
            mScannerView!!.stopCamera()

            val resultIntent = Intent()

            resultIntent.putExtra(Constants.BAR_CODE, rawResult.text)
            setResult(2, resultIntent)
            finish()

            println(Constants.STOP_CAMERA)
            // Stop camera on pause
        } catch (e: Exception) {
            Log.e(Constants.ERROR, e.message)
        }

    }
}