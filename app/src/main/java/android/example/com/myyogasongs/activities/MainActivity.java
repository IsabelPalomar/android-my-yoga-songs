package android.example.com.myyogasongs.activities;

import android.content.Intent;
import android.example.com.myyogasongs.R;
import android.example.com.myyogasongs.models.Category;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        initializeData();

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(categories);
        rv.setAdapter(adapter);


    }

    private void initializeData(){
        categories = new ArrayList<>();
        categories.add(new Category("Yoga: Elevation of Spirit", "Desc", R.drawable.yoga_elevation));
        categories.add(new Category("New Age Yoga", "Desc", R.drawable.yoga_new_age));
        categories.add(new Category("Indie Yoga", "Desc", R.drawable.yoga_indie));
        categories.add(new Category("Classic Relaxation", "Desc", R.drawable.yoga_classic));

    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

        List<Category> categories;

        RVAdapter(List<Category> persons){
            this.categories = persons;
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder holder, final int position) {
            holder.categoryName.setText(categories.get(position).getName());
            holder.categoryDesc.setText(categories.get(position).getDescription());
            holder.categoryImg.setImageResource(categories.get(position).getImageId());

            holder.cv.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, PlayerActivity.class);
                    i.putExtra("category", categories.get(position).getName());
                    startActivity(i);
                }

            });

        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class PersonViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView categoryName;
            TextView categoryDesc;
            ImageView categoryImg;

            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                categoryName = (TextView)itemView.findViewById(R.id.category_name);
                categoryDesc = (TextView)itemView.findViewById(R.id.category_desc);
                categoryImg = (ImageView)itemView.findViewById(R.id.category_img);


            }
        }

    }

}
