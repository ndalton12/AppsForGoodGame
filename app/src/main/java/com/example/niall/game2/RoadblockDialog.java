package com.example.niall.game2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class RoadblockDialog extends DialogFragment {
    private Controller aController;
    private TextView totalMoney;
    private TextView spentMoney;
    private TextView numChoices;
    private TextView moneyCounter;

    /*
    Class for Roadblock dialogs
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        aController = (Controller) getActivity().getApplicationContext();

        final Roadblock r = aController.getRoadblockRand();


        totalMoney = (TextView) getActivity().findViewById(R.id.tot_money);
        spentMoney = (TextView) getActivity().findViewById(R.id.spent_money);
        numChoices = (TextView) getActivity().findViewById(R.id.num_choices);
        moneyCounter = (TextView) getActivity().findViewById(R.id.money_counter);

        // Use the Builder to create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(String.format("%s%s","Random event: ", r.getPrompt()))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Update stuff
                        StatsValues stats = aController.getStatsValues();
                        aController.changeStats(r);

                        totalMoney.setText(String.valueOf(stats.getTotalMoney()));
                        spentMoney.setText(String.valueOf(stats.getMoneySpent()));
                        numChoices.setText(String.valueOf(stats.getNumChoices()));
                        moneyCounter.setText(
                                String.format(
                                        "%s%s",
                                        getString(R.string.dollar_sign),
                                        String.valueOf(stats.getTotalMoney())));

                        if (stats.getTotalMoney() <= 0) {
                            DialogFragment newFragment = new EndGameDialog();
                            newFragment.show(getFragmentManager(), "end_game_dialog");
                        }

                    }
                });
        builder.setCancelable(false);

        // Create the AlertDialog object and return it
        Dialog d = builder.create();
        d.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        return d;
    }
}
