package com.rock.software.torrent.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rock.software.torrent.R;

public class MagnetToolActivity extends AppCompatActivity implements View.OnClickListener{

    protected EditText editText2;
    protected Button button;
    protected Button button2;
    protected RelativeLayout activityMagnetTool;
    private ClipboardManager clipboardManager;
    private ClipData clipData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_magnet_tool);
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        initView();
    }

    private void initView() {
        editText2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        activityMagnetTool = (RelativeLayout) findViewById(R.id.activity_magnet_tool);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                editText2.setText("magnet:?xt=urn:btih:"+editText2.getText().toString().trim());
                break;
            case R.id.button2:
                clipData = ClipData.newPlainText("text",editText2.getText().toString().trim());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
