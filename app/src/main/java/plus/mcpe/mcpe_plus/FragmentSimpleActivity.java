package plus.mcpe.mcpe_plus;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public abstract class FragmentSimpleActivity extends AppCompatActivity
{

	Fragment mFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);

		FragmentManager manager=getSupportFragmentManager();
		mFragment=manager.findFragmentById(R.id.fragment_container);
		if(mFragment==null){
			mFragment=createFragment();
			manager.beginTransaction().add(R.id.fragment_container,mFragment).commit();
		}
	}


	public abstract Fragment createFragment();
}

