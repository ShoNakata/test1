package to.msn.wings.loginlogout33;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBUser;



/**
 * Created by 4163103 on 2017/09/08.
 */

public class Login_success_Activity extends Activity {



    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);




        final NCMBUser currentUser = NCMBUser.getCurrentUser();

        Button logout_btn = (Button) findViewById(R.id.logout_button);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();


                try {

                    NCMBUser.logout();



                } catch (NCMBException e) {

                    e.printStackTrace();

                }



                Toast.makeText(getApplication(),  "ログイン中のユーザー:" + currentUser.getUserName(), Toast.LENGTH_SHORT).show();

            }
        });



        Button button = (Button)findViewById(R.id.project_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), New_project_Activity.class);
                startActivity(intent);
            }
        });


    }






}


