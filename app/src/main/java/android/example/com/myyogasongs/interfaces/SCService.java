package android.example.com.myyogasongs.interfaces;

import android.example.com.myyogasongs.models.Track;
import android.example.com.myyogasongs.utils.Config;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SCService{
        @GET("/tracks?client_id=" + Config.CLIENT_ID)
        public void getRecentTracks(@Query("created_at[from]") String date, Callback<List<Track>> cb);

}
