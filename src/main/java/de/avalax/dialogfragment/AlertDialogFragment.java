package de.avalax.dialogfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {

    private static final String ARGS_ALERT = "alert";
    private DialogListener listener;
    public static AlertDialogFragment newInstance(String alert) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_ALERT, alert);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (DialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement AlertDialogFragment.DialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String message = getArguments().getString(ARGS_ALERT);
        builder
                .setTitle(message)
                .setPositiveButton(R.string.done_label, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick(AlertDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AlertDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public interface DialogListener {
        public void onDialogPositiveClick(AlertDialogFragment alertDialogFragment);
    }
}
