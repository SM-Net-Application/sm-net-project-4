<?php

require_once dirname(__DIR__, 1) . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $database, 'utf8' );

if (! $database) {

    $response ["status"] = 1;
    $response ["error"] = mysqli_connect_error ();

} else {

    $query = "SELECT * FROM sp_terrregistry";
    $result = mysqli_query ( $database, $query );

    $response ["status"] = 0;
    $response ["result"] = array ();

    while ( $resultRow = $result->fetch_assoc () ) {
        
        $resultRow = array_map ( "utf8_encode", $resultRow );
        
        $row = array ();
        $row ["id"] = $resultRow ["id"];
        $row ["spInf1"] = $resultRow ["spInf1"];
        $row ["spInf2"] = $resultRow ["spInf2"];
        $row ["spInf3"] = $resultRow ["spInf3"];
        $row ["spInf4"] = $resultRow ["spInf4"];
        
        array_push ( $response ["result"], $row );
        
    }

    $result->close ();
    
    mysqli_close ( $database );
}

?>