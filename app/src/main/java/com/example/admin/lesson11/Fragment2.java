package com.example.admin.lesson11;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Fragment2 extends Fragment {
    Button button;
    EditText editText;
    IActivityCallbacks iActivityCallbacks;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = (Button) view.findViewById(R.id.button2);
        editText = (EditText) view.findViewById(R.id.textView2);


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iActivityCallbacks = (MainActivity) context;

    }


    public void onClickSendData(View view) {
        iActivityCallbacks.sendData(editText.getText().toString()
        );
    }
}
