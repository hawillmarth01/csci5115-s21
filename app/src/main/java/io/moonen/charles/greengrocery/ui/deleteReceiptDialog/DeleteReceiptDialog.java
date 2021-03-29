package io.moonen.charles.greengrocery.ui.deleteReceiptDialog;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.moonen.charles.greengrocery.R;


/* This code was automatically generated and I have very little idea what any of it does.
   My only edits were making it extend DialogFragment, adding declarations for button functions
    and deleting things that looked like they shouldn't be here.
 */



public class DeleteReceiptDialog extends DialogFragment {


    public DeleteReceiptDialog() {
        // Required empty public constructor
    }

    public static DeleteReceiptDialog newInstance() {
        DeleteReceiptDialog fragment = new DeleteReceiptDialog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_reciept_dialog, container, false);
    }

    public void confirm(android.view.View view) {
        //TODO: ???
    }

    public void cancel(android.view.View view) {
        //TODO: ???
    }
}