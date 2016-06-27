package android.example.com.myyogasongs.activities;

import android.example.com.myyogasongs.R;
import android.example.com.myyogasongs.adapters.CustomRecyclerAdapter;
import android.example.com.myyogasongs.models.Category;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize the categories data
        initializeData();

        //Gets the recycler view by Id and set the initial configuration
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.categories_rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(llm);
        }

        //Creates a new a instance of CustomRecyclerAdapter, passing the current context and the list of categories
        CustomRecyclerAdapter adapter = new CustomRecyclerAdapter(this, categories);
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
        }

    }

    /**
     * Initialize the categories data, creating 5 Yoga categories,
     * using Category model
     */
    private void initializeData(){
        categories = new ArrayList<>();
        categories.add(new Category("Yoga: Elevation of Spirit", "Feel the esscence of yoga with this amazing songs", R.drawable.yoga_elevation, "Yoga"));
        categories.add(new Category("New Age Yoga", "Like Vinyasa, these songs are both flowing and dynamic", R.drawable.yoga_new_age, "inspiration"));
        categories.add(new Category("Indie Yoga", "Listen this indie playlist to clear the mind", R.drawable.yoga_indie, "chill-out"));
        categories.add(new Category("Classic Relaxation", "Find solance in this mellow classic yoga songs", R.drawable.yoga_classic, "meditation"));

    }
}
