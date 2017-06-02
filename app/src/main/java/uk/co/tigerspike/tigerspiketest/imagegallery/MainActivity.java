package uk.co.tigerspike.tigerspiketest.imagegallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.tigerspike.tigerspiketest.App;
import uk.co.tigerspike.tigerspiketest.R;
import uk.co.tigerspike.tigerspiketest.data.model.Image;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @Inject MainPresenter mMainPresenter;
    @Inject ImageListAdapter imageListAdapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);

        mMainPresenter.loadImageList();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showImageList(List<Image> imageList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        imageListAdapter.setData(imageList);
        recyclerView.setAdapter(imageListAdapter);
    }
}
