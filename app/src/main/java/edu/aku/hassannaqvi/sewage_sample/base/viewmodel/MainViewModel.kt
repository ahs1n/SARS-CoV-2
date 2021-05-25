package edu.aku.hassannaqvi.sewage_sample.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.aku.hassannaqvi.sewage_sample.base.repository.GeneralRepository
import edu.aku.hassannaqvi.sewage_sample.base.repository.ResponseStatusCallbacks
import edu.aku.hassannaqvi.sewage_sample.models.FormIndicatorsModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(val repository: GeneralRepository) : ViewModel() {

    var sysdateToday = SimpleDateFormat("dd-MM-yy", Locale.ENGLISH).format(Date())

    /*
    * Today's form
    * */
    private var _tf: MutableLiveData<ResponseStatusCallbacks<Int>> = MutableLiveData()
    val todayForms: MutableLiveData<ResponseStatusCallbacks<Int>>
        get() = _tf

    /*
    * Unsynced and Synced Forms as upload forms
    * */
    private var _uf: MutableLiveData<ResponseStatusCallbacks<FormIndicatorsModel>> = MutableLiveData()
    val uploadForms: MutableLiveData<ResponseStatusCallbacks<FormIndicatorsModel>>
        get() = _uf

    /*
    * Complete and Incomplete forms as status forms
    * */
    private var _fs: MutableLiveData<ResponseStatusCallbacks<FormIndicatorsModel>> = MutableLiveData()
    val formsStatus: MutableLiveData<ResponseStatusCallbacks<FormIndicatorsModel>>
        get() = _fs


    /*
    * Get Villages from DB
    * */
/*    private val _villagesResponse: MutableLiveData<ResponseStatusCallbacks<List<Villages>>> = MutableLiveData()
    val districtResponse: MutableLiveData<ResponseStatusCallbacks<List<Villages>>>
        get() = _villagesResponse*/


    fun getTodayForms(date: String) {
        _tf.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                val todayData = repository.getFormsByDate(date)
                _tf.value = ResponseStatusCallbacks.success(data = todayData.size, message = "Forms exist")
            } catch (e: Exception) {
                _tf.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }


    fun getUploadFormsStatus() {
        _uf.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                val todayData = repository.getUploadStatus()
                _uf.value = ResponseStatusCallbacks.success(data = todayData, message = "Upload status exist")
            } catch (e: Exception) {
                _uf.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }


    fun getFormsStatus(date: String) {
        _fs.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                val todayData = repository.getFormStatus(date)
                _fs.value = ResponseStatusCallbacks.success(data = todayData, message = "Form status exist")
            } catch (e: Exception) {
                _fs.value = ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }


    fun getFormsStatusUploadStatus(date: String) {
        _uf.value = ResponseStatusCallbacks.loading(null)
        _fs.value = ResponseStatusCallbacks.loading(null)
        _tf.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            launch {
                try {
                    val todayData = repository.getFormsByDate(date)
                    _tf.value = ResponseStatusCallbacks.success(data = todayData.size, message = "Forms exist")
                } catch (e: Exception) {
                    _tf.value = ResponseStatusCallbacks.error(null, e.message.toString())
                }
            }
            launch {
                try {
                    val todayData = repository.getUploadStatus()
                    _uf.value = ResponseStatusCallbacks.success(data = todayData, message = "Upload status exist")
                } catch (e: Exception) {
                    _fs.value = ResponseStatusCallbacks.error(null, e.message.toString())
                }
            }
            launch {
                try {
                    val fstatus = repository.getFormStatus(date)
                    _fs.value = ResponseStatusCallbacks.success(data = fstatus, message = "Form status exist")
                } catch (e: Exception) {
                    _fs.value = ResponseStatusCallbacks.error(null, e.message.toString())
                }
            }
        }
    }


/*    fun getDistrictFromDB() {
        _villagesResponse.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                delay(1000)
                val district = repository.getVillagesFromDB()
                _villagesResponse.value = if (district.size > 0) {
                    ResponseStatusCallbacks.success(data = district, message = "District item found")
                } else
                    ResponseStatusCallbacks.error(data = null, message = "No district found!")
            } catch (e: Exception) {
                _villagesResponse.value =
                        ResponseStatusCallbacks.error(data = null, message = e.message.toString())
            }

        }

    }*/

}