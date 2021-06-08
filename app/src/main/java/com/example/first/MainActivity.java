package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button enbtn, debtn, wpbtn, helpbtn;
    private TextView msgOut;
    private EditText msgIn, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);  //these two lines will hide the title or nav bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();  // to hide action bar [action bar is already hidden in themes.xml files]

        setContentView(R.layout.activity_main);

        enbtn = findViewById(R.id.button);
        debtn = findViewById(R.id.button2);
        wpbtn = findViewById(R.id.button3);
        helpbtn = findViewById(R.id.button4);
        msgOut = findViewById(R.id.textView2);
        msgIn = findViewById(R.id.editText);
        key = findViewById(R.id.editText2);

        msgIn.addTextChangedListener(msgKeyTextWatcher);
        key.addTextChangedListener(msgKeyTextWatcher);

        // ENCODE button
        enbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edcode(true);
            }
        });

        // DECODE button
        debtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edcode(false);
            }
        });

        // WhatsApp button
        wpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(i);
            }
        });

        // HELP button
        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    // function to open Dialog box on clicking HELP button
    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

    // function to ENCODE or DECODE the message
    public void edcode(boolean toDo){

        String ed = msgIn.getText().toString();
        char[] s = ed.toCharArray();

        String edk = key.getText().toString();
        int edkey = Integer.parseInt(edk);

        int ats = edkey / 2;
        int nsl = edkey % 2;

        for(int i = 0; i < nsl; i++) {
            for (int j = 0; j < s.length; j++) {
                if(toDo == true)
                    s[j] += ats;
                else
                    s[j] -= ats;
            }
        }

        msgOut.setText(String.valueOf(s));

        ClipboardManager edClip = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData edC = ClipData.newPlainText("edMsgClip", msgOut.getText().toString());
        edClip.setPrimaryClip(edC);

        Toast.makeText(MainActivity.this, "Message Copied", Toast.LENGTH_SHORT).show();
    }

    // checks if the MESSAGE and KEY boxes are empty or not
    private TextWatcher msgKeyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String msgInput = msgIn.getText().toString();
            String keyInput = key.getText().toString().trim(); //trim() removes spaces

            enbtn.setEnabled(!msgInput.isEmpty() && !keyInput.isEmpty());
            debtn.setEnabled(!msgInput.isEmpty() && !keyInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}