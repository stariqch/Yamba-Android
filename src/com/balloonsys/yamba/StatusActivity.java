package com.balloonsys.yamba;

import com.balloonsys.yamba.util.PostToTwitter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StatusActivity extends Activity implements OnClickListener, TextWatcher {

	private static final String TAG = StatusActivity.class.getSimpleName();
	
	private EditText statusEditText;
	private Button updateStatusButton;
	private TextView textCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        
        statusEditText = (EditText) findViewById(R.id.statusEditText);
        statusEditText.addTextChangedListener(this);
        
        textCount = (TextView) findViewById(R.id.textCount);
        textCount.setText(Integer.toString(140));
        textCount.setTextColor(Color.GREEN);
        
        updateStatusButton = (Button) findViewById(R.id.updateStatusButton);
        updateStatusButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_status, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick");
		String status = statusEditText.getText().toString();
		new PostToTwitter().execute(status);
	}

	@Override
	public void afterTextChanged(Editable s) {
		int count = 140 - statusEditText.length();
		textCount.setText(Integer.toString(count));
		
		int color = Color.GREEN;
		
		if (count < 10) color = Color.YELLOW;
		if (count < 0) color = Color.RED;
		
		textCount.setTextColor(color);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}
}
