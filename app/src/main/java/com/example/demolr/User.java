package com.example.demolr;

public class User {

    private String uname;
    private String name;
    private Number id;
    private String mobile;
    private String wa_link;
    private String fb_link;
    private String location;
    private String profile_pic;
    private Number age;

    public User(String uname, String name, Number id, String mobile, String wa_link, String fb_link, String location, String profile_pic, Number age) {
        this.uname = uname;
        this.name = name;
        this.id = id;
        this.mobile = mobile;
        this.wa_link = wa_link;
        this.fb_link = fb_link;
        this.location = location;
        this.profile_pic = profile_pic;
        this.age = age;
    }

    public String getUname() {
        return uname;
    }

    public String getName() {
        return name;
    }

    public Number getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWa_link() {
        return wa_link;
    }

    public String getFb_link() {
        return fb_link;
    }

    public String getLocation() {
        return location;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public Number getAge() {
        return age;
    }
}
