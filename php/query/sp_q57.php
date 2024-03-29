<?php

if (isset ($jsonObj ["spAudioID"] ) && isset ($jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf5"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["spInf9"] ) && isset ( $jsonObj ["spInf10"] ) && isset ( $jsonObj ["spInf11"] ) && isset ( $jsonObj ["spInf12"] ) && isset ( $jsonObj ["spInf13"] )){

    require_once dirname(__DIR__, 1) . '/config.php';
    $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
    mysqli_set_charset ( $database, 'utf8' );

    if (! $database) { 

        $response ["status"] = 1;
        $response ["error"] = mysqli_connect_error ();        
    
    }
    else
    {
        
        $query = "UPDATE sp_audio SET ";

        $query .= "spInf1=" . $jsonObj ["spInf1"] . ", ";
        
        $query .= "spInf2=" . $jsonObj ["spInf2"] . ", ";
        $query .= "spInf3=" . $jsonObj ["spInf3"] . ", ";
        
        $query .= "spInf4=" . $jsonObj ["spInf4"] . ", ";
        $query .= "spInf5=" . $jsonObj ["spInf5"] . ", ";
        $query .= "spInf6=" . $jsonObj ["spInf6"] . ", ";

        $query .= "spInf7=" . $jsonObj ["spInf7"] . ", ";
        $query .= "spInf8=" . $jsonObj ["spInf8"] . ", ";
        
        $query .= "spInf9=" . $jsonObj ["spInf9"] . ", ";
        $query .= "spInf10=" . $jsonObj ["spInf10"] . ", ";
        $query .= "spInf11=" . $jsonObj ["spInf11"] . ", ";
        $query .= "spInf12=" . $jsonObj ["spInf12"] . ", ";
        $query .= "spInf13=" . $jsonObj ["spInf13"] . " ";

        $query .= "WHERE spAudioID=". $jsonObj ["spAudioID"];

        
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