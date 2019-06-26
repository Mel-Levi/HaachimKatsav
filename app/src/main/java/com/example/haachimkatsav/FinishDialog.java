package com.example.haachimkatsav;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class FinishDialog extends DialogFragment {

    interface MyDialogListener{
        void onOkBtnClicked();
        void onOkBtnClicked2();
    }

    MyDialogListener callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (MyDialogListener) context;
        }catch (ClassCastException ex){
            throw new ClassCastException("");
        }
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String addCalendarEventBtn = getString(R.string.addCalendarEvent);
        String finishBtn = getString(R.string.finish);
        String requestTreatment = getString(R.string.requestTreatment);
        String requestSent = getString(R.string.requestSent);
        builder.setTitle(requestTreatment).setMessage(requestSent).setPositiveButton(addCalendarEventBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onOkBtnClicked2();
            }
        }).setNegativeButton(finishBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onOkBtnClicked();
            }
        });

        return builder.create();
    }
}


