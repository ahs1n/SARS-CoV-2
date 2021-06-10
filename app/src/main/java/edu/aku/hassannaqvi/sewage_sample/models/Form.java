package edu.aku.hassannaqvi.sewage_sample.models;

import android.database.Cursor;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.LocalDate;

import edu.aku.hassannaqvi.sewage_sample.CONSTANTS;
import edu.aku.hassannaqvi.sewage_sample.contracts.FormsContract.FormsTable;

/**
 * @author hussain.siddiqui.
 */

public class Form extends LiveData<Form> {

    private final String projectName = "sewage_sample";

    /*private static final Form instance = new Form();

    private Form() {
        Log.d(projectName, "Forms() returned: " + _ID);
    }

    public static Form getInstance() {
        return instance;
    }*/

    //    Section B
    private String f1bspecid;
    private String f1bsite;
    public String f1b01;
    public String f1b02;
    public String f1b02a;
    public String f1b03;
    public String f1b04;
    public String f1b05;
    public String f1b06;
    public String f1b06a;
    public String f1b07;

    //    Section C
    private String f1cspecid;
    private String f1csite;
    public String f1c01;
    public String f1c02;
    public String f1c03;
    public String f1c04;
    public String f1c05;
    public String f1c06;
    public String f1c07;
    public String f1c08;


    //    Section A
    private String f1aspecid;
    private String f1asite;
    private String f1a01;
    private String f1a02;
    private String f1a03;
    private String f1a04;
    private String f1a05;
    private String f1a06;
    private String f1a07;
    private String f1a08;
    private String f1a09;
    private String f1a10;
    private String f1a11;

    private String specimenID;
    private String siteID;

    public String getSpecimenID() {
        return specimenID;
    }

