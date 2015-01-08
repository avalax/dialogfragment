package de.avalax.dialogfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_dialog, container, false);
        ButterKnife.inject(this, view);
        getDialog().setTitle(R.string.dialog_change_name);
        nameEditText.setText(getArguments().getString(ARGS_NAME));
        return view;
    }

    @OnClick(R.id.done_button)
    protected void positiveButton() {
        listener.onDialogPositiveClick(BasicDialogFragment.this);
        getDialog().dismiss();
    }

    public String getAlertText() {
        return nameEditText.getText().toString();
    }

    public interface DialogListener {
        public void onDialogPositiveClick(BasicDialogFragment basicDialogFragment);
    }
}