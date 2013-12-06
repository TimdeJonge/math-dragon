package org.teaminfty.math_dragon;

import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.expression.F;

import org.teaminfty.math_dragon.R;

import org.teaminfty.math_dragon.MathObject.EmptyChildException;
import org.teaminfty.math_dragon.MathObject.NotConstantException;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentMainScreen extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false);
    }
    
    @Override
    public void onStart()
    {
        super.onStart();
        
        // Create MathObjects to test the functionality
        MathOperationAdd add = new MathOperationAdd(100, 100);
        add.setChild(0, new MathConstant(20, 100, 100));
        add.setChild(1, new MathConstant(5, 100, 100));
        
        MathOperationSubtract subtract = new MathOperationSubtract(100, 100);
        subtract.setChild(0, add);
        subtract.setChild(1, new MathConstant(4, 100, 100));

        MathOperationMultiply multiply = new MathOperationMultiply(100, 100);
        multiply.setChild(0, new MathConstant(2, 100, 100));
        multiply.setChild(1, subtract);
        
        // Test the MathObject
        try
        {
            Log.i(getClass().getCanonicalName(), EvalEngine.eval(F.Simplify(multiply.eval())).toString());
            Log.i(getClass().getCanonicalName(), Double.toString(multiply.approximate()));
        }
        catch(EmptyChildException e)
        {
            e.printStackTrace();
        }
        catch(NotConstantException e)
        {
            e.printStackTrace();
        }
        
        // Just to test MathView
        MathView mathView = (MathView) getView().findViewById(R.id.mathView);
        mathView.setMathObject(multiply);
    }

}