    public void setSpecimenID(String specimenID) {
        this.specimenID = specimenID;
    }

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }


    private String istatus = ""; // Interview Status
    private String istatus96x = ""; // Interview Status
    private String endingdatetime = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String gpslat = "";
    private String gpslng = "";
    private String gpsdate = "";
    private String gpsacc = "";
    private String deviceid = "";
    private String tagid = "";
    private String sA = "";
    private String sB = "";
    private String sC = "";
    private String _luid = "";
    private String fupno = "";
    private String scr_date = "";


    private String _ID = "";
    private String _UID = "";
    private String username;
    private String sysdate = "";
    private String reg_no = "";
    private String countryCode = "";
    private String district = "";
    private String districtCode = "";
    private String uc = "";
    private String ucCode = "";
    private String village = "";
    private String villageCode = "";
    private String formType = "";

    //Only for population in DB
    private String name;
    //Date Settings
    private LocalDate localDate = null, calculatedDOB = null;
    //For section selection
    private SectionSelection secSelection;


    public Form() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getCalculatedDOB() {
        return calculatedDOB;
    }

    public void setCalculatedDOB(LocalDate calculatedDOB) {
        this.calculatedDOB = calculatedDOB;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }


    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }


    public String getUcCode() {
        return ucCode;
    }

    public void setUcCode(String ucCode) {
        this.ucCode = ucCode;
    }


    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }


    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }


    //    Section A
    public String getF1aspecid() {
        return f1aspecid;
    }

    public void setF1aspecid(String f1aspecid) {
        this.f1aspecid = f1aspecid;
    }

    public String getF1asite() {
        return f1asite;
    }

    public void setF1asite(String f1asite) {
        this.f1asite = f1asite;
    }

    public String getF1a01() {
        return f1a01;
    }

    public void setF1a01(String f1a01) {
        this.f1a01 = f1a01;
    }

    public String getF1a02() {
        return f1a02;
    }

    public void setF1a02(String f1a02) {
        this.f1a02 = f1a02;
    }

    public String getF1a03() {
        return f1a03;
    }

    public void setF1a03(String f1a03) {
        this.f1a03 = f1a03;
    }

    public String getF1a04() {
        return f1a04;
    }

    public void setF1a04(String f1a04) {
        this.f1a04 = f1a04;
    }

    public String getF1a05() {
        return f1a05;
    }

    public void setF1a05(String f1a05) {
        this.f1a05 = f1a05;
    }

    public String getF1a06() {
        return f1a06;
    }

    public void setF1a06(String f1a06) {
        this.f1a06 = f1a06;
    }

    public String getF1a07() {
        return f1a07;
    }

    public void setF1a07(String f1a07) {
        this.f1a07 = f1a07;
    }

    public String getF1a08() {
        return f1a08;
    }

    public void setF1a08(String f1a08) {
        this.f1a08 = f1a08;
    }

    public String getF1a09() {
        return f1a09;
    }

    public void setF1a09(String f1a09) {
        this.f1a09 = f1a09;
    }

    public String getF1a10() {
        return f1a10;
    }

    public void setF1a10(String f1a10) {
        this.f1a10 = f1a10;
    }

    public String getF1a11() {
        return f1a11;
    }

    public void setF1a11(String f1a11) {
        this.f1a11 = f1a11;
    }


    //    Section B
    public String getF1bspecid() {
        return f1bspecid;
    }

    public void setF1bspecid(String f1bspecid) {
        this.f1bspecid = f1bspecid;
    }

    public String getF1bsite() {
        return f1bsite;
    }

    public void setF1bsite(String f1bsite) {
        this.f1bsite = f1bsite;
    }

    public String getF1b01() {
        return f1b01;
    }

    public void setF1b01(String f1b01) {
        this.f1b01 = f1b01;
    }

    public String getF1b02() {
        return f1b02;
    }

    public void setF1b02(String f1b02) {
        this.f1b02 = f1b02;
    }

    public String getF1b02a() {
        return f1b02a;
    }

    public void setF1b02a(String f1b02a) {
        this.f1b02a = f1b02a;
    }

    public String getF1b03() {
        return f1b03;
    }

    public void setF1b03(String f1b03) {
        this.f1b03 = f1b03;
    }

    public String getF1b04() {
        return f1b04;
    }

    public void setF1b04(String f1b04) {
        this.f1b04 = f1b04;
    }

    public String getF1b05() {
        return f1b05;
    }

    public void setF1b05(String f1b05) {
        this.f1b05 = f1b05;
    }

    public String getF1b06() {
        return f1b06;
    }

    public void setF1b06(String f1b06) {
        this.f1b06 = f1b06;
    }

    public String getF1b06a() {
        return f1b06a;
    }

    public void setF1b06a(String f1b06a) {
        this.f1b06a = f1b06a;
    }

    public String getF1b07() {
        return f1b07;
    }

    public void setF1b07(String f1b07) {
        this.f1b07 = f1b07;
    }


