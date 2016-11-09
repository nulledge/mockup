package com.demo.ib.mockup.Register.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.demo.ib.mockup.R;
import com.demo.ib.mockup.Register.RegisterOnClickListener;
import Core.enums.EventType;

import Core.Util.ContextResolver;
import Core.Util.Logger;
import Core.Util.Save;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_register );

        ContextResolver.Init( this );

        findViewById( R.id.registerButtonRegister ).setOnClickListener(
                new RegisterOnClickListener( this ) );
    }

    @Override
    public void onBackPressed() {
        Logger.addEvent( EventType.Click, "registerSystemBackButton" );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.system_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuSaveLog:
                Save.SaveLog( this );
                return true;
            case R.id.menuSurvey:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
