package com.example.first;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information")
                .setMessage(
                        "# Enter your message and any prime or odd number when encoding a message.\n\n" +
                        "# When decoding, paste the message and key and press on Decode Button.\n\n" +
                        "# Note that entering 2 or any even number won't encode the message. This will be resolved shortly.\n\n" +
                        "# The Encode and Decode button won't be available if either or both Message and Key boxes are empty.\n\n" +
                        "# The maximum number of digits you can enter in the Key Box is 4."
                )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}