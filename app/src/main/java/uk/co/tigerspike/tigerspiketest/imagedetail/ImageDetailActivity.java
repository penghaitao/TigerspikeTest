package uk.co.tigerspike.tigerspiketest.imagedetail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.tigerspike.tigerspiketest.R;
import uk.co.tigerspike.tigerspiketest.data.model.Image;
import uk.co.tigerspike.tigerspiketest.util.Constants;
import uk.co.tigerspike.tigerspiketest.util.Utils;

public class ImageDetailActivity extends AppCompatActivity implements ImageDetailContract.View, View.OnClickListener{

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.share_image)
    ImageView shareView;
    @BindView(R.id.save_image)
    ImageView saveView;
    @BindView(R.id.open_image)
    ImageView openView;
    @BindView(R.id.title_view)
    TextView titleView;
    @BindView(R.id.publish_time_view)
    TextView publishTimeView;
    @BindView(R.id.taken_time_view)
    TextView takenTimeView;
    @BindView(R.id.tag_view)
    TextView tagView;

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 123;

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

        setupView();

    }

    public void setupView() {
        Picasso.with(this).load(image.getMedia().getLargeSizeImage()).into(imageView);
        titleView.setText(image.getTitle());
        publishTimeView.setText("Published at: " + image.getPublished());
        takenTimeView.setText("Taken at: " + image.getDateTaken());
        tagView.setText(image.getTags());
        shareView.setOnClickListener(this);
        saveView.setOnClickListener(this);
        openView.setOnClickListener(this);
    }

    @Override
    public void showDetail(Image image) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_image:
                if (verifyStoragePermissions()) {
                    String filename = image.getMedia().getImageName();
                    Uri uri;
                    File file = new File(Utils.getImageDirectory(this)+filename);
                    if (file.exists()) {
                        uri = Uri.fromFile(file);
                    } else {
                        uri = Utils.saveImage(imageView, filename, this);
                    }
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "", null));
                    intent.putExtra(android.content.Intent.EXTRA_EMAIL, "");
                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Image");
                    intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                    startActivity(Intent.createChooser(intent, "Share image"));
                }
                break;
            case R.id.save_image:
                if (verifyStoragePermissions()) {
                    String filename = image.getMedia().getImageName();
                    File file = new File(Utils.getImageDirectory(this) + filename);
                    if (!file.exists()) {
                        Uri uri = Utils.saveImage(imageView, filename, this);
                        if (uri != null) {
                            Toast.makeText(this, R.string.image_saved, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, R.string.image_saved_already, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.open_image:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(image.getLink()));
                startActivity(i);
                break;
        }
    }

    public boolean verifyStoragePermissions() {
        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return false;
        } else {
            Utils.init(this);
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Utils.init(this);
                    Toast.makeText(this, R.string.permission_granted, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, R.string.need_permission, Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
