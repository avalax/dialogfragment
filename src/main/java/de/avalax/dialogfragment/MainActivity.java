package de.avalax.dialogfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends FragmentActivity implements BasicDialogFragment.DialogListener, AlertDialogFragment.DialogListener {
    private String alertText = "alertText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.buttonShowBasicDialog)
    protected void showBasicDialog() {
        BasicDialogFragment.newInstance(alertText).show(getSupportFragmentManager(), "basic_dialog_fragment");
    }

    @OnClick(R.id.buttonShowAlertDialog)
    protected void showAlertDialog() {
        AlertDialogFragment.newInstance(alertText).show(getSupportFragmentManager(), "alert_dialog_fragment");
    }

    @Override
    public void onDialogPositiveClick(BasicDialogFragment basicDialogFragment) {
        alertText = basicDialogFragment.getAlertText();
    }

    @Override
    public void onDialogPositiveClick(AlertDialogFragment alertDialogFragment) {
        Toast.makeText(this,R.string.alert_dialog_ok_label,Toast.LENGTH_SHORT).show();
    }
}