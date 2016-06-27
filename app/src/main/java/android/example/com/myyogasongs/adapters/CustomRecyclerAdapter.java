package android.example.com.myyogasongs.adapters;

import android.content.Context;
import android.content.Intent;
import android.example.com.myyogasongs.R;
import android.example.com.myyogasongs.activities.PlayerActivity;
import android.example.com.myyogasongs.models.Category;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * This adapter uses extends RecyclerView adapter to create a list of elements using CardView.
 * We are passing a list of "categories" with an image resource, description and name.
 * When the user clicks one element(CardView) the application initiates a PlayerActivity
 */
public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.CategoryViewHolder> {

    List<Category> categories;
    Context context;

    public CustomRecyclerAdapter(Context con, List<Category> categories) {
        this.categories = categories;
        this.context = con;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView categoryName;
        TextView categoryDesc;
        ImageView categoryImg;

        CategoryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.category_card_view);
            categoryName = (TextView) itemView.findViewById(R.id.category_name);
            categoryDesc = (TextView) itemView.findViewById(R.id.category_desc);
            categoryImg = (ImageView) itemView.findViewById(R.id.category_img);
        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        holder.categoryName.setText(categories.get(position).getName());
        holder.categoryDesc.setText(categories.get(position).getDescription());
        holder.categoryImg.setImageResource(categories.get(position).getImageId());

        holder.cv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, PlayerActivity.class);
                i.putExtra("category", categories.get(position).getName());
                i.putExtra("type", categories.get(position).getType());
                context.startActivity(i);
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

}