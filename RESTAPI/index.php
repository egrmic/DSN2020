<?php

use Composer\Autoload\ClassLoader;

use Jakopec\Osoba;

require 'vendor/autoload.php';


Flight::route('GET /osobe', function(){
    

    $doctrineBoostrap = Flight::entityManager();
    $em = $doctrineBoostrap->getEntityManager();
    $repozitorij = $em->getRepository('Jakopec\Osoba');
    $osobe = $repozitorij->findAll();

    echo $doctrineBoostrap->getJson($osobe);

});

Flight::route('POST /osobe', function(){
    $osoba = new Osoba();
    $osoba->setPodaci(Flight::request()->data);
    $doctrineBoostrap = Flight::entityManager();
    $em = $doctrineBoostrap->getEntityManager();
    $em->persist($osoba);
    $em->flush();

    $poruka=new stdClass();
    $poruka->tekst="OK";
    $poruka->greska=false;
    $odgovor=new stdClass();
    $odgovor->poruka=$poruka;
    
    Flight::json($odgovor); 

    header("HTTP/1.1 201 Created"); //https://gist.github.com/phoenixg/5326222

});


Flight::route('PUT /osobe/@sifra', function($sifra){ 
    $doctrineBoostrap = Flight::entityManager();
    $em = $doctrineBoostrap->getEntityManager();
    $repozitorijOsoba = $em->getRepository('Jakopec\Osoba');
    $osoba = $repozitorijOsoba->find($sifra);
    if ($osoba==null){
        $poruka=new stdClass();
        $poruka->tekst="Ne postoji osoba s danom šifrom";
        $poruka->greska=true;
        $odgovor=new stdClass();
        $odgovor->poruka=$poruka;
        
        Flight::json($odgovor); 
        exit;
    }
    $osoba->setPodaci(Flight::request()->data);
    $em->persist($osoba);
    $em->flush();

    $poruka=new stdClass();
    $poruka->tekst="OK";
    $poruka->greska=false;
    $odgovor=new stdClass();
    $odgovor->poruka=$poruka;
    
    Flight::json($odgovor); 
    

});

Flight::route('DELETE /osobe/@sifra', function($sifra){ 
    $doctrineBoostrap = Flight::entityManager();
    $em = $doctrineBoostrap->getEntityManager();
    $repozitorijOsoba = $em->getRepository('Jakopec\Osoba');
    $osoba = $repozitorijOsoba->find($sifra);

    if ($osoba==null){
        $poruka=new stdClass();
        $poruka->tekst="Ne postoji osoba s danom šifrom";
        $poruka->greska=true;
        $odgovor=new stdClass();
        $odgovor->poruka=$poruka;
        
        Flight::json($odgovor); 
        exit;
    }
    
    $em->remove($osoba);
    $em->flush();

    $poruka=new stdClass();
    $poruka->tekst="OK";
    $poruka->greska=false;
    $odgovor=new stdClass();
    $odgovor->poruka=$poruka;
    
    Flight::json($odgovor); 

});

Flight::route('/', function(){
    $poruka=new stdClass();
    $poruka->tekst="Nepotpuni zahtjev";
    $poruka->kod=1;
    $poruka->greska=true;
    $poruka->detalji="https://upute.app.hr/blog/api/v1/greske/7";

    $odgovor=new stdClass();
    $odgovor->poruka=$poruka;

    Flight::json($odgovor);

});

$cl = new ClassLoader('Jakopec', __DIR__ . '/src');
$cl->register();
require "bootstrap.php";
Flight::register('entityManager', 'DoctrineBoostrap');



Flight::start();