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

public class TempFragment extends Fragment implements AdapterView.OnItemSelectedListener {

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
                R.array.Temperature,
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
                case "Celsius":
                    switch (case2){
                        case "Celsius":
                            outputvalue = inputvalue;
                            output.setText(String.format("%s C", outputvalue));
                            break;
                        case "Fareinheit (F)":
                            outputvalue = (inputvalue * 9/5)+32;
                            output.setText(String.format("%s F", outputvalue));
                            break;
                        case "Kelvin (K)":
                            outputvalue = inputvalue + 273;
                            output.setText(String.format("%s K", outputvalue));
                            break;
                        default:
                            Toast.makeText(requireActivity().getBaseContext(), ErrorMsg+ case2, Toast.LENGTH_SHORT)
                                    .show();
                    }
                    break;
                case "Fareinheit (F)":
                    switch (case2){
                        case "Celsius":
                            outputvalue = (inputvalue - 32)*5/9;
                            output.setText(String.format("%s C", outputvalue));
                            break;
                        case "Fareinheit (F)":
                            outputvalue = inputvalue;
                            output.setText(String.format("%s F", outputvalue));
                            break;
                        case "Kelvin (K)":
                            outputvalue = ((inputvalue - 32)*5/9) + 273;
                            output.setText(String.format("%s K", outputvalue));
                            break;
                        default:
                            Toast.makeText(requireActivity().getBaseContext(), ErrorMsg+ case2, Toast.LENGTH_SHORT)
                                    .show();
                    }
                    break;
                case "Kelvin (K)":
                    switch (case2){
                        case "Celsius":
                            outputvalue = inputvalue - 273;
                            output.setText(String.format("%s C", outputvalue));
                            break;
                        case "Fareinheit (F)":
                            outputvalue = ((inputvalue - 273)*9/5) + 32;
                            output.setText(String.format("%s F", outputvalue));
                            break;
                        case "Kelvin (K)":
                            outputvalue = inputvalue;
                            output.setText(String.format("%s K", outputvalue));
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