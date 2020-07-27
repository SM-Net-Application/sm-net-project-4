<?php

if (isset ( $jsonObj ["infos"] )) {

    if (! empty ( $jsonObj ["infos"] )) {

        require_once dirname(__DIR__, 1) . '/config.php';
        $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
        mysqli_set_charset ( $database, 'utf8' );

        if (! $database) {

            $response ["status"] = 1;
            $response ["error"] = mysqli_connect_error ();

        } else {

            foreach($jsonObj ["infos"] as $info)
            {

                $query = "SELECT * FROM sp_conf WHERE keyInf='". $info ["key"] ."'";

                $find = mysqli_query ( $database, $query );

                if (mysqli_num_rows ( $find ) > 0) {

                    $query = "UPDATE sp_conf SET";
                    $query .= " inf='" . $info ["value"] . "'";
                    $query .= " WHERE keyInf='" . $info ["key"] . "'";

                    if ($database->query ( $query ) === TRUE) {

                        $response ["status"] = 0;
                        $response ["error"] = "";

                    } else {

                        $response ["status"] = 1;
                        $response ["error"] = $database->error;

                    }

                } else {

                    $query = "INSERT INTO sp_conf (keyInf, inf) VALUES (";
                    $query .= "'" . $info ["key"] . "', ";
                    $query .= "'" . $info ["value"] . "')";

                    if ($database->query ( $query ) === TRUE) {

                        $response ["status"] = 0;
                        $response ["error"] = "";

                    } else {

                        $response ["status"] = 1;
                        $response ["error"] = $database->error;

                    }

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