package jakopec.mvpcrud.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Osoba implements Serializable {

    private int id;
    private String ime;
    private String prezime;
    private String urlSlika;

}


