package com.example.myunitconverter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CurrencyFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinnerFrom,spinnerTo;

    //default error msg
    public static String ErrorMsg = "Plz choose the correct options";

    //button for performing conversion operation
    Button convert;

    //input and output texts
    EditText input;
    TextView output;

    //variables to store input and output values
    double inputvalue,outputvalue;

    //these strings are user selection where case1= From which unit
    //case2 = to which unit
    String case1,case2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_weight,container, false);
        spinnerFrom = view.findViewById(R.id.spinner_from);
        spinnerTo = view.findViewById(R.id.spinner_to);
        convert = view.findViewById(R.id.button_convert);
        input = view.findViewById(R.id.editText_Input);
        output = view.findViewById(R.id.editText_output);

        //creating arrayAdapter for a spinner
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(requireActivity()
                        .getBaseContext(),
                R.array.Currency,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        spinnerFrom.setOnItemSelectedListener(this);
        spinnerTo.setOnItemSelectedListener(this);

        convert.setOnClickListener(v -> {
            case1 = String.valueOf(spinnerFrom.getSelectedItem());
            case2 = String.valueOf(spinnerTo.getSelectedItem());
            inputvalue = Double.parseDouble(input.getText().toString());

            //switch statements to perform functionality of conversion
            switch (case1) {
                case "USD":
                    switch (case2){
                        case "USD":
                            outputvalue = inputvalue;
                            output.setText(String.format("%s \u0024", outputvalue));
                            break;
                        case "Euro":
                            outputvalue = inputvalue * 0.93;
                            output.setText(String.format("%s \u20AC", outputvalue));
                            break;
                        case "INR":
                            outputvalue = inputvalue * 82.52;
                            output.setText(String.format("%s \u20B9", outputvalue));
                            break;
                        default:
                            Toast.makeText(requireActivity().getBaseContext(), ErrorMsg+ case2, Toast.LENGTH_SHORT)
                                    .show();
                    }
                    break;
                case "Euro":
                    switch (case2){
                        case "USD":
                            outputvalue = inputvalue * 1.07;
                            output.setText(String.format("%s \u0024", outputvalue));
                            break;
                        case "Euro":
                            outputvalue = inputvalue;
                            output.setText(String.format("%s \u20AC", outputvalue));
                            break;
                        case "INR":
                            outputvalue = inputvalue * 88.28;
                            output.setText(String.format("%s \u20B9", outputvalue));
                            break;
                        default:
                            Toast.makeText(requireActivity().getBaseContext(), ErrorMsg+ case2, Toast.LENGTH_SHORT)
                                    .show();
                    }
                    break;
                case "INR":
                    switch (case2){
                        case "USD":
                            outputvalue = inputvalue * 0.012;
                            output.setText(String.format("%s \u0024", outputvalue));
                            break;
                        case "Euro":
                            outputvalue = inputvalue * 0.011;
                            output.setText(String.format("%s \u20AC", outputvalue));
                            break;
                        case "INR":
                            outputvalue = inputvalue;
                            output.setText(String.format("%s \u20B9", outputvalue));
                            break;
                        default:
                            Toast.makeText(requireActivity().getBaseContext(), ErrorMsg+ case2, Toast.LENGTH_SHORT)
                                    .show();
                    }
                    break;
                default:
                    Toast.makeText(requireActivity().getBaseContext(), ErrorMsg, Toast.LENGTH_SHORT)
                            .show();
            }
        });


        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();


    }
    public void onNothingSelected(AdapterView<?> parent) { }
}