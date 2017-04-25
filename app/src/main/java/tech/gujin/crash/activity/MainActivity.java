package tech.gujin.crash.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteFullException;
import android.os.Bundle;
import android.view.View;

import tech.gujin.crash.R;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListener();
    }

    private void setListener() {
        findViewById(R.id.btn_crash).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crash:
                throw new SQLiteFullException("crash test");
        }
    }
}
