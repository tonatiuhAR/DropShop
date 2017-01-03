package com.rawr.dropshop.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rawr.dropshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCategoryDialogFragment extends DialogFragment implements View.OnClickListener {


    @BindView(R.id.etNewCategory)
    EditText etNewCategory;
    @BindView(R.id.btnNegative)
    Button btnNegative;
    @BindView(R.id.btnPositive)
    Button btnPositive;

    public NewCategoryDialogFragment() {
        //Empty required
    }

    public static NewCategoryDialogFragment newInstance(String title) {
        NewCategoryDialogFragment frag = new NewCategoryDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_new_category, container);
        ButterKnife.bind(this, view);
        btnNegative.setOnClickListener(this);
        btnPositive.setOnClickListener(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().setTitle(R.string.add__new_category);
        // Show soft keyboard automatically and request focus to field
        etNewCategory.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNegative:
                Toast.makeText(getActivity(), "cancelar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnPositive:
                Toast.makeText(getActivity(), "aceptar", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
