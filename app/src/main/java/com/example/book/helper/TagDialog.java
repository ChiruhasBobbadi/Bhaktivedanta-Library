package com.example.book.helper;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.book.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class TagDialog extends AppCompatDialogFragment {

    private EditText tags;
    private tagListener listener;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_tag_prompt, null);
        tags = view.findViewById(R.id.tag);
        return new MaterialAlertDialogBuilder(getActivity()).setTitle("Add Tag").setView(view)
                .setMessage("Choose a tag to asscoiate with this page")
                .setPositiveButton("Submit", (dialog, which) -> {
                    sendBackResult();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {

                }).show();


    }

    public void sendBackResult() {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        tagListener listener = (tagListener) getTargetFragment();
        listener.onDialogSubmit(tags.getText().toString());
        dismiss();
    }

    public interface tagListener {
        void onDialogSubmit(String inputText);
    }
}
