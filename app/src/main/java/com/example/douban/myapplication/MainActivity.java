package com.example.douban.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.net.IDN;

public class MainActivity extends Activity{

    private Button button;
    private Button button2;
    private Button button4;
    private Button button5;
    private EditText editText_name;
    private EditText editText_pass;
    private SharedPreferences pref;//记住密码
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //对象进行实例化
        editText_name=(EditText)findViewById(R.id.editText2);
        editText_pass=(EditText)findViewById(R.id.editText);
        button  = (Button) findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass=(CheckBox)findViewById(R.id.remember_pass);
        boolean isRemember=pref.getBoolean("remember_password",false);

        if(isRemember){
            //将已经保存的账号和密码都填写到文本框中
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            editText_name.setText(account);
            editText_pass.setText(password);
            rememberPass.setChecked(true);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=editText_name.getText().toString();
                String pwd=editText_pass.getText().toString();
                if(username.equals("zhao")&&pwd.equals("123")){
                    //密码确认
                    editor=pref.edit();
                    if(rememberPass.isChecked()){//检查复选框是否被选中
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",username);
                        editor.putString("password",pwd);

                    }else{
                        editor.clear();
                    }
                    editor.commit();
                    Intent in=new Intent(MainActivity.this,ThirdActivity.class);
                    startActivity(in);
                }else{
                    //textView.setText("很高兴见到你*.*");
                    Toast.makeText(MainActivity.this,"Input Wrong!Try again",
                     Toast.LENGTH_SHORT).show();
                }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
            //Toast.makeText(MainActivity.this,"You clicked Button2",
                      // Toast.LENGTH_SHORT).show();
                Intent in2=new Intent();//创建意图对象
                //发短信
                in2.setAction(Intent.ACTION_SENDTO);
                in2.setData(Uri.parse("smsto:10086"));
                in2.putExtra("sms_body","password");
                startActivity(in2);//=MainActivity.this.startActivity(in2)
            }
        });


        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);

            }
        });


        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.douban.com"));
                startActivity(intent);

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch(item.getItemId()){
            case  R.id.add_item:
                Toast.makeText(this,"You click Add",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.remove_item:
                Toast.makeText(this,"You cliked Remove",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
