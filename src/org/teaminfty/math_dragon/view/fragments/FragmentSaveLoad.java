package org.teaminfty.math_dragon.view.fragments;

import java.util.ArrayList;

import org.teaminfty.math_dragon.R;
import org.teaminfty.math_dragon.model.FormulaDatabase;
import org.teaminfty.math_dragon.model.FormulaDatabase.Formula;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        final ArrayList<Formula> formulaList = new ArrayList<Formula>();
        formulaList = getAllFormulas();
        String[] values = { "Formule 1", "Formule 2", "Formule 3", "", "", "", "", "", "", "", ""};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
          //list.add(values[i]);
        	list.add("Formule" + Integer.toString(i));
        }
        
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> parent, final View view,
              int position, long id) {
            final String item = (String) parent.getItemAtPosition(position);
          }

        });
        
        return view;
    }
	
	private class OnSaveClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View btn)
        { 
        	// TODO actually save the given equation
        	dismiss(); }
    }
	public FragmentSaveLoad() {
		// TODO Auto-generated constructor stub
	}

}
