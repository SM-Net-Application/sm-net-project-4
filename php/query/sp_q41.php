<?php

if (isset ( $jsonObj ["typePlace"] ) && isset ( $jsonObj ["descr"] ) && isset ( $jsonObj ["street"] ) && isset ( $jsonObj ["num"] ) && isset ( $jsonObj ["postCode"] ) && isset ( $jsonObj ["city"] ) && isset ( $jsonObj ["county"] ) && isset ( $jsonObj ["country"] ) && isset ( $jsonObj ["coord"] ) && isset ( $jsonObj ["def"] )) {

    require_once dirname(__DIR__, 1) . '/config.php';
    $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
    mysqli_set_charset ( $database, 'utf8' );

    if (! $database) {

        $response ["status"] = 1;
        $response ["error"] = mysqli_connect_error ();

    } else {

        $query = "INSERT INTO sp_places (spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10) VALUES (";
        $query .= $jsonObj ["typePlace"] . ", ";
        $query .= "'" . $jsonObj ["descr"] . "', ";
        $query .= "'" . $jsonObj ["street"] . "', ";
        $query .= "'" . $jsonObj ["num"] . "', ";
        $query .= "'" . $jsonObj ["postCode"] . "', ";
        $query .= "'" . $jsonObj ["city"] . "', ";
        $query .= "'" . $jsonObj ["county"] . "', ";
        $query .= "'" . $jsonObj ["country"] . "', ";
        $query .= "'" . $jsonObj ["coord"] . "', ";
        
        $query .= $jsonObj ["def"] . ")";

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