package com.example.niall.game2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Menu extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set builder settings
        builder.setTitle(R.string.menu)
                .setItems(R.array.menu_strings, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 1) {
                            goToSettings(getView());
                        }
                    }
                });

        // Return the dialog
        return builder.create();
    }

    /*
    Initialize intent to go to SettingsMenu activity
     */
    public void goToSettings(View v) {
        Intent intent = new Intent(getActivity(), SettingMenu.class);
        startActivity(intent);
    }
}
