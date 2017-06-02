package uk.co.tigerspike.tigerspiketest.imagegallery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.tigerspike.tigerspiketest.R;
import uk.co.tigerspike.tigerspiketest.data.model.Image;

/**
 * Created by haitao on 01/06/2017.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private List<Image> imageList;

    @Inject
    public ImageListAdapter() {
    }

    public void setData(List<Image> imageList) {
        this.imageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image = imageList.get(position);
        Picasso.with(holder.itemView.getContext()).load(image.getMedia().getM()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
