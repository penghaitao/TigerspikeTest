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
    private IClickItem iClickItem;

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
        return new ViewHolder(view);
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

    public List<Image> getImageList() {
        return imageList;
    }

    interface IClickItem {
        void OnClick(Image image);
    }

    public void setiClickItem(IClickItem iClickItem) {
        this.iClickItem = iClickItem;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.image_view)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iClickItem.OnClick(imageList.get(getAdapterPosition()));
        }
    }
}
