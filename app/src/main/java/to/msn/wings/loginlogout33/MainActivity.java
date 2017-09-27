package to.msn.wings.loginlogout33;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nifty.cloud.mb.core.LoginCallback;
import com.nifty.cloud.mb.core.NCMB;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBUser;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {


    EditText UserName;
    EditText Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NCMB.initialize(getApplication(), "b18d561e7aa78c63abe4cd7a0bab2693b84cb975fe627e805281aaf9a2cfd82b", "80c9d743a613b53e9c09f104371d32cfdf7d79449ca1ed2b8b2e89dd31de5f72");

        UserName = (EditText) findViewById(R.id.editText_login_username);

        Password = (EditText) findViewById(R.id.editText_login_password);


        final NCMBUser currentUser = NCMBUser.getCurrentUser();


        Log.d("TEST", "ログイン中のユーザー: " + currentUser.getUserName());

        //------------------------------------------------------------------------------------------------------------------------
        //トーストでログイン中のユーザー見てみる

        Toast.makeText(getApplication(),  "ログイン中のユーザー:" + currentUser.getUserName(), Toast.LENGTH_SHORT).show();



        if (currentUser.getUserName() != null) {

            //その端末でログインしていて　ログアウトボタンを押してなくログアウトしていない場合　
            Intent intent = new Intent(getApplication(), Login_success_Activity.class);
            startActivity(intent);


        }

        if (currentUser.getUserName() == null) {

            //何もしない

        }
        //-------------------------------------------------------------------------------------------------------------------------


        //-------------------------ログインボタンタップ

        Button btn_login = (Button)findViewById(R.id.button_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NCMBUser user = new NCMBUser();

                user.setUserName(UserName.getText().toString());

                user.setPassword(Password.getText().toString());

                try {


                    NCMBUser.loginInBackground(UserName.getText().toString(), Password.getText().toString(), new LoginCallback() {
                        @Override
                        public void done(NCMBUser ncmbUser, NCMBException e) {

                            if(e == null) {

                                if (currentUser.getUserName() != null) {
                                    // ログイン中のユーザーの取得に成功
                                    Toast.makeText(getApplication(),  "ログイン中のユーザー:" + currentUser.getUserName(), Toast.LENGTH_SHORT).show();

                                    Log.d("TAG", "1");

                                    Intent intent = new Intent(getApplication(), Login_success_Activity.class);
                                    startActivity(intent);

                                } else if (currentUser.getUserName() == null){
                                    // 未ログインまたは取得に失敗
                                    Toast.makeText(getApplication(),  "未ログインまたは取得に失敗", Toast.LENGTH_SHORT).show();
                                    Log.d("TAG", "2");

                                }



                            }

                            if (e != null) {

                                if (currentUser == null){

                                    Toast.makeText(getApplication(),  "正常", Toast.LENGTH_SHORT).show();

                                }

                                if (currentUser != null) {

                                    Toast.makeText(getApplication(),  "エラー" + e.toString(), Toast.LENGTH_SHORT).show();

                                }

                            }

                        }
                    });
                } catch (NCMBException e) {

                    e.printStackTrace();

                }


            }
        });

    }
}



