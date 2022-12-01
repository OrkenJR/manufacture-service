package kz.iitu.manufactureservice.model.enums;

public enum Country {
    KZ, RU, US, JP;

    public static boolean isCountry(String str){
        try{
            Country c = Country.valueOf(str.toUpperCase());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
