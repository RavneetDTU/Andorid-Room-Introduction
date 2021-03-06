package info.ravneetpunia.wordpaging1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_ID = "com.example.android.wordlistsql.ID";

    private EditText mEditWordView, mEditID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        mEditWordView = findViewById(R.id.et_word_AddNewActvity);
        mEditID = findViewById(R.id.et_id_AddNewActvity);

        final Button button = findViewById(R.id.btn_save_AddnewActvity);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    String id = mEditID.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(EXTRA_ID,id);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
