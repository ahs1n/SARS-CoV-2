package edu.aku.hassannaqvi.sewage_sample.base.repository

import edu.aku.hassannaqvi.sewage_sample.database.DatabaseHelper
import edu.aku.hassannaqvi.sewage_sample.models.Form
import edu.aku.hassannaqvi.sewage_sample.models.FormIndicatorsModel
import edu.aku.hassannaqvi.sewage_sample.models.Identification
import edu.aku.hassannaqvi.sewage_sample.models.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class GeneralRepository(private val db: DatabaseHelper) : GeneralDataSource {

/*    override suspend fun getSelectedServerChildList(country: String, identification: Identification): ArrayList<ChildFollowup> = withContext(Dispatchers.IO) {
        db.getChildrenFollowUpFromDB(country, identification)
    }

    override suspend fun getSelectedChildLocalFormList(country: String, identification: Identification): ArrayList<ChildFollowup> = withContext(Dispatchers.IO) {
        db.getChildrenFollowupFromFormDB(country, identification)
    }*/

/*    override suspend fun getSelectedServerWraList(country: String, identification: Identification): ArrayList<WraFollowup> = withContext(Dispatchers.IO) {
        db.getWraFollowUpFromDB(country, identification)
    }

    override suspend fun getSelectedWraLocalFormList(country: String, identification: Identification): ArrayList<WraFollowup> = withContext(Dispatchers.IO) {
        db.getWraFollowupFromFormDB(country, identification)
    }*/

    override suspend fun getLocalDBFollowupFormList(country: String, identification: Identification, reg_no: String, followupType: String): Form? = withContext(Dispatchers.IO) {
        db.getFollowUpFormStatus(country, identification, reg_no, followupType)
    }

    override suspend fun getLoginInformation(username: String, password: String): Users? = withContext(Dispatchers.IO) {
        db.getLoginUser(username, password)
    }

    override suspend fun getFormsByDate(date: String): ArrayList<Form> = withContext(Dispatchers.IO) {
        db.getFormsByDate(date)
    }

    override suspend fun getUploadStatus(): FormIndicatorsModel = withContext(Dispatchers.IO) {
        db.uploadStatusCount
    }

    override suspend fun getFormStatus(date: String): FormIndicatorsModel = withContext(Dispatchers.IO) {
        db.getFormStatusCount(date)
    }

/*    override suspend fun insertZScore(date: JSONArray): Int = withContext(Dispatchers.IO) {
        db.syncZStandard(date)
    }*/
}