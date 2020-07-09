<?php

if (isset ( $jsonObj ["startDate"] ) && isset ( $jsonObj ["day1"] ) && isset ( $jsonObj ["hour1"] ) && isset ( $jsonObj ["minute1"] ) && isset ( $jsonObj ["day2"] ) && isset ( $jsonObj ["hour2"] ) && isset ( $jsonObj ["minute2"] )) {

    if (! empty ( $jsonObj ["startDate"] )) {

        require_once dirname(__DIR__, 1) . '/config.php';
        $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
        mysqli_set_charset ( $database, 'utf8' );

        if (! $database) {

            $response ["status"] = 1;
            $response ["error"] = mysqli_connect_error ();

        } else {

            /* CHECK SAME DATE */

            $query = "SELECT * FROM sp_datetime WHERE spInf1=";
            $query .= "'" . $jsonObj ["startDate"] . "'";

            $result = mysqli_query ( $database, $query );

            if (mysqli_num_rows ( $result ) > 0) {

                $response ["status"] = 2;
                $response ["error"] = "datetime.new.error.exists";
                
            } else {

                $query = "INSERT INTO sp_datetime (spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7) VALUES (";
                $query .= "'" . $jsonObj ["startDate"] . "', ";
                $query .= $jsonObj ["day1"] . ", ";
                $query .= $jsonObj ["hour1"] . ", ";
                $query .= $jsonObj ["minute1"] . ", ";
                $query .= $jsonObj ["day2"] . ", ";
                $query .= $jsonObj ["hour2"] . ", ";
                $query .= $jsonObj ["minute2"] . ")";

                if ($database->query ( $query ) === TRUE) {

                    $response ["status"] = 0;
                    $response ["error"] = "";

                } else {

                    $response ["status"] = 1;
                    $response ["error"] = $database->error;

                }
            }
            mysqli_close ( $database );
        }
    } else {

        $response ["status"] = 1;
        $response ["error"] = "Error : empty";

    }
} else {

    $response ["status"] = 1;
    $response ["error"] = "Error : isset";

}
?>