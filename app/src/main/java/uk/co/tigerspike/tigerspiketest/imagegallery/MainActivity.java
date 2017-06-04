package uk.co.tigerspike.tigerspiketest.imagegallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.tigerspike.tigerspiketest.App;
import uk.co.tigerspike.tigerspiketest.R;
import uk.co.tigerspike.tigerspiketest.data.model.Image;
import uk.co.tigerspike.tigerspiketest.imagedetail.ImageDetailActivity;
import uk.co.tigerspike.tigerspiketest.util.Constants;

public class MainActivity extends AppCompatActivity implements MainContract.View, ImageListAdapter.IClickItem{

    @Inject MainPresenter mMainPresenter;
    @Inject ImageListAdapter imageListAdapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        swipeRefreshLayout.setEnabled(false);
        DaggerMainComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);

        mMainPresenter.loadImageList(null);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        swipeRefreshLayout.setRefreshing(active);
    }

    @Override
    public void showImageList(List<Image> imageList) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        imageListAdapter.setData(imageList);
        recyclerView.setAdapter(imageListAdapter);
        imageListAdapter.setiClickItem(this);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnClick(Image image) {
        Intent intent = new Intent(this, ImageDetailActivity.class);
        intent.putExtra(Constants.IMAGE, image);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_order:
                showOrderPopupMenu();
                break;
        }
        return true;
    }

    @Override
    public void showOrderPopupMenu() {
        PopupMenu popup = new PopupMenu(this, findViewById(R.id.menu_order));
        popup.getMenuInflater().inflate(R.menu.order_images, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.publish_time:
                        mMainPresenter.orderImageList(imageListAdapter.getImageList(), Constants.ORDER_BY_PUBLISH_DATE);
                        break;
                    default:
                        mMainPresenter.orderImageList(imageListAdapter.getImageList(), Constants.ORDER_BY_TAKEN_DATE);
                        break;
                }
                return true;
            }
        });

        popup.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mMainPresenter.loadImageList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