//    Section C

    public String getF1cspecid() {
        return f1cspecid;
    }

    public void setF1cspecid(String f1cspecid) {
        this.f1cspecid = f1cspecid;
    }

    public String getF1csite() {
        return f1csite;
    }

    public void setF1csite(String f1csite) {
        this.f1csite = f1csite;
    }

    public String getF1c01() {
        return f1c01;
    }

    public void setF1c01(String f1c01) {
        this.f1c01 = f1c01;
    }

    public String getF1c02() {
        return f1c02;
    }

    public void setF1c02(String f1c02) {
        this.f1c02 = f1c02;
    }

    public String getF1c03() {
        return f1c03;
    }

    public void setF1c03(String f1c03) {
        this.f1c03 = f1c03;
    }

    public String getF1c04() {
        return f1c04;
    }

    public void setF1c04(String f1c04) {
        this.f1c04 = f1c04;
    }

    public String getF1c05() {
        return f1c05;
    }

    public void setF1c05(String f1c05) {
        this.f1c05 = f1c05;
    }

    public String getF1c06() {
        return f1c06;
    }

    public void setF1c06(String f1c06) {
        this.f1c06 = f1c06;
    }

    public String getF1c07() {
        return f1c07;
    }

    public void setF1c07(String f1c07) {
        this.f1c07 = f1c07;
    }

    public String getF1c08() {
        return f1c08;
    }

    public void setF1c08(String f1c08) {
        this.f1c08 = f1c08;
    }


    // Sections
    public String getsA() {
        return sA;
    }

    public void setsA(String sA) {
        this.sA = sA;
    }


    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
    }


    public String get_luid() {
        return _luid;
    }

    public void set_luid(String _luid) {
        this._luid = _luid;
    }


    public String getFupno() {
        return fupno;
    }

    public void setFupno(String fupno) {
        this.fupno = fupno;
    }

    public String getsC() {
        return sC;
    }

    public void setsC(String sC) {
        this.sC = sC;
    }


    public String getScr_date() {
        return scr_date;
    }

    public void setScr_date(String scr_date) {
        this.scr_date = scr_date;
    }


    public SectionSelection getSecSelection() {
        return secSelection;
    }

    public void setSecSelection(SectionSelection secSelection) {
        this.secSelection = secSelection;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }


    public String getGpslat() {
        return gpslat;
    }

    public Form setGpslat(String gpslat) {
        this.gpslat = gpslat;
        return this;
    }


    public String getGpslng() {
        return gpslng;
    }

    public Form setGpslng(String gpslng) {
        this.gpslng = gpslng;
        return this;
    }


    public String getGpsdate() {
        return gpsdate;
    }

    public Form setGpsdate(String gpsdate) {
        this.gpsdate = gpsdate;
        return this;
    }


    public String getGpsacc() {
        return gpsacc;
    }

    public Form setGpsacc(String gpsacc) {
        this.gpsacc = gpsacc;
        return this;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public Form setDeviceid(String deviceid) {
        this.deviceid = deviceid;
        return this;
    }

    public String getTagid() {
        return tagid;
    }

    public Form setTagid(String tagid) {
        this.tagid = tagid;
        return this;
    }


    //======================


    //====================


    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }


    public String getProjectName() {
        return projectName;
    }


    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }


    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }


    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }


    public String getIstatus96x() {
        return istatus96x;
    }

    public void setIstatus96x(String istatus96x) {
        this.istatus96x = istatus96x;
    }


    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }


    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }


    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }


    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }


    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }


    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }


    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }


    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;

    }

    public Form Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(FormsTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.username = jsonObject.getString(FormsTable.COLUMN_USERNAME);
        this.sysdate = jsonObject.getString(FormsTable.COLUMN_SYSDATE);
        this.f1aspecid = jsonObject.getString(FormsTable.COLUMN_F1ASPECID);
        this.f1asite = jsonObject.getString(FormsTable.COLUMN_F1ASITE);
        this.f1bspecid = jsonObject.getString(FormsTable.COLUMN_F1BSPECID);
        this.f1bsite = jsonObject.getString(FormsTable.COLUMN_F1BSITE);
        this.f1cspecid = jsonObject.getString(FormsTable.COLUMN_F1CSPECID);
        this.f1csite = jsonObject.getString(FormsTable.COLUMN_F1CSITE);

        this.specimenID = jsonObject.getString(FormsTable.COLUMN_SPECIMEN_ID);
        this.siteID = jsonObject.getString(FormsTable.COLUMN_SITE_ID);

        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus96x = jsonObject.getString(FormsTable.COLUMN_ISTATUS96x);
        this.endingdatetime = jsonObject.getString(FormsTable.COLUMN_ENDINGDATETIME);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDATE);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_APPVERSION);
        this.formType = jsonObject.getString(FormsTable.COLUMN_FORM_TYPE);

        this.sA = jsonObject.getString(FormsTable.COLUMN_SA);
        this.sB = jsonObject.getString(FormsTable.COLUMN_SB);
        this.sC = jsonObject.getString(FormsTable.COLUMN_SC);

        return this;
    }

    public Form Hydrate(Cursor cursor, String type) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.username = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USERNAME));
        this.sysdate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYSDATE));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APPVERSION));
        this.f1aspecid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1ASPECID));
        this.f1asite = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1ASITE));
        this.f1bspecid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1BSPECID));
        this.f1bsite = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1BSITE));
        this.f1cspecid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1CSPECID));
        this.f1csite = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1CSITE));

        this.specimenID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SPECIMEN_ID));
        this.siteID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SITE_ID));

        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));
        this.formType = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORM_TYPE));

        switch (type) {
            case CONSTANTS.SECTION_C:
                sCHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SC)));
                break;
            case CONSTANTS.SECTION_A:
                sAHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SA)));
                break;
            case CONSTANTS.SECTION_B:
                sBHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SB)));
                break;
        }
        return this;
    }

    //
    public Form hydrateSB(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.f1aspecid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_F1ASPECID));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.sB = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SB));

        return this;
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Form.class);
    }

    public String sAtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("f1a01", f1a01)
                    .put("f1a02", f1a02)
                    .put("f1a03", f1a03)
                    .put("f1a04", f1a04)
                    .put("f1a05", f1a05)
                    .put("f1a06", f1a06)
                    .put("f1a07", f1a07)
                    .put("f1a08", f1a08)
                    .put("f1a09", f1a09)
                    .put("f1a10", f1a10)
                    .put("f1a11", f1a11);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\"" + e.getMessage() + "\"";
        }
        return json.toString();
    }

    public String sBtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("f1b01", f1b01)
                    .put("f1b02", f1b02)
                    .put("f1b02a", f1b02a)
                    .put("f1b03", f1b03)
                    .put("f1b04", f1b04)
                    .put("f1b05", f1b05)
                    .put("f1b06", f1b06)
                    .put("f1b06a", f1b06a)
                    .put("f1b07", f1b07);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\"" + e.getMessage() + "\"";
        }
        return json.toString();
    }

    public String sCtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("f1c01", f1c01)
                    .put("f1c02", f1c02)
                    .put("f1c03", f1c03)
                    .put("f1c04", f1c04)
                    .put("f1c05", f1c05)
                    .put("f1c06", f1c06)
                    .put("f1c07", f1c07)
                    .put("f1c08", f1c08);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\"" + e.getMessage() + "\"";
        }
        return json.toString();
    }

    public JSONObject toJSONObject(String type) {

        JSONObject json = new JSONObject();

        try {
            json.put(FormsTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
            json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
            json.put(FormsTable.COLUMN_USERNAME, this.username == null ? JSONObject.NULL : this.username);
            json.put(FormsTable.COLUMN_SYSDATE, this.sysdate == null ? JSONObject.NULL : this.sysdate);
            json.put(FormsTable.COLUMN_F1ASPECID, this.f1aspecid == null ? JSONObject.NULL : this.f1aspecid);
            json.put(FormsTable.COLUMN_F1ASITE, this.f1asite == null ? JSONObject.NULL : this.f1asite);
            json.put(FormsTable.COLUMN_F1BSPECID, this.f1bspecid == null ? JSONObject.NULL : this.f1bspecid);
            json.put(FormsTable.COLUMN_F1BSITE, this.f1bsite == null ? JSONObject.NULL : this.f1bsite);
            json.put(FormsTable.COLUMN_F1CSPECID, this.f1cspecid == null ? JSONObject.NULL : this.f1cspecid);
            json.put(FormsTable.COLUMN_F1CSITE, this.f1csite == null ? JSONObject.NULL : this.f1csite);

            json.put(FormsTable.COLUMN_SPECIMEN_ID, this.specimenID == null ? JSONObject.NULL : this.specimenID);
            json.put(FormsTable.COLUMN_SITE_ID, this.siteID == null ? JSONObject.NULL : this.siteID);


            switch (type) {
                case CONSTANTS.SECTION_A:
                    json.put(FormsTable.COLUMN_SA, new JSONObject(sAtoString()));
                    if (this.sA != null && !this.sA.equals("")) {
                        json.put(FormsTable.COLUMN_SA, new JSONObject(this.sA));
                    }
                    break;
                case CONSTANTS.SECTION_B:
                    json.put(FormsTable.COLUMN_SB, new JSONObject(sBtoString()));
                    if (this.sB != null && !this.sB.equals("")) {
                        json.put(FormsTable.COLUMN_SB, new JSONObject(this.sB));
                    }
                    break;
                case CONSTANTS.SECTION_C:
                    json.put(FormsTable.COLUMN_SC, new JSONObject(sCtoString()));
                    if (this.sC != null && !this.sC.equals("")) {
                        json.put(FormsTable.COLUMN_SC, new JSONObject(this.sC));
                    }
                    break;
            }

            json.put(FormsTable.COLUMN_F1ASPECID, this.f1aspecid == null ? JSONObject.NULL : this.f1aspecid);
            json.put(FormsTable.COLUMN_F1BSPECID, this.f1bspecid == null ? JSONObject.NULL : this.f1bspecid);
            json.put(FormsTable.COLUMN_F1CSPECID, this.f1cspecid == null ? JSONObject.NULL : this.f1cspecid);

            json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
            json.put(FormsTable.COLUMN_ISTATUS96x, this.istatus96x == null ? JSONObject.NULL : this.istatus96x);
            json.put(FormsTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);
            json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
            json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
            json.put(FormsTable.COLUMN_GPSDATE, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
            json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
            json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
            json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
            json.put(FormsTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
            json.put(FormsTable.COLUMN_FORM_TYPE, this.formType == null ? JSONObject.NULL : this.formType);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sAHydrate(String string) {
        if (string != null) {

            try {

                JSONObject json = new JSONObject(string);
                this.f1a01 = json.getString("f1a01");
                this.f1a02 = json.getString("f1a02");
                this.f1a03 = json.getString("f1a03");
                this.f1a04 = json.getString("f1a04");
                this.f1a05 = json.getString("f1a05");
                this.f1a06 = json.getString("f1a06");
                this.f1a07 = json.getString("f1a07");
                this.f1a08 = json.getString("f1a08");
                this.f1a09 = json.getString("f1a09");
                this.f1a10 = json.getString("f1a10");
                this.f1a11 = json.getString("f1a11");

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error at Hyderate", "cSHydrate: " + e.getMessage());
            }
        }
    }

    private void sBHydrate(String string) {
        if (string != null) {

            try {
                JSONObject json = new JSONObject(string);
                this.f1b01 = json.getString("f1b01");
                this.f1b02 = json.getString("f1b02");
                this.f1b02a = json.getString("f1b02a");
                this.f1b03 = json.getString("f1b03");
                this.f1b04 = json.getString("f1b04");
                this.f1b05 = json.getString("f1b05");
                this.f1b06 = json.getString("f1b06");
                this.f1b06a = json.getString("f1b06a");

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error at Hyderate", "cSHydrate: " + e.getMessage());
            }
        }
    }

    public void sCHydrate(String string) {
        if (string != null) {

            try {

                JSONObject json = new JSONObject(string);
                this.f1c01 = json.getString("f1c01");
                this.f1c02 = json.getString("f1c02");
                this.f1c03 = json.getString("f1c03");
                this.f1c04 = json.getString("f1c04");
                this.f1c05 = json.getString("f1c05");
                this.f1c06 = json.getString("f1c06");
                this.f1c07 = json.getString("f1c07");
                this.f1c08 = json.getString("f1c08");

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error at Hyderate", "cSHydrate: " + e.getMessage());
            }
        }
    }
}
