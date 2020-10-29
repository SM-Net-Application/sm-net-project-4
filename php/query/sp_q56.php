<?php

if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf5"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["spInf9"] ) && isset ( $jsonObj ["spInf10"] ) && isset ( $jsonObj ["spInf11"] ) && isset ( $jsonObj ["spInf12"] ) && isset ( $jsonObj ["spInf13"] )) {

    require_once dirname(__DIR__, 1) . '/config.php';
    $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
    mysqli_set_charset ( $database, 'utf8' );

    if (! $database) {

        $response ["status"] = 1;
        $response ["error"] = mysqli_connect_error ();

    } else {

        $query = "INSERT INTO sp_audio (spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12, spInf13) VALUES (";
        $query .= $jsonObj ["spInf1"] . ", ";
        $query .= $jsonObj ["spInf2"] . ", ";
        $query .= $jsonObj ["spInf3"] . ", ";
        $query .= $jsonObj ["spInf4"] . ", ";
        $query .= $jsonObj ["spInf5"] . ", ";
        $query .= $jsonObj ["spInf6"] . ", ";
        $query .= $jsonObj ["spInf7"] . ", ";
        $query .= $jsonObj ["spInf8"] . ", ";
        $query .= $jsonObj ["spInf9"] . ", ";
        $query .= $jsonObj ["spInf10"] . ", ";
        $query .= $jsonObj ["spInf11"] . ", ";
        $query .= $jsonObj ["spInf12"] . ", ";
        $query .= $jsonObj ["spInf13"];
        $query .= ")";

        if ($database->query ( $query ) === TRUE) {

            $response ["status"] = 0;
            $response ["error"] = "";

        } else {

            $response ["status"] = 1;
            $response ["error"] = $database->error;

        }

        mysqli_close ( $database );
    }

} else {

    $response ["status"] = 1;
    $response ["error"] = "Error : isset";

}
?>