package android.example.com.myyogasongs.activities;

import android.example.com.myyogasongs.R;
import android.example.com.myyogasongs.adapters.SCTrackAdapter;
import android.example.com.myyogasongs.interfaces.SCService;
import android.example.com.myyogasongs.models.Track;
import android.example.com.myyogasongs.utils.Config;
import android.example.com.myyogasongs.adapters.SoundCloud;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * In this activity the application displays a list of tracks returned by the SoundCloud API
 * by tag list https://developers.soundcloud.com/docs/api/reference#tag_list

 */
public class PlayerActivity extends AppCompatActivity {

    private static final String TAG = "PlayList";
    private List<Track> mListItems;
    private SCTrackAdapter mAdapter;
    private ListView listView;
    private TextView mSelectedTrackTitle;
    private ImageView mSelectedTrackImage;
    private String category;
    String type;

    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //Get the extras from the previous activity
        category = getIntent().getStringExtra("category");
        type = getIntent().getStringExtra("type");

        //Get the Views from layout
        listView = (ListView)findViewById(R.id.track_list_view);
        mSelectedTrackTitle = (TextView)findViewById(R.id.selected_track_title);
        mSelectedTrackImage = (ImageView)findViewById(R.id.selected_track_image);
        mPlayerControl = (ImageView)findViewById(R.id.player_control);

        //Create the MediaPlayer element and prepare the listener
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                togglePlayPause();
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerControl.setImageResource(R.drawable.ic_play);
            }
        });

        //Creates a new SCTrackAdapter using the current context and the list of items
        mListItems = new ArrayList<Track>();
        mAdapter = new SCTrackAdapter(this, mListItems);
        listView.setAdapter(mAdapter);

        //Creates the behaviour of the mPlayerControl
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track track = mListItems.get(position);

                mSelectedTrackTitle.setText(track.getTitle());
                Picasso.with(PlayerActivity.this).load(track.getArtworkURL()).into(mSelectedTrackImage);

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();
                }

                try {
                    mMediaPlayer.setDataSource(track.getStreamURL() + "?client_id=" + Config.CLIENT_ID);
                    mMediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //Calls the SoundCloud service to get the tracks
        SCService scService = SoundCloud.getService();
        scService.getRecentTracks(type, new Callback<List<Track>>() {
            @Override
            public void success(List<Track> tracks, Response response) {
                loadTracks(tracks);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error: " + error);
            }
        });

    }

    /**
     * Stops the media player and makes it null
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }


    /**
     * Loads the tracks from SCService
     * @param tracks
     */
    private void loadTracks(List<Track> tracks) {
        mListItems.clear();
        mListItems.addAll(tracks);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Play and pause the song, changes the drawable for a better UX
     */
    private void togglePlayPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlayerControl.setImageResource(R.drawable.ic_play);
        } else {
            mMediaPlayer.start();
            mPlayerControl.setImageResource(R.drawable.ic_pause);
        }
    }
}
