package jakopec.mvvmcrud;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import jakopec.mvvmcrud.adapter.OsobaAdapter;
import jakopec.mvvmcrud.model.Odgovor;
import jakopec.mvvmcrud.view.MainActivity;
import jakopec.mvvmcrud.viewmodel.OsobaViewModel;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {

        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        OsobaViewModel model = new OsobaViewModel();
        model.dohvatiOsobe().observe(mainActivity, new Observer<Odgovor>() {
            @Override
            public void onChanged(@Nullable Odgovor odgovor) {
                assertTrue(odgovor.isGreska());
            }
        });
    }
}
