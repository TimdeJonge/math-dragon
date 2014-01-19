package org.teaminfty.math_dragon;

import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IExpr;
import org.teaminfty.math_dragon.exceptions.EmptyChildException;
import org.teaminfty.math_dragon.exceptions.MathException;
import org.teaminfty.math_dragon.model.Database;
import org.teaminfty.math_dragon.model.EvalHelper;
import org.teaminfty.math_dragon.model.ExpressionBeautifier;
import org.teaminfty.math_dragon.model.ModelHelper;
import org.teaminfty.math_dragon.model.ParenthesesHelper;
import org.teaminfty.math_dragon.view.TypefaceHolder;
import org.teaminfty.math_dragon.view.fragments.FragmentEvaluation;
import org.teaminfty.math_dragon.view.fragments.FragmentMainScreen;
import org.teaminfty.math_dragon.view.fragments.FragmentOperationsSource;
import org.teaminfty.math_dragon.view.fragments.FragmentSubstitute;
import org.teaminfty.math_dragon.view.math.Expression;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.espian.showcaseview.OnShowcaseEventListener;
import com.espian.showcaseview.ShowcaseView;
import com.espian.showcaseview.ShowcaseViews;
import com.espian.showcaseview.ShowcaseView.ConfigOptions;
import com.espian.showcaseview.targets.ActionViewTarget;
import com.espian.showcaseview.targets.PointTarget;
import com.espian.showcaseview.targets.ViewTarget;

public class MainActivity extends Activity implements FragmentOperationsSource.CloseMeListener
{

    /** The ActionBarDrawerToggle that is used to toggle the drawer using the action bar */
    ActionBarDrawerToggle actionBarDrawerToggle = null;

