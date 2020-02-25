package jakopec.mvpcrud;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import jakopec.mvpcrud.model.Odgovor;
import jakopec.mvpcrud.presenter.ReadOsobaPresenter;
import jakopec.mvpcrud.presenter.ReadOsobaSucelja;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class OsobaUnitTest implements ReadOsobaSucelja.View{

    private ReadOsobaSucelja.Presenter presenter;

    public OsobaUnitTest(){
        System.out.println("Konstruktor");
        presenter = new ReadOsobaPresenter(this);
    }

    @Test
    public void citaj_isCorrect() {

        System.out.println("Metoda");
        presenter.dohvatiOsobe();
    }


    @Override
    public void zavrsenOdgovor(Odgovor odgovor) {
        System.out.println("Odgovor");
        System.out.println(odgovor.getOsobe().size());
        assertTrue(odgovor.getOsobe().size()>0);
    }
}