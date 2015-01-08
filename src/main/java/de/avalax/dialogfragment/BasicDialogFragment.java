package de.avalax.dialogfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BasicDialogFragment extends DialogFragment {

    private static final String ARGS_NAME = "alertText";
    @InjectView(R.id.nameEditText)
    protected EditText nameEditText;
    private DialogListener listener;

    public static BasicDialogFragment newInstance(String name) {
        BasicDialogFragment fragment = new BasicDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_NAME, name);
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
                    + " must implement BasicDialogFragment.DialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_basic_dialog, null);
        ButterKnife.inject(this, view);

        nameEditText.setText(getArguments().getString(ARGS_NAME));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setMessage(R.string.dialog_change_name)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick(BasicDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        BasicDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public String getAlertText() {
        return nameEditText.getText().toString();
    }

    public interface DialogListener {
        public void onDialogPositiveClick(BasicDialogFragment basicDialogFragment);
    }
}
