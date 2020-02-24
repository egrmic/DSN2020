package jakopec.alati;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Odgovor {
    private String kljuc;
    private Date vrijeme;
    private boolean greska;
    private List<Osoba> osobe;
}
