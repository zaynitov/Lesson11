package com.example.admin.lesson11;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

public class Fragment1 extends Fragment {
    EditText editText;
    IActivityCallbacks iActivityCallbacks;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = (EditText) view.findViewById(R.id.editTextToSend);
        editText.setText(iActivityCallbacks.getData());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iActivityCallbacks = (MainActivity) context;

    }


}
