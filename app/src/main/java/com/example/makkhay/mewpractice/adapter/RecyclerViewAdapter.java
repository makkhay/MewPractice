package com.example.makkhay.mewpractice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.makkhay.mewpractice.R;
import com.example.makkhay.mewpractice.model.Model;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  private Context mContext;
  private List<Model> mModelList;

  public RecyclerViewAdapter(Context context, List<Model> modelList) {
    mContext = context;
    mModelList = modelList;
  }

  @NonNull
  @Override
  public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.meow_item, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {
    Model model = mModelList.get(i);
    viewHolder.name.setText(model.getTitle());
    viewHolder.description.setText(model.getDescription());
    String imageUrl = model.getImage_url();
    Picasso.get()
        .load(imageUrl)
        .placeholder(R.drawable.ic_launcher_background) // show this if no image received
        .error(R.drawable.ic_launcher_background)           // show this when error occurs
        .into(viewHolder.mImageView);

  }

  @Override
  public int getItemCount() {
    return mModelList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView description;
    ImageView mImageView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.name_text);
      description = (TextView) itemView.findViewById(R.id.description_text);
      mImageView = (ImageView) itemView.findViewById(R.id.image_view);

    }
  }
}
