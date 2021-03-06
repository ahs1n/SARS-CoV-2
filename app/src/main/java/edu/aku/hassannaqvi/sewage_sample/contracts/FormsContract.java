package edu.aku.hassannaqvi.sewage_sample.contracts;

import android.provider.BaseColumns;

/**
 * @author hussain.siddiqui.
 */

public class FormsContract {

    public static abstract class FormsTable implements BaseColumns {
        public static final String TABLE_NAME = "forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "project_name";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_F1ASPECID = "f1aSpecID";
        public static final String COLUMN_F1ASITE = "f1asite";
        public static final String COLUMN_F1BSPECID = "f1bSpecID";
        public static final String COLUMN_F1BSITE = "f1bsite";
        public static final String COLUMN_F1CSPECID = "f1cSpecID";
        public static final String COLUMN_F1CSITE = "f1csite";

        public static final String COLUMN_SPECIMEN_ID = "specimen";
        public static final String COLUMN_SITE_ID = "site_id";

        public static final String COLUMN_SA = "sA";
        public static final String COLUMN_SB = "sB";
        public static final String COLUMN_SC = "sC";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS96x = "istatus96x";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDATE = "gpsdate";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_FORM_TYPE = "formType";
    }
}
