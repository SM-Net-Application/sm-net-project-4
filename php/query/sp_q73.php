<?php

require_once dirname(__DIR__, 1) . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $database, 'utf8' );

if (! $database) {
    
    $response ["status"] = 1;
    $response ["error"] = mysqli_connect_error ();
    
} else {
    
    $query = "SELECT spInfID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8";
    $query .= " FROM sp_post";

    $result = mysqli_query ( $database, $query );

    if (mysqli_num_rows ( $result ) > 0) {
        
        $response ["status"] = 0;
        $response ["error"] = "";
        $response ["result"] = array ();
        
        while ( $resultRow = $result->fetch_assoc () ) {
            
            $resultRow = array_map ( "utf8_encode", $resultRow );
            
            $row = array ();
            $row ["spInfID"] = $resultRow ["spInfID"];
            $row ["spInf1"] = $resultRow ["spInf1"];
            $row ["spInf2"] = $resultRow ["spInf2"];
            $row ["spInf3"] = $resultRow ["spInf3"];
            $row ["spInf4"] = $resultRow ["spInf4"];
            $row ["spInf5"] = $resultRow ["spInf5"];
            $row ["spInf6"] = $resultRow ["spInf6"];
            $row ["spInf7"] = $resultRow ["spInf7"];
            $row ["spInf8"] = $resultRow ["spInf8"];
            
            array_push ( $response ["result"], $row );
            
        }
    } else {

        $response ["status"] = 0;
        $response ["error"] = "";
        $response ["result"] = array ();
    }
    
    $result->close ();
    mysqli_close ( $database );
    
}

?>