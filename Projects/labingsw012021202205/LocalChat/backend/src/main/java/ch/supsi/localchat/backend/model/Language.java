package ch.supsi.localchat.backend.model;

public enum Language {
    IT("it"), EN("US");
    private final String country;

    Language(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }


}
