<?php

if (isset ( $jsonObj ["id"] ) && isset ( $jsonObj ["spInf4"] )) {

    require_once dirname(__DIR__, 1) . '/config.php';
    $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
    mysqli_set_charset ( $database, 'utf8' );

    if (! $database) { 

        $response ["status"] = 1;
        $response ["error"] = mysqli_connect_error ();        
    
    }
    else
    {
        
        $query = "UPDATE sp_terrregistry SET ";

        $query .= "spInf4='" . $jsonObj ["spInf4"] . "' ";
        $query .= "WHERE id=". $jsonObj ["id"];
        
        if ($database->query ( $query ) === TRUE) {

            $response ["status"] = 0;
            $response ["error"] = "";

        } else {

            $response ["status"] = 1;
            $response ["error"] = $database->error . " - Query: " . $query;

        }

        mysqli_close ( $database );

    }

} else {

    $response ["status"] = 1;
    $response ["error"] = "Error : isset";
}
?>