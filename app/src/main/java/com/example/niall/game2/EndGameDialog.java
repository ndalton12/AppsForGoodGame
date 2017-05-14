package com.example.niall.game2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class EndGameDialog extends DialogFragment {
    private Controller aController;

    /*
    Class for the end game dialog popup
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Gets necessary objects
        aController = (Controller) getActivity().getApplicationContext();
        StatsValues stats = aController.getStatsValues();

        String message;

        System.out.println("**************************" + aController.getOriginalQuestions().size()
                + "***********************" + stats.getNumChoices());


        // Set endgame message
        if (stats.getNumChoices() == aController.getOriginalQuestions().size() && stats.getTotalMoney() > 0)
            message = String.format("%s\n\n%s%s%s%s", getString(R.string.congrats),getString(R.string.end_message),
                    Integer.toString(Math.abs(stats.getMoneySpent())), " ",getString(R.string.end_message2));
        else
            message = String.format("%s%s%s%s", getString(R.string.end_message),
                    Integer.toString(Math.abs(stats.getMoneySpent())), " ",getString(R.string.end_message2));

        // Use the Builder to create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                    .setPositiveButton(R.string.play_again, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Move to the start screen and kill the Game.java activity
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);

                            Intent finishHim = new Intent(Game.FINISH_ALERT);
                            getActivity().sendBroadcast(finishHim);
                        }
                });
        builder.setCancelable(false);

        // Create the AlertDialog object and return it
        Dialog d = builder.create();
        d.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        return d;
    }
}
