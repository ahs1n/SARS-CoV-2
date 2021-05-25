package edu.aku.hassannaqvi.sewage_sample.utils

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.aku.hassannaqvi.sewage_sample.base.factory.ViewModelFactory
import edu.aku.hassannaqvi.sewage_sample.base.repository.GeneralRepository
import java.io.Serializable

/*
* @author Mustufa.Ansari
* @modified Ali Azaz Alam dt. 12.18.20
* */
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>, generalRepository: GeneralRepository) =
        ViewModelProvider(this, ViewModelFactory(generalRepository)).get(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(activity: AppCompatActivity, viewModelClass: Class<T>, generalRepository: GeneralRepository) =
        ViewModelProvider(activity, ViewModelFactory(generalRepository)).get(viewModelClass)

fun <T : AppCompatActivity> AppCompatActivity.gotoActivity(targetActivityClass: Class<T>) {
    val intent = Intent(this, targetActivityClass)
    startActivity(intent)
}

fun <T : Activity> Activity.gotoActivity(targetActivityClass: Class<T>) {
    val intent = Intent(this, targetActivityClass)
    startActivity(intent)
}

fun <T : AppCompatActivity> AppCompatActivity.gotoActivityWithSerializable(targetActivityClass: Class<T>, key: String, data: Serializable) {
    val intent = Intent(this, targetActivityClass).putExtra(key, data)
    startActivity(intent)
}

fun <T : AppCompatActivity> Fragment.gotoActivityWithSerializable(targetActivityClass: Class<T>, key: String, data: Serializable) {
    val intent = Intent(view?.context, targetActivityClass).putExtra(key, data)
    startActivity(intent)
}

fun <T : AppCompatActivity> AppCompatActivity.gotoActivityWithNoHistory(targetActivityClass: Class<T>) {
    val i = Intent(this, targetActivityClass)
    i.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(i)
}