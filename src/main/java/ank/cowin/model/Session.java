package ank.cowin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Session {

    @JsonProperty("center_id")
    int centerId;
    String name;
    String name1;
    String address;
    String address1;
    @JsonProperty("state_name")
    String stateName;
    String stateName1;
    @JsonProperty("district_name")
    String districtName;
    String districtName1;
    @JsonProperty("block_name")
    String blockName;
    String blockName1;
    String pincode;
    String from;
    String to;
    @JsonProperty("fee_type")
    String feeType;
    String fee;
    @JsonProperty("session_id")
    String sessionId;
    String date;
    @JsonProperty("available_capacity")
    int availableCapacity;
    @JsonProperty("min_age_limit")
    int minAgeLimit;
    String vaccine;
    String[] slots;

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName1() {
        return stateName1;
    }

    public void setStateName1(String stateName1) {
        this.stateName1 = stateName1;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName1() {
        return districtName1;
    }

    public void setDistrictName1(String districtName1) {
        this.districtName1 = districtName1;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockName1() {
        return blockName1;
    }

    public void setBlockName1(String blockName1) {
        this.blockName1 = blockName1;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public int getMinAgeLimit() {
        return minAgeLimit;
    }

    public void setMinAgeLimit(int minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public void setSlots(String[] slots){
        this.slots = slots;
    }

    public String[] getSlots(){
        return slots;
    }

    public String toString(){
        return "Name:"+ getName()+" "+getName1()+", Address:"+ getAddress()+ getBlockName()
                +", Pincode:"+ getPincode()+", Availability:"+ getAvailableCapacity()
                +", MinAgeLimit:"+ getMinAgeLimit()+", Slots:"+ Arrays.toString(getSlots())
                +", Vaccine:"+ getVaccine()+", FeeType:"+getFeeType()+", Fee:"+ getFee();
    }
}
