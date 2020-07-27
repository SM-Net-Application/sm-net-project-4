<?php

require_once dirname(__DIR__, 1) . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $database, 'utf8' );

if (! $database) {
    
    $response ["status"] = 1;
    $response ["error"] = mysqli_connect_error ();
    
} else {
    
    $query = "SELECT keyInf, inf";
    $query .= " FROM sp_conf";

    $result = mysqli_query ( $database, $query );

    if (mysqli_num_rows ( $result ) > 0) {
        
        $response ["status"] = 0;
        $response ["error"] = "";
        
        $response ["result"] = array ();
        
        while ( $resultRow = $result->fetch_assoc () ) {
            
            $resultRow = array_map ( "utf8_encode", $resultRow );
            
            $row = array ();
            $row ["keyInf"] = $resultRow ["keyInf"];
            $row ["inf"] = $resultRow ["inf"];
            
            array_push ( $response ["result"], $row );
            
        }
    }
    
    $result->close ();
    mysqli_close ( $database );
    
}

?>