    /** Class that loads the Symja library in a separate thread */
    private class SymjaLoader extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... args)
        {
            // Simply do a simple (yet beautiful :D) calculation to make the system load Symja
            EvalEngine.eval(F.Plus(F.ZZ(1), F.Power(F.E, F.Times(F.Pi, F.I))));
            
            // Solve an integral to load that module
            EvalEngine.eval(F.Integrate(F.x, F.x));
            
            // Return null (return value won't be used)
            return null;
        }
    }
    private void tutorial() {
		final ShowcaseView actionBar, slideToOpen, editExpr, ops, evaluate;

		// TODO use string resources instead of hardcoded strings

		ShowcaseView.ConfigOptions co = new ShowcaseView.ConfigOptions();
		
		co.shotType = ShowcaseView.TYPE_ONE_SHOT;
		
		final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		
		
		
		
		actionBar = ShowcaseView
				.insertShowcaseView(
						new ActionViewTarget(this, ActionViewTarget.Type.HOME),
						this,
						"Functions and Operators",
						"Click on the MathDragOn icon to open the side-menu. Here you will find various functions and operators that you can drag n drop into the main screen",co);

		slideToOpen = ShowcaseView
				.insertShowcaseView(
						new PointTarget(0, 300),
						this,
						"Functions and Operators",
						"You can also slide from the left edge of the screen to quickly reveal the side-menu");

		

		slideToOpen.hide();
	
		actionBar.setOnShowcaseEventListener(new OnShowcaseEventListener() {

			@Override
			public void onShowcaseViewHide(ShowcaseView showcaseView) {
				slideToOpen.show();
				drawerLayout.closeDrawer(Gravity.LEFT);
			}

			@Override
			public void onShowcaseViewDidHide(ShowcaseView showcaseView) {}

			@Override
			public void onShowcaseViewShow(ShowcaseView showcaseView) {}

		});

		final MainActivity self = this;
		slideToOpen.setOnShowcaseEventListener(new OnShowcaseEventListener() {

			boolean wasHiding = false;
			@Override
			public void onShowcaseViewShow(ShowcaseView showcaseView) {
				slideToOpen.animateGesture(-40, 0, 200, 0);
			}

			@Override
			public void onShowcaseViewHide(ShowcaseView showcaseView) {
				wasHiding = true;
				
				ShowcaseView.ConfigOptions co = new ShowcaseView.ConfigOptions();
				final ShowcaseView dragNDrop = ShowcaseView.insertShowcaseView(new ViewTarget(findViewById(R.id.mathSourceDivide)), self,
						"Building an expression",
						"Try dragging the divide operator into the main view");
				
				dragNDrop.setOnShowcaseEventListener(new OnShowcaseEventListener() {
					
					@Override
					public void onShowcaseViewShow(ShowcaseView showcaseView) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onShowcaseViewHide(ShowcaseView showcaseView) {
						
					}
					
					@Override
					public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
				
//				
//				ShowcaseView.ConfigOptions co = new ShowcaseView.ConfigOptions();
//				
//				co.hideOnClickOutside = true;
//				final ShowcaseView dragNDrop = ShowcaseView.insertShowcaseView(new ViewTarget(findViewById(R.id.mathSourceAdd)), self,
//						"Building an expression",
//						"Drag the highlighted operation into the screen", co); 
//				dragNDrop.show();
//				
//				dragNDrop.setOnShowcaseEventListener(new OnShowcaseEventListener() {
//					
//					@Override
//					public void onShowcaseViewShow(ShowcaseView showcaseView) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void onShowcaseViewHide(ShowcaseView showcaseView) {
//						
//						if (drawerLayout.isDrawerOpen(Gravity.LEFT))
//						{
//							// The user didn't drag n drop anything. so what is the use.
//							
//							dragNDrop.show();
//							return;
//						}
//						final ShowcaseView editStuff = ShowcaseView.insertShowcaseView(new ViewTarget(findViewById(R.id.mathView)), self, "Editing", "now our operator needs content. Touch one of the boxes to start editing them!");
//						
//						editStuff.setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//						
//								editStuff.hide();
//							}
//						});
//					
//					}
//					
//					@Override
//					public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
//						
//						
//					}
//				});
//				
//			
		}
			@Override
			public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
				
				// make sure the user has opened the drawer before he continues with the tutorial
				if (wasHiding && !drawerLayout.isDrawerOpen(Gravity.LEFT))
				{
					slideToOpen.show();
					slideToOpen.animateGesture(-40, 0, 200, 0);
					return;
				}
				
			}
			
		});
		
		
	}
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        // Load the typefaces
        TypefaceHolder.loadFromAssets(getAssets());

        // Set the default size in the MathObject class
        Expression.lineWidth = getResources().getDimensionPixelSize(R.dimen.math_object_line_width);
        
        // Load the layout
        setContentView(R.layout.main);
        
        // Load Symja
        new SymjaLoader().execute();

        DrawerLayout drawerLayout;
        // DrawLayout specific code
        if((drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout)) != null)
        {

            // Remove the grey overlay
            drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
    
            // Set the shadow
            //drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.LEFT);
    
            try
            {
                // Make sure the source drawer is closed
                drawerLayout.closeDrawer(Gravity.LEFT);
    
                // Set the toggle for the action bar
                actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.operation_drawer_open, R.string.operation_drawer_close);
                getActionBar().setDisplayHomeAsUpEnabled(true);
    
                // Listen when to close the operations drawer
                ((FragmentOperationsSource) getFragmentManager().findFragmentById(R.id.fragmentOperationDrawer)).setOnCloseMeListener(this);
            }
            catch(IllegalArgumentException e)
            {
                // There was no drawer to close
            }
        }
        
        tutorial();
        
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred
        if(actionBarDrawerToggle != null)
            actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        // Notify actionBarDrawerToggle of any configuration changes
        if(actionBarDrawerToggle != null)
            actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Pass the event to ActionBarDrawerToggle, if it returns true, then it has handled the app icon touch event
        if(actionBarDrawerToggle != null && actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle other action bar items...
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * Gets called when wolfram alpha needs to be started. It will send the unevaluated IExpr to wolfram alpha for evaluation and inspection
     * @param view
     */
    public void wolfram(View view)
    {
        // Get the MathObject
        FragmentMainScreen fragmentMainScreen = (FragmentMainScreen) getFragmentManager().findFragmentById(R.id.fragmentMainScreen);
        Expression obj = fragmentMainScreen.getMathObject();
        
        // Only send to Wolfram|Alpha if the MathObject is completed
        if(obj.isCompleted())
        {
            // Get the query
            String query = obj.toString();
            
            // Strip the query of unnecessary outer parentheses
            if(query.startsWith("(") && query.endsWith(")"))
                query = query.substring(1, query.length() - 1);

            // Start an intent to send the user to Wolfram|Alpha
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.wolframalpha.com/input/?i=" + Uri.encode(query)));
            startActivity(intent);
        }
    }

    public void evaluate(View view)
    {
        try
        {
            // Load the substitutions
            Database db = new Database(this);
            EvalHelper.substitutions = db.getAllSubstitutions();
            db.close();
            
            // Calculate the answer
            FragmentMainScreen fragmentMainScreen = (FragmentMainScreen) getFragmentManager().findFragmentById(R.id.fragmentMainScreen);
            IExpr result = EvalEngine.eval( EvalHelper.eval(fragmentMainScreen.getMathObject()) );

            // Create an evaluation fragment and show the result
            FragmentEvaluation fragmentEvaluation = new FragmentEvaluation();
            fragmentEvaluation.showMathObject(ParenthesesHelper.setParentheses(ExpressionBeautifier.parse(ModelHelper.toExpression(result))));
            fragmentEvaluation.setEvalType(true);
            fragmentEvaluation.show(getFragmentManager(), "evaluation");
        }
        catch(EmptyChildException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(MathException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void approximate(View view)
    {
        try
        {
            // Load the substitutions
            Database db = new Database(this);
            EvalHelper.substitutions = db.getAllSubstitutions();
            db.close();
            
            // Calculate the answer
            FragmentMainScreen fragmentMainScreen = (FragmentMainScreen) getFragmentManager().findFragmentById(R.id.fragmentMainScreen);
            IExpr result = F.evaln( EvalHelper.eval(fragmentMainScreen.getMathObject()) );

            // Create an evaluation fragment and show the result
            FragmentEvaluation fragmentEvaluation = new FragmentEvaluation();
            fragmentEvaluation.showMathObject(ParenthesesHelper.setParentheses(ModelHelper.toExpression(result)));
            fragmentEvaluation.setEvalType(false);
            fragmentEvaluation.show(getFragmentManager(), "evaluation");
        }
        catch(EmptyChildException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(MathException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clear(View view)
    {
        // Simply clear the current formula
        FragmentMainScreen fragmentMainScreen = (FragmentMainScreen) getFragmentManager().findFragmentById(R.id.fragmentMainScreen);
        fragmentMainScreen.clear();
    }
    
    /** The tag for the substitute dialog */
    private static final String SUBSTITUTE_TAG = "substitute";
    
    public void substitute(View view)
    {
        // If a substitute dialog is already shown, stop here
        if(getFragmentManager().findFragmentByTag(SUBSTITUTE_TAG) != null)
            return;
        
        // Create and show the substitute dialog
        FragmentSubstitute fragmentSubstitute = new FragmentSubstitute();
        fragmentSubstitute.show(getFragmentManager(), SUBSTITUTE_TAG);
    }

    @Override
    public void closeMe()
    {
        // Get the DrawerLayout object
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        try
        {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
        catch(IllegalArgumentException e)
        {
            // there was no drawer to open.
            // Don't have a way to detect if there is a drawer yet so we just listen for this exception..
        }
    }   
}
