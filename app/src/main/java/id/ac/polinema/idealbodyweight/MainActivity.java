package id.ac.polinema.idealbodyweight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import id.ac.polinema.idealbodyweight.fragment.AboutFragment;
import id.ac.polinema.idealbodyweight.fragment.BodyMassIndexFragment;
import id.ac.polinema.idealbodyweight.fragment.BrocaIndexFragment;
import id.ac.polinema.idealbodyweight.fragment.MenuFragment;
import id.ac.polinema.idealbodyweight.fragment.ResultFragment;

public class MainActivity extends AppCompatActivity implements
		MenuFragment.OnFragmentInteractionListener,
		BrocaIndexFragment.OnFragmentInteractionListener,
		ResultFragment.OnFragmentInteractionListener,
		BodyMassIndexFragment.OnFragmentInteractionListener{
	// Deklarasikan atribut Fragment di sinii
	private  MenuFragment menuFragment;
    private AboutFragment aboutFragment;
    private BrocaIndexFragment brocaIndexFragment;
    private ResultFragment resultFragment;
	private BodyMassIndexFragment bodyMassIndexFragment;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        aboutFragment = AboutFragment.newInstance("Jimmy Audrio");
        menuFragment = new MenuFragment();
        brocaIndexFragment = new BrocaIndexFragment();
        resultFragment = new ResultFragment();
		bodyMassIndexFragment = new BodyMassIndexFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, menuFragment)
				.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return  true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO: Tambahkan penanganan menu di sini

        if (item.getItemId() == R.id.menu_about) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, aboutFragment)
                    .addToBackStack(null)
                    .commit();
        }

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBrocaIndexButtonClicked() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, brocaIndexFragment)
				.commit();

	}

	@Override
	public void onBodyMassIndexButtonClicked() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, bodyMassIndexFragment)
				.commit();

    }

	@Override
			public void onCalculateBrocaIndexClicked(float index) {
		resultFragment.setInformation(String.format("Your ideal weight is %.2f kg", index));
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, resultFragment)
				.commit();

			}

	@Override
		public void onTryAgainButtonClicked(String tag) {
		if (tag.equals(ResultFragment.BROCA_TAG)) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, brocaIndexFragment)
					.commit();
		} else {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, bodyMassIndexFragment)
					.commit();
			}

		}

	@Override
	public void onCalculateBMIClicked(float index, String tag) {
		resultFragment.setInformation(String.format("Your ideal body weight is %.2f", index));
		resultFragment.setTag(tag);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, resultFragment)
				.commit();

	}
}
