package android.example.com.myyogasongs.utils;

import android.example.com.myyogasongs.interfaces.SCService;

import retrofit.RestAdapter;

public class SoundCloud {
    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder().setEndpoint(Config.API_URL).build();
    private static final SCService SERVICE = REST_ADAPTER.create(SCService.class);

    public static SCService getService() {
        return SERVICE;
    }
}
