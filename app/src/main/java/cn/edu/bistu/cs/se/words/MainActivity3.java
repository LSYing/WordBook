package cn.edu.bistu.cs.se.words;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lsy on 2016/10/29.
 */
public class MainActivity3 extends AppCompatActivity {
    private String strMeaning;
    private EditText get_English;
    private TextView result_Ch,eng;
    private Button translate, input, return3;
    QueryTask task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        get_English = (EditText) findViewById(R.id.English);
        result_Ch = (TextView) findViewById(R.id.result);
        eng = (TextView)findViewById(R.id.eng);
        translate = (Button) findViewById(R.id.translate);
        input = (Button) findViewById(R.id.input);
        return3 = (Button) findViewById(R.id.return3);

        return3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String city = get_English.getText().toString();
                if (city.length() < 1) {
                    Toast.makeText(MainActivity3.this, "请输入单词",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                task = new QueryTask(MainActivity3.this, result_Ch,eng);
                task.execute(city);
            }
        });

        input.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String request = get_English.getText().toString();
                final String mean = result_Ch.getText().toString();
                WordsDB wordsDB = WordsDB.getWordsDB();
                wordsDB.InsertUserSql(request,mean, "");
                Toast.makeText(MainActivity3.this, "添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RefreshWordItemFragment(String strWord) {
        WordItemFragment wordItemFragment = (WordItemFragment) getFragmentManager().findFragmentById(R.id.wordslist);
        wordItemFragment.refreshWordsList(strWord);
    }

    private void RefreshWordItemFragment() {
        WordItemFragment wordItemFragment = (WordItemFragment) getFragmentManager().findFragmentById(R.id.wordslist);
        wordItemFragment.refreshWordsList();
    }


}
