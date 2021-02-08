<?php
if (isset( $jsonObj ["news"] )) {

    if (! empty ( $jsonObj ["news"] )) {

        require_once dirname(__DIR__, 1) . '/config.php';
        $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
        mysqli_set_charset ( $database, 'utf8' );

        if (! $database) {

            $response ["status"] = 1;
            $response ["error"] = mysqli_connect_error ();

        } else {

                foreach($jsonObj ["news"] as $info)
                {
    
                    $query = "INSERT INTO sp_post (spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8) VALUES (";
                    $query .= $info ["spInf1"];
                    $query .= ",";
                    $query .= "'";
                    $query .= $info ["spInf2"];
                    $query .= "'";
                    $query .= ",";
                    $query .= "'";
                    $query .= $info ["spInf3"];
                    $query .= "'";
                    $query .= ",";
                    $query .= "'";
                    $query .= $info ["spInf4"];
                    $query .= "'";
                    $query .= ",";
                    $query .= "'";
                    $query .= $info ["spInf5"];
                    $query .= "'";
                    $query .= ",";
                    $query .= "'";
                    $query .= $info ["spInf6"];
                    $query .= "'";
                    $query .= ",";
                    $query .= $info ["spInf7"];
                    $query .= ",";
                    $query .= $info ["spInf8"];
                    $query .= ")";

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