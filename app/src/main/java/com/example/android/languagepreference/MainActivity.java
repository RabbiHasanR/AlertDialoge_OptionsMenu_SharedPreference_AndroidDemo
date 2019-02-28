package com.example.android.languagepreference;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        //create sharedPreferences
        sharedPreferences=this.getSharedPreferences("com.example.android.languagepreference", Context.MODE_PRIVATE);
        //get language from sharedPreference
        String language=sharedPreferences.getString("language","Error");
        if(language.equals("Error")){

            //create alert dialog for chose language
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Choose a language")
                    .setMessage("Which language would yout like to use!")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("Bangla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("Bangla");
                        }
                    }).show();
        }
        else {
            textView.setText(language);
        }


    }

    //save language in sharedPreference
    public void setLanguage(String language){
        sharedPreferences.edit().putString("language",language).apply();
        textView.setText(language);

    }
    //create menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId()){
             case R.id.english:
                 setLanguage("English");
                 return true;
             case R.id.bangla:
                 setLanguage("Bangla");
                 return true;
             default:
                 return false;
         }
    }
}
