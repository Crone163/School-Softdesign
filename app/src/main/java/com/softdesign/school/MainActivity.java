package com.softdesign.school;


import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity {

    public static final String VISIBLE_KEY = "visible";
    public static final String ACCENT_COLOR = "accent";
    public static final String TOOLBAR_COLOR = "color_toolbar";

    private int mSaverColor;

    private CheckBox mCheckBox;

    private EditText mEditText;
    private EditText mEditText2;

    private Toolbar mToolBar;

    private Button mButtonRed;
    private Button mButtonBlue;
    private Button mButtonGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lg.e(this.getClass().getSimpleName(), "onCreate()");

        setTitle("Lesson 2.2");

        mEditText = (EditText)findViewById(R.id.editText);
        mEditText2 = (EditText)findViewById(R.id.editText2);
        mCheckBox = (CheckBox)findViewById(R.id.checkBox);
        mToolBar = (Toolbar)findViewById(R.id.toolbar);

        mButtonBlue = (Button) findViewById(R.id.btn_blue);
        mButtonGreen = (Button) findViewById(R.id.btn_green);
        mButtonRed = (Button) findViewById(R.id.btn_red);

        setupOnClick();
        setupToolbar();


    }

    private void setupOnClick(){

        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Click!", Toast.LENGTH_SHORT).show();
                if (mCheckBox.isChecked()) {
                    mEditText.setVisibility(View.INVISIBLE);
                } else {
                    mEditText.setVisibility(View.VISIBLE);
                }

            }
        });

        mButtonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setAccentAndToolbarColor(R.color.darkred,R.color.red);
            }
        });

        mButtonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setAccentAndToolbarColor(R.color.darkblue,R.color.blue);

            }
        });

        mButtonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setAccentAndToolbarColor(R.color.darkgreen,R.color.green);

            }
        });

    }

    /**
     *
     * @param accent - задаем цвет статус бару
     * @param toolbar - задаем цвет тул бару
     */
    private void setAccentAndToolbarColor(int accent,int toolbar){
        // используем ContextCompat т.к. в новых версиях будет изменен метод getColor
        if (checkLolipopVersion() && accent != 0)
            getWindow().setStatusBarColor(ContextCompat.getColor(this, accent));
        int res = ContextCompat.getColor(this, toolbar);
        mToolBar.setBackgroundColor(res);
        mSaverColor = toolbar;
    }

    private void setupToolbar(){

        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            Toast.makeText(MainActivity.this, "Menu Click!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.e(this.getClass().getSimpleName(),"onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.e(this.getClass().getSimpleName(), "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getClass().getSimpleName(), "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getClass().getSimpleName(), "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getClass().getSimpleName(), "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getClass().getSimpleName(), "onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.e(this.getClass().getSimpleName(), "onSaveInstanceState()");

        outState.putBoolean(VISIBLE_KEY, mEditText.getVisibility() == View.VISIBLE);
        outState.putInt(TOOLBAR_COLOR, mSaverColor);

        if (checkLolipopVersion()) outState.putInt(ACCENT_COLOR, getWindow().getStatusBarColor());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.e(this.getClass().getSimpleName(), "onRestoreInstanceState()");

        int visibilityState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        mEditText.setVisibility(visibilityState);


        int colorToolbar = savedInstanceState.getInt(TOOLBAR_COLOR);
        int accentColor = savedInstanceState.getInt(ACCENT_COLOR);

        if(colorToolbar != 0)
            setAccentAndToolbarColor(0,colorToolbar);
        if (accentColor != 0)
            getWindow().setStatusBarColor(accentColor);

    }

    private boolean checkLolipopVersion(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
    }
}
