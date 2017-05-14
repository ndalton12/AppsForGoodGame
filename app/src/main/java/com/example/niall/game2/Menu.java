package com.example.niall.game2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ComponentCallbacks2;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;


public class Menu extends DialogFragment {

    /*
    Class for the menu dialog popup
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set builder settings
        builder.setTitle(R.string.menu)
                .setItems(R.array.menu_strings, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 1) {
                            // If the settings button is clicked, go to the settings menu
                            goToSettings(getView());
                        }
                    }
                });

        // Return the dialog
        Dialog d = builder.create();
        d.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        return d;
    }

    /*
    Initialize intent to go to SettingsMenu activity
     */
    public void goToSettings(View v) {
        Intent intent = new Intent(getActivity(), SettingMenu.class);
        startActivity(intent);
    }

}
