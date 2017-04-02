package com.example.rent.zulicywiesciapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.zulicywiesciapp.model.AddNewsDTO;
import com.example.rent.zulicywiesciapp.model.AddNewsResponse;
import com.example.rent.zulicywiesciapp.model.Category;
import com.example.rent.zulicywiesciapp.model.Status;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.CategoryUtil;
import com.example.rent.zulicywiesciapp.utils.PhotoCachingTask;
import com.example.rent.zulicywiesciapp.utils.SessionManager;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class AddNewsActivity extends AbstractCapsuleActivity  implements ApiManager.OnNewsAddedListener
                                                                        ,PhotoCachingTask.OnPictureCachedListener {

    private static final int REQUEST_GALLERY_IMAGE = 10;
    private static final int REQUEST_CAPTURE = 110 ;
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

    Bitmap bitmap;
    ProgressDialog uploadDialog;
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

    }

    @Override
    public void setViews() {
        super.setViews();
        setImageView();
        setSeekBar();
        setCheckboxes();
        setTextWatchers();
    }

    @Override
    void setToolbarTitle() {
        toolbarTitle.setText(R.string.add_new);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_new_item_menu, menu);
        return true;
    }

    @OnClick(R.id.add_button)
    void onAddButtonClicked(){

        checkIfLoggedIn();
    }

    @Override
    public void onAuthCheck(Boolean response) {
        super.onAuthCheck(response);

        continueAdding();
    }

    private void continueAdding(){
        if(!areFieldsCorrect()) return;

        uploadDialog=uploadDialog.show(this,getString(R.string.uploading_title),getString(R.string.uploading));
        new PhotoCachingTask(this,this).execute(bitmap);
    }


    private boolean areFieldsCorrect(){
        boolean correct = true;

        String title = titleInput.getText().toString();
        if (TextUtils.isEmpty(title)) {
            titleLayout.setError(getString(R.string.empty_title_error));
            correct = false;
        }

        String content = contentInput.getText().toString();
        if (TextUtils.isEmpty(content)) {
            contentLayout.setError(getString(R.string.empty_content_error));
            correct = false;
        }

        if(bitmap==null) {
            correct=false;
            Toast.makeText(this,R.string.no_photo_error,Toast.LENGTH_SHORT).show();
        }

        return correct;
    }


    @Override
    public void onPictureCached(File file) {

        upload(file);
    }

    private void upload(File file){
        Set<Integer>  categorySet = categories.keySet();
        List<Integer> categoryList = new ArrayList<>();

        for(Integer i: categorySet){
            categoryList.add(i);
        }
        AddNewsDTO dto = new AddNewsDTO(titleInput.getText().toString()
                ,contentInput.getText().toString()
                ,priority
                ,"emptyPAth"
                ,categoryList);
        ApiManager.addNews(SessionManager.getInstance().getUser().getToken(),dto,file, this);

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

            Uri photoUri = data.getData();
            updateImageView(photoUri);

        }
        else if(requestCode==REQUEST_CAPTURE && resultCode==RESULT_OK){

            updateImageView(data.getData());

        }

    }


    private void updateImageView(Uri uri) {

         bitmap = getBitmap(uri);
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

        if(uploadDialog !=null) uploadDialog.dismiss();

        if(response.getStatus()== Status.UPLOAD_ERROR||response.getStatus()==Status.UNAUTHORISED){
            Toast.makeText(this,R.string.upload_error,Toast.LENGTH_SHORT).show();
        }
        else if (response.getStatus()==Status.OK){
            Toast.makeText(this,R.string.upload_ok,Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_make_photo:
                onMakePhotoClicked();
                break;
            case R.id.action_choose_photo:
                launchImagePicker();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMakePhotoClicked() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CAPTURE);
    }

    private void setTextWatchers(){
        setTextWatcher(titleInput,titleLayout);
        setTextWatcher(contentInput,contentLayout);
    }
    private void setTextWatcher(final TextInputEditText editText, final TextInputLayout inputLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (inputLayout.isErrorEnabled()) {
                    inputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


}
