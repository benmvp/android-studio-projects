package com.benmvp.fragmentexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Created by ben.ilegbodu on 8/24/15.
 */
public class ToolbarFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private static int seekValue = 10;
    private static EditText editText;

    ToolbarListener activityCallback;

    public interface ToolbarListener {
        void onButtonClick(int position, String text);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ToolbarListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ToolbarListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the layout for the fragment
        View view = inflater.inflate(R.layout.toolbar_fragment, container, false);

        editText = (EditText) view.findViewById(R.id.editText1);
        final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar1);

        seekBar.setOnSeekBarChangeListener(this);

        final Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        return view;
    }

    public void buttonClicked(View view) {
        if (activityCallback != null)
            activityCallback.onButtonClick(seekValue, editText.getText().toString());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekValue = progress;
        if (activityCallback != null)
            activityCallback.onButtonClick(seekValue, editText.getText().toString());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
