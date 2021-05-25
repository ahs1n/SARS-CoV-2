package edu.aku.hassannaqvi.sewage_sample.ui.other

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.aku.hassannaqvi.sewage_sample.R
import edu.aku.hassannaqvi.sewage_sample.ui.login_activity.LoginActivity
import edu.aku.hassannaqvi.sewage_sample.utils.gotoActivity
import kotlinx.coroutines.*


/*
* @author Ali Azaz Alam dt. 12.16.20
* */
class SplashscreenActivity : AppCompatActivity() {
    private lateinit var activityScope: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        activityScope = launchSplashScope()
    }

    override fun onPause() {
        super.onPause()
        activityScope.cancel()
    }

    override fun onResume() {
        super.onResume()
        if (activityScope.isActive.not())
            launchSplashScope()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }

    private fun launchSplashScope() =
            CoroutineScope(Dispatchers.Main).launch {
                delay(SPLASH_TIME_OUT.toLong())
                finish()
                gotoActivity(LoginActivity::class.java)
            }

    companion object {
        private const val SPLASH_TIME_OUT = 1000
    }
}

/* *//*
        * Show FullScreen
        * *//*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) window.insetsController?.hide(WindowInsets.Type.statusBars())
        else window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        *//*
        * Observe zscore count
        * *//*
*//*        viewModel.zscoreRecord.observe(this, {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    catLoad.dismiss()
                    finish()
                    gotoActivity(LoginActivity::class.java)
                }
                ResponseStatus.ERROR -> {
                    Snackbar.make(findViewById(android.R.id.content), it.data.toString(), Snackbar.LENGTH_LONG).show()
                    catLoad.dismiss()
                }
                ResponseStatus.LOADING -> {
                    catLoad.setText("PROCESSING..")
                    catLoad.setClickCancelAble(false)
                    catLoad.setBackgroundColor(R.color.colorPrimaryDark)
                    catLoad.show(supportFragmentManager, "")

                    *//**//*progressSplashscreen.visibility = View.VISIBLE
                    progressTxt.visibility = View.VISIBLE
                    Snackbar.make(findViewById(android.R.id.content), "Configuring ZScore. Don't close app", Snackbar.LENGTH_LONG).show()*//**//*
                }
            }
        })*//*
    }

*//*    private fun launchSplashScope() =
            CoroutineScope(Dispatchers.Main).launch {
                delay(SPLASH_TIME_OUT.toLong())
                if (SharedStorage.getFirstInstallFlag(this@SplashscreenActivity)) {
                    SharedStorage.setFirstInstallFlag(this@SplashscreenActivity, false)
                    var streamReader: InputStreamReader? = null
                    val job = async(Dispatchers.IO) {
                        streamReader = InputStreamReader(assets.open("zStandardJson.json"))
                    }
                    job.await()
                    streamReader?.let { viewModel.insertZScoreRecord(it) }
                } else {
                    finish()
                    gotoActivity(LoginActivity::class.java)
                }
            }

    companion object {
        private const val SPLASH_TIME_OUT = 1000
    }*//*

    override fun onBackPressed() {
    }*/
