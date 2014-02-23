package com.testing.app;

import android.app.Activity;


import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {

    public static final double TIP_AMOUNT = 0.12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tip_calculator, container, false);
            assert rootView != null;
            final Button calculate = (Button) rootView.findViewById(R.id.calculate_tip);
            final TextView tipText = (TextView) rootView.findViewById(R.id.tip_amount);
            final CheckBox shouldRound = (CheckBox) rootView.findViewById(R.id.should_round);
            final EditText billAmount = (EditText) rootView.findViewById(R.id.bill_amount);
            calculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        Editable text = billAmount.getText();
                        assert text != null;
                        double price = Double.parseDouble(text.toString());
                        double tip = price * TIP_AMOUNT;
                        if(shouldRound.isChecked()){
                            tip = Math.round(tip);
                        }
                        tipText.setText("Tip: " + tip + "$");
                    }catch(NumberFormatException e){
                        tipText.setText("Please enter a valid bill amount.");
                    }

                }
            });
            return rootView;
        }
    }

}
