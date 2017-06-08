package yls.example.com.video.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yhdj on 2017/5/12.
 */

public class SPUtil {
    private static final String ISNIGHT_SP = "ISNIGHT_SP";
    private static final String IS_NIGHT = "IS_NIGHT";
    private static final String TEST_SIZE_SP = "TEST_SIZE_SP";
    private static final String TEST_SIZE = "TEST_SIZE";
    private static final String FIRST_SP = "FIRST_SP";
    private static final String FIRST_RUN = "FIRST_RUN";
    private static final String GET_CITY_SP = "GET_CITY_SP";
    private static final String CITY_ID = "CITY_ID";
    private static final String LONGITUDE_SP = "LONGITUDE_SP";
    private static final String LONGITUDE = "LONGITUDE";
    private static final String LATITUDE_SP = "LATITUDE_SP";
    private static final String LATITUDE = "LATITUDE";


    private static final String CINEMA_LONGITUDE_SP = "CINEMA_LONGITUDE_SP";
    private static final String CINEMA_LONGITUDE = "CINEMA_LONGITUDE";
    private static final String CINEMA_LATITUDE_SP = "CINEMA_LATITUDE_SP";
    private static final String CINEMA_LATITUDE = "CINEMA_LATITUDE";



    public static boolean getIsFirstRun(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FIRST_SP, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(FIRST_RUN, true);
    }

    public static void setIsFirstRun(Context context, boolean isRuned) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FIRST_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FIRST_RUN, isRuned);
        editor.commit();
    }


    public static String getCityId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GET_CITY_SP, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CITY_ID, "");
    }

    public static void setCity(Context context, String cityName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GET_CITY_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CITY_ID, cityName);
        editor.commit();
    }


    public static float getLongitude(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LONGITUDE_SP, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(LONGITUDE, 0);
    }

    public static void setLongitude(Context context, float longitude) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LONGITUDE_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(LONGITUDE, longitude);
        editor.commit();
    }


    public static float getLatitude(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LATITUDE_SP, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(LATITUDE, 0);
    }

    public static void setLatitude(Context context, float latitude) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LATITUDE_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(LATITUDE, latitude);
        editor.commit();
    }








    public static float getCinemaLongitude(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CINEMA_LONGITUDE_SP, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(CINEMA_LONGITUDE, 0);
    }

    public static void setCinemaLongitude(Context context, float longitude) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CINEMA_LONGITUDE_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(CINEMA_LONGITUDE, longitude);
        editor.commit();
    }


    public static float getCinemaLatitude(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CINEMA_LATITUDE_SP, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(CINEMA_LATITUDE, 0);
    }

    public static void setCinemaLatitude(Context context, float latitude) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CINEMA_LATITUDE_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(CINEMA_LATITUDE, latitude);
        editor.commit();
    }

    public static boolean getIsNight(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ISNIGHT_SP, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_NIGHT, true);
    }

    public static void setIsNight(Context context, boolean isRuned) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ISNIGHT_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_NIGHT, isRuned);
        editor.commit();
    }

    public static void updateIsNight(Context context, boolean isRuned) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ISNIGHT_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(IS_NIGHT);
        editor.putBoolean(IS_NIGHT, isRuned);
        editor.commit();
    }


    public static int getTextSize(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TEST_SIZE_SP, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(TEST_SIZE, 2);
    }

    public static void setTestSize(Context context, int textSize) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TEST_SIZE_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TEST_SIZE, textSize);
        editor.commit();
    }

}
