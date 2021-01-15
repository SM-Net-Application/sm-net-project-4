<?php
if (isset( $jsonObj ["songs"] )) {

    if (! empty ( $jsonObj ["songs"] )) {

        require_once dirname(__DIR__, 1) . '/config.php';
        $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
        mysqli_set_charset ( $database, 'utf8' );

        if (! $database) {

            $response ["status"] = 1;
            $response ["error"] = mysqli_connect_error ();

        } else {

            //Clear table

            $query = "DELETE FROM sp_songs";

            if ($database->query ( $query ) === TRUE) {

                foreach($jsonObj ["songs"] as $info)
                {
    
                    $query = "INSERT INTO sp_songs (spInf1, spInf2) VALUES (";
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

            } else {

                $response ["status"] = 1;
                $response ["error"] = $database->error;

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