package com.example.dwidar.elmawkaf.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dwidar.elmawkaf.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CallCabBottomSheetDialog extends BottomSheetDialogFragment
{
    private BottomSheetListener bottomSheetListener;
    Button BtnCallCab;
    TextView TxtViewShowPrice;
    String cost;

    public CallCabBottomSheetDialog(){}

    @SuppressLint("ValidFragment")
    public CallCabBottomSheetDialog(double cost)
    {
        this.cost = String.valueOf(cost);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        TxtViewShowPrice = v.findViewById(R.id.TxtView_Show_Price);

        int rounded_cost = Math.round(Float.parseFloat(this.cost));
        TxtViewShowPrice.setText(rounded_cost + " EGP");

        BtnCallCab = v.findViewById(R.id.BtnCallCab);
        BtnCallCab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                bottomSheetListener.onButtonClicked();
            }
        });

        return v;
    }

    public interface BottomSheetListener
    {
        void onButtonClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        bottomSheetListener = (BottomSheetListener) context;

    }
}
