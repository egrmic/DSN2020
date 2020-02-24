<?php
// bootstrap.php
use Doctrine\ORM\Tools\Setup;
use Doctrine\ORM\EntityManager;

use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;



class DoctrineBoostrap{

    private $entityManager;

    public function __construct()
    {

        $config = Setup::createAnnotationMetadataConfiguration([], true);
    
        $conn = array(
            'dbname' => 'dsn2020',
            'user' => 'dsn2020',
            'password' => 'dsn2020',
            'host' => '127.0.0.1',
            'driver' => 'pdo_mysql',
            'charset'  => 'utf8',
            'driverOptions' => array(
                1002 => 'SET NAMES utf8'
            )
        );

        $this->entityManager = EntityManager::create($conn, $config);
    }

    public function getEntityManager(){
        return $this->entityManager;
    }

    public function getJson($entitet){
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizers = array(new ObjectNormalizer());
        $serializer = new Serializer($normalizers, $encoders);
        $jsonContent = $serializer->serialize($entitet, 'json');
        return $jsonContent;
    }

}

