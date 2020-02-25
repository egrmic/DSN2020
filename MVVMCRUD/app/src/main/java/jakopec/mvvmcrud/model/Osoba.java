package jakopec.mvvmcrud.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Osoba implements Serializable {

    private int id;
    private String ime;
    private String prezime;
    private String urlSlika;

}


