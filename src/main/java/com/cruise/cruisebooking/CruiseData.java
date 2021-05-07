package com.cruise.cruisebooking;

public class CruiseData {
    private int id;
    private String cruiseName;

    // no arg, default
    public CruiseData() {
    }

    public CruiseData(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public int getCruiseId() {
        return id;
    }

    public void setCruiseId(int id) {
        this.id = id;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

//    @Override
//    public String toString() {
//        return "MyData{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }

}
