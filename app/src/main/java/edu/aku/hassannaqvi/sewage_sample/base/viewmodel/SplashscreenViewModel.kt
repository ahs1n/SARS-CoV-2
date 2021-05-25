package edu.aku.hassannaqvi.sewage_sample.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.aku.hassannaqvi.sewage_sample.base.repository.GeneralRepository
import edu.aku.hassannaqvi.sewage_sample.base.repository.ResponseStatusCallbacks

class SplashscreenViewModel(var repository: GeneralRepository) : ViewModel() {

    private val _zscoreRecord: MutableLiveData<ResponseStatusCallbacks<Int>> = MutableLiveData()
    val zscoreRecord: MutableLiveData<ResponseStatusCallbacks<Int>>
        get() = _zscoreRecord

    /* fun insertZScoreRecord(inputStreamReader: InputStreamReader) {
         _zscoreRecord.value = ResponseStatusCallbacks.loading(null)
         viewModelScope.launch {
             delay(1000)
             val result = StringBuilder()
             try {
                 val bufferedReader = BufferedReader(inputStreamReader)
                 var eachline = bufferedReader.readLine()
                 while (eachline != null) {
                     result.append(eachline)
                     eachline = bufferedReader.readLine()
                 }
                 val json = JSONArray(result.toString())
                 val count = repository.insertZScore(json)
                 _zscoreRecord.value =
                         if (count > 0)
                             ResponseStatusCallbacks.success(count)
                         else
                             ResponseStatusCallbacks.error(count, "No ZScore found. Please restart the app")
             } catch (e: IOException) {
                 e.printStackTrace()
             }

         }

     }*/

}