package android.example.com.myyogasongs.adapters;

import android.example.com.myyogasongs.interfaces.SCService;
import android.example.com.myyogasongs.utils.Config;

import retrofit.RestAdapter;

public class SoundCloud {
    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder().setEndpoint(Config.API_URL).build();
    private static final SCService SERVICE = REST_ADAPTER.create(SCService.class);

    public static SCService getService() {
        return SERVICE;
    }
}
