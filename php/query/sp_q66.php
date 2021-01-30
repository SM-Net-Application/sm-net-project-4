<?php

if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] )) {

    require_once dirname(__DIR__, 1) . '/config.php';
    $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
    mysqli_set_charset ( $database, 'utf8' );

    if (! $database) {

        $response ["status"] = 1;
        $response ["error"] = mysqli_connect_error ();

    } else {

        $query = "INSERT INTO sp_postrepl (spInf1, spInf2, spInf3) VALUES (";
        $query .= $jsonObj ["spInf1"];
        $query .= ", ";
        $query .= "'";
        $query .= $jsonObj ["spInf2"];
        $query .= "'";
        $query .= ", ";
        $query .= "'";
        $query .= $jsonObj ["spInf3"];
        $query .= "'";
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