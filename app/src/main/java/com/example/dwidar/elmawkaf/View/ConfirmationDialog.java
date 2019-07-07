package com.example.dwidar.elmawkaf.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ConfirmationDialog extends AppCompatDialogFragment
{

    private DialogLestiner lestiner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sign out")
                .setMessage("Do you want to sign out ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        lestiner.onYesClicked();
                    }
                });
        return builder.create();
    }

    public interface DialogLestiner
    {
        void onYesClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        lestiner = (DialogLestiner) context;
    }
}
