package kz.iitu.manufactureservice.model.enums;

public enum Icon {
    SAND, IRON, GOLD;

    public static boolean isIcon(String str){
        try{
            Icon c = Icon.valueOf(str);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
