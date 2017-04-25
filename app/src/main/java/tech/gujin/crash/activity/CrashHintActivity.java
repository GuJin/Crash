package tech.gujin.crash.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteFullException;
import android.os.Bundle;
import android.widget.TextView;

import tech.gujin.crash.R;

public class CrashHintActivity extends Activity {

    public static final String THROWABLE = "throwable";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);

        TextView tvHint = (TextView) findViewById(R.id.tv_crash);
        Throwable ex = (Throwable) getIntent().getSerializableExtra(THROWABLE);

        if (ex instanceof SQLiteFullException) {
            tvHint.setText(R.string.SQLiteFullException_hint);
        }
    }
}
