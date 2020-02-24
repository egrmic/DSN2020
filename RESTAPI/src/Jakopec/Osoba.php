<?php
namespace Jakopec;
/**
 * @Entity @Table(name="osoba")
 **/
class Osoba
{
    /** @Id @Column(type="integer") @GeneratedValue **/
    protected $sifra;

    /** @Column(type="string") **/
    protected $ime;

    /** @Column(type="string") **/
    protected $prezime;


    public function setPodaci($podaci){
        foreach ($podaci AS $key => $value) $this->{$key} = $value;
    }

    /**
     * @return mixed
     */
    public function getSifra()
    {
        return $this->sifra;
    }

    /**
     * @param mixed $sifra
     */
    public function setSifra($sifra)
    {
        $this->sifra = $sifra;
    }


    /**
     * @return mixed
     */
    public function getIme()
    {
        return $this->ime;
    }

    /**
     * @param mixed $ime
     */
    public function setIme($ime)
    {
        $this->ime = $ime;
    }

    /**
     * @return mixed
     */
    public function getPrezime()
    {
        return $this->prezime;
    }

    /**
     * @param mixed $prezime
     */
    public function setPrezime($prezime)
    {
        $this->prezime = $prezime;
    }


}