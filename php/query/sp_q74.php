<?php
if (isset( $jsonObj ["spInfID"] ) && isset( $jsonObj ["spInf7"] )) {

    if (! empty ( $jsonObj ["spInfID"] )) {

        require_once dirname(__DIR__, 1) . '/config.php';
        $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
        mysqli_set_charset ( $database, 'utf8' );

        if (! $database) {

            $response ["status"] = 1;
            $response ["error"] = mysqli_connect_error ();

        } else {
    
            $query = "UPDATE sp_post SET ";
            $query .= "spInf7=" . $jsonObj ["spInf7"] . " ";
            $query .= "WHERE spInfID=". $jsonObj ["spInfID"];

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
        $response ["error"] = "Error : empty";
    }

} else {
    $response ["status"] = 1;
    $response ["error"] = "Error : isset";
}
?>