package com.example.rent.zulicywiesciapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.rent.zulicywiesciapp.model.AddNewsDTO;
import com.example.rent.zulicywiesciapp.model.AddNewsResponse;
import com.example.rent.zulicywiesciapp.model.Author;
import com.example.rent.zulicywiesciapp.exceptions.ApiConnectException;
import com.example.rent.zulicywiesciapp.model.Login;
import com.example.rent.zulicywiesciapp.model.LoginResponse;
import com.example.rent.zulicywiesciapp.model.Register;
import com.example.rent.zulicywiesciapp.model.Status;
import com.example.rent.zulicywiesciapp.model.User;
import com.example.rent.zulicywiesciapp.retrofit.ApiManager;
import com.example.rent.zulicywiesciapp.utils.PassEncryption;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestRetrofitActivity extends AppCompatActivity implements ApiManager.OnNewsAddedListener, ApiManager.OnLoginListener {

    @BindView(R.id.test)
    TextView testField;


    @BindView(R.id.testresult)
    TextView testResult;

    User u;
    AddNewsDTO news;

    final String uploadFilePath = "/mnt/sdcard/";
    final String uploadFileName = "cat.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        ButterKnife.bind(this);
        u = new User();
        List<Integer> categories = new ArrayList<>();
        categories.add(1);
        categories.add(2);
        news = new AddNewsDTO("android add test1", "Początek traktatu czasu panowania Fryderyka Wielkiego, Króla Pruskiego żył w naszej wiedzy o Dobru jako co zasługuie na kreskowane em. Miało to jemu samemu był błąd pochodzący z swojego stanu w prawdzie była tylko na użyteczność i sądzenia. Kiedy więc wkradło się tylko podług rozumu do wykonywania dobrego sprawowania się, kiedy objektowe już dla niego jest najwyższy, jaki tylko w czasie, a grunt lub urzeczywiścić może. podług jednego punktu jej przymiotów wywodzę ja teraz spolszczone szanownej. Publiczności jako należytości. Od Dobra jako należytości. Od nas był ograniczony czyli godności człowieka rozkrzewiane, nim się godną szczęśliwości. Trzeci czyli doskonałości zbliżyć. Do takiej Istności, która istnieje sama przez moralność należy. Owo pierwsze zowie się dalej postępuje i równię sprawiedliwy. Od nas doprowadza do wystrzegania się wzięła część całości? Rod ludzki potrzebuje pewnego wzoru lub obowiąski które Dobro zostawia go i w religii, nagrody lub wprost pszenikający. Albowiem gdyby bez żądzy w stanie jest w sobie samemu. Na tej mierze ma większą wartość lub motywy uważane być osobą, a zatym owo miejsce. Tak wiec nie może np. zdrowie jest zastosowanie teologii do tych.",
                1, categories);
        ApiManager.login(new Login("md", "zaq"), this);

    }

    @OnClick(R.id.uploadBtn)
    public void upload() {

        final File file = new File(uploadFilePath+uploadFileName);
        ApiManager.addNewsImg(u.getToken(), "upload", file.getAbsolutePath(), TestRetrofitActivity.this, TestRetrofitActivity.this);


    }

    @Override
    public void onNewsAdded(AddNewsResponse response) {
            testResult.setText(response.getStatus() + ": " + response.getName());
    }

    @Override
    public void onLogin(LoginResponse response) {
        if(response.getStatus().equals(Status.OK)){
            u = response.getUser();
            testField.setText("login completed");
        } else {
            testField.setText(response.getStatus().toString());
        }
    }
}
