package com.example.rent.zulicywiesciapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.model.AddNewsDTO;
import com.example.rent.zulicywiesciapp.model.AddNewsResponse;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.CategoryUtil;
import com.example.rent.zulicywiesciapp.utils.SessionManager;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class AddNewsActivity extends AbstractCapsuleActivity  implements ApiManager.OnNewsAddedListener{

    private static final int REQUEST_GALLERY_IMAGE = 10;
    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.add_button)
    Button addButton;

    @BindView(R.id.seekBar)
    SeekBar prioritySeekBar;

    @BindView(R.id.title_edit)
    TextInputEditText titleInput;
    @BindView(R.id.content_edit)
    TextInputEditText contentInput;

    @BindView(R.id.title_layout)
    TextInputLayout titleLayout;
    @BindView(R.id.content_layout)
    TextInputLayout contentLayout;

    Uri imageUri;
    private int priority=1;
    private Map<Integer,Category> categories = new HashMap<>();
    private List<CheckBox> checkBoxes = new ArrayList<>();

    @BindView((R.id.priority_textView))
    TextView priorityOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        setViews();
        checkIfLoggedIn();

    }

    @Override
    public void setViews() {
        super.setViews();
        setImageView();
        setSeekBar();
        setCheckboxes();
    }

    @Override
    void setToolbarTitle() {
        toolbarTitle.setText(R.string.add_new);
    }

    @OnClick(R.id.add_button)
    void onAddButtonClicked(){

        SessionManager.checkIfLoggedIn(this);
        Set<Integer>  categorySet = categories.keySet();
        List<Integer> categoryList = new ArrayList<>();

        for(Integer i: categorySet){
            categoryList.add(i);
        }

        File image = new File(imageUri.getPath());


        AddNewsDTO dto = new AddNewsDTO(titleInput.getText().toString()
                                        ,contentInput.getText().toString()
                                        ,priority
                                        ,getRealPathFromUri(imageUri)
                                        ,categoryList);
        ApiManager.addNews(SessionManager.getInstance().getUser().getToken(),dto,this);
    }

    public  String getRealPathFromUri(Uri uri) {
        String result = "";
        String documentID;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            String[] pathParts = uri.getPath().split("/");
            documentID = pathParts[pathParts.length - 1];
        } else {
            String pathSegments[] = uri.getLastPathSegment().split(":");
            documentID = pathSegments[pathSegments.length - 1];
        }
        String mediaPath = MediaStore.Images.Media.DATA;
        Cursor imageCursor = getContentResolver().query(uri, new String[]{mediaPath}, MediaStore.Images.Media._ID + "=" + documentID, null, null);
        if (imageCursor.moveToFirst()) {
            result = imageCursor.getString(imageCursor.getColumnIndex(mediaPath));
        }
        return result;
    }



    private void setSeekBar(){

        updatePriorityOutput();
        prioritySeekBar.setMax(90);
        prioritySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress<=30) priority = 1;
                else if(progress<=60) priority = 2;
                else priority=3;

                updatePriorityOutput();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void updatePriorityOutput() {

        switch (priority){
            case 1:
                priorityOutput.setText(R.string.priority_normal);
                break;
            case 2:
                priorityOutput.setText(R.string.priority_high);
                break;
            case 3:
                priorityOutput.setText(R.string.priority_breaking);
        }
    }

    private void setImageView() {
        Picasso.with(this)
                .load(R.drawable.happy_journalist_click)
                .centerCrop()
                .fit()
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangePhotoClicked();
            }
        });
    }

    private void setCheckboxes(){
       checkBoxes.add((CheckBox)findViewById(R.id.tech_cb));
       checkBoxes.add((CheckBox)findViewById(R.id.art_cb));
       checkBoxes.add((CheckBox)findViewById(R.id.econ_cb));
       checkBoxes.add((CheckBox)findViewById(R.id.pol_cb));
       checkBoxes.add((CheckBox)findViewById(R.id.sports_cb));
        
        for(CheckBox rb: checkBoxes){
            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String category = buttonView.getText().toString();
                    if(isChecked) addCategoryToList(category);
                    else deleteCategoryFromList(category);
                }
            });
        }
    }

    private void addCategoryToList(String categoryName) {

        int id = CategoryUtil.getCategoryIdFromString(categoryName);
        categories.put(id,new Category(id,categoryName));

    }

    private void deleteCategoryFromList(String categoryName) {
        int id = CategoryUtil.getCategoryIdFromString(categoryName);
        categories.remove(id);

    }

    private void onChangePhotoClicked() {

        launchImagePicker();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK) {

            updateImageView(data.getData());
            imageUri = data.getData();

        }
    }

    private void updateImageView(Uri data) {

        Bitmap bitmap = getBitmap(data);
        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
        }

    }

    public Bitmap getBitmap(Uri uri) {

        Bitmap source = null;
        try {
            source = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return source;
    }

    private void launchImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an image"),
                REQUEST_GALLERY_IMAGE);
    }



    @Override
    public void onNewsAdded(AddNewsResponse response) {
        Log.d("ADD NEWS", "onNewsAdded: " +response);
        Toast.makeText(this,"response: "+response,Toast.LENGTH_SHORT).show();
    }
}
