package edu.aku.hassannaqvi.sewage_sample.utils

import edu.aku.hassannaqvi.sewage_sample.contracts.FormsContract.FormsTable
import edu.aku.hassannaqvi.sewage_sample.models.Users.UsersTable
import edu.aku.hassannaqvi.sewage_sample.models.VersionApp.VersionAppTable


object CreateTable {
    const val DATABASE_NAME = "sewage_sample.db"
    const val DATABASE_COPY = "sewage_sample_copy.db"
    const val PROJECT_NAME = "sewage_sample"
    const val DATABASE_VERSION = 2
    const val SQL_CREATE_FORMS = ("CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_DEVICEID + " TEXT,"
            + FormsTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsTable.COLUMN_SYSDATE + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT,"
            + FormsTable.COLUMN_USERNAME + " TEXT,"
            + FormsTable.COLUMN_SA + " TEXT,"
            + FormsTable.COLUMN_SB + " TEXT,"
            + FormsTable.COLUMN_SC + " TEXT,"
            + FormsTable.COLUMN_GPSLAT + " TEXT,"
            + FormsTable.COLUMN_GPSLNG + " TEXT,"
            + FormsTable.COLUMN_GPSDATE + " TEXT,"
            + FormsTable.COLUMN_GPSACC + " TEXT,"
            + FormsTable.COLUMN_APPVERSION + " TEXT,"
            + FormsTable.COLUMN_ENDINGDATETIME + " TEXT,"
            + FormsTable.COLUMN_ISTATUS + " TEXT,"
            + FormsTable.COLUMN_ISTATUS96x + " TEXT,"
            + FormsTable.COLUMN_SYNCED + " TEXT,"
            + FormsTable.COLUMN_SYNCED_DATE + " TEXT,"
            + FormsTable.COLUMN_FORM_TYPE + " TEXT"
/*            + FormsTable.COLUMN_COUNTRY_CODE + " TEXT,"
            + FormsTable.COLUMN_REG_NO + " TEXT,"
            + FormsTable.COLUMN_DISTRICT_CODE + " TEXT,"
            + FormsTable.COLUMN_DISTRICT + " TEXT,"
            + FormsTable.COLUMN_UC_CODE + " TEXT,"
            + FormsTable.COLUMN_UC + " TEXT,"
            + FormsTable.COLUMN_VILLAGE_CODE + " TEXT,"
            + FormsTable.COLUMN_VILLAGE + " TEXT,"*/
            + " );")

    const val SQL_CREATE_USERS = ("CREATE TABLE " + UsersTable.TABLE_NAME + "("
            + UsersTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_FULLNAME + " TEXT"
            + " );")

/*    const val SQL_CREATE_VILLAGES = ("CREATE TABLE " + VillagesTable.TABLE_NAME + "("
            + VillagesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VillagesTable.COLUMN_REGION + " TEXT,"
            + VillagesTable.COLUMN_DISTRICT + " TEXT,"
            + VillagesTable.COLUMN_UC + " TEXT,"
            + VillagesTable.COLUMN_VILLAGE + " TEXT,"
            + VillagesTable.COLUMN_COUNTRY_CODE + " TEXT,"
            + VillagesTable.COLUMN_COUNTRY + " TEXT,"
            + VillagesTable.COLUMN_DISTRICT_CODE + " TEXT,"
            + VillagesTable.COLUMN_UC_CODE + " TEXT,"
            + VillagesTable.COLUMN_VILLLAGE_CODE + " TEXT,"
            + VillagesTable.COLUMN_REGION_CODE + " TEXT,"
            + VillagesTable.COLUMN_USERNAME + " TEXT" +
            " );")

    const val SQL_ALTER_VILLAGES = "ALTER TABLE " +
            VillagesTable.TABLE_NAME + " ADD COLUMN " +
            VillagesTable.COLUMN_USERNAME + " TEXT"

    const val SQL_ALTER_HEALTHFACILITY = "ALTER TABLE " +
            HealthFacility.HealthFacilityTable.TABLE_NAME + " ADD COLUMN " +
            HealthFacility.HealthFacilityTable.COLUMN_USERNAME + " TEXT"*/

    const val SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppTable.TABLE_NAME + " (" +
            VersionAppTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");"
/*    const val SQL_CREATE_ZSTANDARD = "CREATE TABLE " + ZStandardContract.ZScoreTable.TABLE_NAME + " (" +
            ZStandardContract.ZScoreTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ZStandardContract.ZScoreTable.COLUMN_SEX + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_AGE + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_MEASURE + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_L + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_M + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_S + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_CAT + " TEXT " +
            ");"

    const val SQL_CREATE_HEALTHFACILITY = "CREATE TABLE " + HealthFacility.HealthFacilityTable.TABLE_NAME + " (" +
            HealthFacility.HealthFacilityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            HealthFacility.HealthFacilityTable.COLUMN_COUNTRY_CODE + " TEXT, " +
            HealthFacility.HealthFacilityTable.COLUMN_REGION_CODE + " TEXT, " +
            HealthFacility.HealthFacilityTable.COLUMN_HF_CODE + " TEXT, " +
            HealthFacility.HealthFacilityTable.COLUMN_DIST_CODE + " TEXT, " +
            HealthFacility.HealthFacilityTable.COLUMN_UC_CODE + " TEXT, " +
            HealthFacility.HealthFacilityTable.COLUMN_HEALTH_FACILITY + " TEXT, " +
            HealthFacility.HealthFacilityTable.COLUMN_FACILITY_TYPE + " TEXT, " +
            HealthFacility.HealthFacilityTable.COLUMN_USERNAME + " TEXT " +
            ");"*/


    /* const val SQL_CREATE_CHILD_FOLLOW_UP_LIST = "CREATE TABLE " + ChildTable.TABLE_NAME + " (" +
             ChildTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
             ChildTable.COLUMN_LUID + " TEXT, " +
             ChildTable.COLUMN_CS01 + " TEXT, " +
             ChildTable.COLUMN_CS01A + " TEXT, " +
             ChildTable.COLUMN_CS01B + " TEXT, " +
             ChildTable.COLUMN_CS09 + " TEXT, " +
             ChildTable.COLUMN_CS04 + " TEXT, " +
             ChildTable.COLUMN_CS05 + " TEXT, " +
             ChildTable.COLUMN_CS08 + " TEXT, " +
             ChildTable.COLUMN_CS10 + " TEXT, " +
             ChildTable.COLUMN_CS10A + " TEXT, " +
             ChildTable.COLUMN_CS11 + " TEXT, " +
             ChildTable.COLUMN_CS11A + " TEXT, " +
             ChildTable.COLUMN_CS12 + " TEXT, " +
             ChildTable.COLUMN_CS13 + " TEXT, " +
             ChildTable.COLUMN_FUPDT + " TEXT, " +
             ChildTable.COLUMN_FUPNO + " TEXT, " +
             ChildTable.COLUMN_DOB + " TEXT " +
             ");"

     const val SQL_CREATE_WRA_FOLLOW_UP_LIST = "CREATE TABLE " + WraFollowup.WraTable.TABLE_NAME + " (" +
             WraFollowup.WraTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
             WraFollowup.WraTable.COLUMN_LUID + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS01 + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS01A + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS01B + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS09 + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS04 + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS05 + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS08 + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS10 + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS11 + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS12 + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS12A + " TEXT, " +
             WraFollowup.WraTable.COLUMN_WS13 + " TEXT, " +
             WraFollowup.WraTable.COLUMN_FUPDT + " TEXT, " +
             WraFollowup.WraTable.COLUMN_FUPNO + " TEXT " +
             ");"*/

}