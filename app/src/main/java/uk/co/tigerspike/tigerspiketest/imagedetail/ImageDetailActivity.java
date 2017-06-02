package uk.co.tigerspike.tigerspiketest.imagedetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.tigerspike.tigerspiketest.R;
import uk.co.tigerspike.tigerspiketest.data.model.Image;
import uk.co.tigerspike.tigerspiketest.util.Constants;

public class ImageDetailActivity extends AppCompatActivity implements ImageDetailContract.View{

    @BindView(R.id.image_view)
    ImageView imageView;

    @Inject ImageDetailPresenter imageDetailPresenter;

    private Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        image = getIntent().getParcelableExtra(Constants.IMAGE);

        DaggerImageDetailComponent.builder()
                .imageDetailModule(new ImageDetailModule(this, image))
                .build().inject(this);

        imageDetailPresenter.showImage();
    }

    @Override
    public void showImage(String imageUrl) {
        Picasso.with(this).load(imageUrl).into(imageView);
    }

    @Override
    public void showDetail(Image image) {

    }
}
