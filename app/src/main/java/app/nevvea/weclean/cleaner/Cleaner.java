package app.nevvea.weclean.cleaner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Anna on 7/9/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cleaner {
    private String gender;
    private String latitude;
    private String longitude;
    private String name;
    private String phone;
    private String price;
    private String profile_url;
    private String school;
    private String service;
    private String supplies;
    private String uid;
//    private String reviews;

    private Cleaner() {}

    Cleaner(String gender, String latitude, String longitude, String name, String phone, String price, String profile_url,
        String school, String service, String supplies, String uid) {

        this.gender = gender;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phone = phone;
        this.price = price;
        this.profile_url = profile_url;
        this.school = school;
        this.service = service;
        this.supplies = supplies;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrice() {
        return price;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public String getSchool() {
        return school;
    }

    public String getService() {
        return service;
    }

    public String getSupplies() {
        return supplies;
    }

    public String getUid() {
        return uid;
    }

}

