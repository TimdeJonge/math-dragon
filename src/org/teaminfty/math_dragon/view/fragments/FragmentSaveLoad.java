package org.teaminfty.math_dragon.view.fragments;

import java.util.ArrayList;

import org.teaminfty.math_dragon.R;
import org.teaminfty.math_dragon.model.FormulaDatabase;
import org.teaminfty.math_dragon.model.FormulaDatabase.Formula;
import org.teaminfty.math_dragon.view.math.MathObjectEmpty;
import org.teaminfty.math_dragon.view.math.MathSymbol;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class FragmentSaveLoad extends DialogFragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Some dialog settings
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        
        View view = inflater.inflate(R.layout.fragment_saveload, container, false);
        Button button = (Button) view.findViewById(R.id.buttonSave);
        button.setOnClickListener(new OnSaveClickListener());
        ListView listView = (ListView) view.findViewById(R.id.listViewLoad);
      
        final ArrayList<String> list = new ArrayList<String>();

        FormulaDatabase formulaDatabase = new FormulaDatabase(getActivity());
        //formulaDatabase.saveFormula(1, "ABC-formule", new MathSymbol(3));
        //formulaDatabase.saveFormula(2, "ABC-formule", new MathObjectEmpty());
        //formulaDatabase.saveFormula(3, "ABC-formule", new MathObjectEmpty());
        //formulaDatabase.saveFormula(4, "ABC-formule", new MathObjectEmpty());
        //System.out.println(formulaDatabase.getFormulaByID(1).name);
        for (Formula formula : formulaDatabase.getAllFormulas()){
        	list.add("formula.name");
        }
        formulaDatabase.close();
        //list.add("Test");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> parent, final View view,
              int position, long id) {
            final String item = (String) parent.getItemAtPosition(position);
            // TODO load the loaded MathObject into the main screen
          }

        });
        
        return view;
    }
	
	private class OnSaveClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View btn)
        { 
        	FormulaDatabase formulaDatabase = new FormulaDatabase(getActivity());
          	EditText editText = (EditText) getView().findViewById(R.id.editTextSave);
          	formulaDatabase.saveFormula(formulaDatabase.getAllFormulas().size(), editText.getText().toString(), new MathSymbol(3));
          	//TODO get MathObject from MainActivity, insert in line above
        	System.out.println(formulaDatabase.getFormulaByID(155255));
          	formulaDatabase.close();
        	dismiss(); }
    }


}
