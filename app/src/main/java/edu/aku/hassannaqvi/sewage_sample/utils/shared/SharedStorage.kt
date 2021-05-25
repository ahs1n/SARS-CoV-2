package edu.aku.hassannaqvi.sewage_sample.utils.shared

import android.content.Context
import edu.aku.hassannaqvi.sewage_sample.CONSTANTS.Companion.BACKUP_DT
import edu.aku.hassannaqvi.sewage_sample.CONSTANTS.Companion.COUNTRY_CODE
import edu.aku.hassannaqvi.sewage_sample.CONSTANTS.Companion.DOWNLOAD_FILE_REFID
import edu.aku.hassannaqvi.sewage_sample.CONSTANTS.Companion.FIRST_TIME_INSTALL
import edu.aku.hassannaqvi.sewage_sample.CONSTANTS.Companion.TAG_NAME
import org.apache.commons.lang3.StringUtils

/*
* @author Ali.Azaz
* */
object SharedStorage : SharedStorageBase() {

    fun setLastRegistrationID(context: Context, villageCode: String, currentID: String) {
        put(context, villageCode, currentID)
    }

    fun getLastRegistrationID(context: Context, villageCode: String): String {
        return get(context, villageCode, StringUtils.EMPTY) as String
    }

    fun setCountryCode(context: Context, country: Int) {
        put(context, COUNTRY_CODE, country)
    }

    fun getCountryCode(context: Context): Int {
        return get(context, COUNTRY_CODE, 0) as Int
    }

    fun setFirstInstallFlag(context: Context, flag: Boolean) {
        put(context, FIRST_TIME_INSTALL, flag)
    }

    fun getFirstInstallFlag(context: Context): Boolean {
        return get(context, FIRST_TIME_INSTALL, true) as Boolean
    }

    fun setTagName(context: Context, tag: String) {
        put(context, TAG_NAME, tag)
    }

    fun getTagName(context: Context): String {
        return get(context, TAG_NAME, StringUtils.EMPTY) as String
    }

    fun setBackUpDTFolder(context: Context, tag: String) {
        put(context, BACKUP_DT, tag)
    }

    fun getBackUpDTFolder(context: Context): String {
        return get(context, BACKUP_DT, StringUtils.EMPTY) as String
    }

    fun setDownloadFileRefID(context: Context, refID: Long) {
        put(context, DOWNLOAD_FILE_REFID, refID)
    }

    fun getDownloadFileRefID(context: Context): Long {
        return get(context, DOWNLOAD_FILE_REFID, 0) as Long
    }
}