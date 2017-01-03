package com.rawr.dropshop.views.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rawr.dropshop.R;
import com.rawr.dropshop.views.NewCategoryDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TotalCategoriasFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.buttonNewCategory)
    FloatingActionButton buttonNewCategory;

    public TotalCategoriasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_total_categorias, container, false);
        ButterKnife.bind(this, view);

        buttonNewCategory.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonNewCategory:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                NewCategoryDialogFragment editNameDialogFragment = NewCategoryDialogFragment.newInstance("Some Title");
                editNameDialogFragment.show(fm, "fragment_edit_name");

                break;
        }
    }
}
