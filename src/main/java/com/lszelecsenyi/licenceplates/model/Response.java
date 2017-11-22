package com.lszelecsenyi.licenceplates.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    private String result;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    @JsonProperty("data")
    private Iterable<LicencePlate> licencePlates;

    public Response() {
        result = "ok";
    }

    public Response(String result) {
        this.result = result;
    }

    public Response(Iterable<LicencePlate> licencePlates) {
        result = "ok";
        this.licencePlates = licencePlates;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Iterable<LicencePlate> getLicencePlates() {
        return licencePlates;
    }

    public void setLicencePlates(Iterable<LicencePlate> licencePlates) {
        this.licencePlates = licencePlates;
    }
}
