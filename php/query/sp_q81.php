<?php

if (isset ( $jsonObj ["id"] ) && isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["spInf9"] ) && isset ( $jsonObj ["spInf10"] ) && isset ( $jsonObj ["spInf11"] ) && isset ( $jsonObj ["spInf12"] ) && isset ( $jsonObj ["spInf13"] ) && isset ( $jsonObj ["spInf14"] ) && isset ( $jsonObj ["spInf15"] ) && isset ( $jsonObj ["spInf16"] ) && isset ( $jsonObj ["spInf17"] ) && isset ( $jsonObj ["spInf18"] ) && isset ( $jsonObj ["spInf19"] ) && isset ( $jsonObj ["spInf20"] ) && isset ( $jsonObj ["spInf21"] ) && isset ( $jsonObj ["spInf22"] ) && isset ( $jsonObj ["spInf23"] ) && isset ( $jsonObj ["spInf24"] ) && isset ( $jsonObj ["spInf25"] ) && isset ( $jsonObj ["spInf26"] ) && isset ( $jsonObj ["spInf27"] ) && isset ( $jsonObj ["spInf28"] ) && isset ( $jsonObj ["spInf29"] ) && isset ( $jsonObj ["spInf30"] ) && isset ( $jsonObj ["spInf31"] ) && isset ( $jsonObj ["spInf32"] ) && isset ( $jsonObj ["spInf33"] ) && isset ( $jsonObj ["spInf34"] ) && isset ( $jsonObj ["spInf35"] ) && isset ( $jsonObj ["spInf36"] ) && isset ( $jsonObj ["spInf37"] ) && isset ( $jsonObj ["spInf38"] ) && isset ( $jsonObj ["spInf39"] ) && isset ( $jsonObj ["spInf40"] )) {

    require_once dirname(__DIR__, 1) . '/config.php';
    $database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
    mysqli_set_charset ( $database, 'utf8' );

    if (! $database) { 

        $response ["status"] = 1;
        $response ["error"] = mysqli_connect_error ();        
    
    }
    else
    {
        
        $query = "UPDATE sp_territory SET ";

        $query .= "spInf1='" . $jsonObj ["spInf1"] . "', ";
        $query .= "spInf2='" . $jsonObj ["spInf2"] . "', ";
        $query .= "spInf3='" . $jsonObj ["spInf3"] . "', ";
        $query .= "spInf4='" . $jsonObj ["spInf4"] . "', ";
        $query .= "spInf5='" . $jsonObj ["spInf5"] . "', ";
        $query .= "spInf6='" . $jsonObj ["spInf6"] . "', ";
        $query .= "spInf7='" . $jsonObj ["spInf7"] . "', ";
        $query .= "spInf8='" . $jsonObj ["spInf8"] . "', ";
        $query .= "spInf9='" . $jsonObj ["spInf9"] . "', ";
        $query .= "spInf10='" . $jsonObj ["spInf10"] . "', ";
        $query .= "spInf11='" . $jsonObj ["spInf11"] . "', ";
        $query .= "spInf12='" . $jsonObj ["spInf12"] . "', ";
        $query .= "spInf13='" . $jsonObj ["spInf13"] . "', ";
        $query .= "spInf14='" . $jsonObj ["spInf14"] . "', ";
        $query .= "spInf15='" . $jsonObj ["spInf15"] . "', ";
        $query .= "spInf16='" . $jsonObj ["spInf16"] . "', ";
        $query .= "spInf17='" . $jsonObj ["spInf17"] . "', ";
        $query .= "spInf18='" . $jsonObj ["spInf18"] . "', ";
        $query .= "spInf19='" . $jsonObj ["spInf19"] . "', ";
        $query .= "spInf20='" . $jsonObj ["spInf20"] . "', ";
        $query .= "spInf21='" . $jsonObj ["spInf21"] . "', ";
        $query .= "spInf22='" . $jsonObj ["spInf22"] . "', ";
        $query .= "spInf23='" . $jsonObj ["spInf23"] . "', ";
        $query .= "spInf24='" . $jsonObj ["spInf24"] . "', ";
        $query .= "spInf25='" . $jsonObj ["spInf25"] . "', ";
        $query .= "spInf26='" . $jsonObj ["spInf26"] . "', ";
        $query .= "spInf27='" . $jsonObj ["spInf27"] . "', ";
        $query .= "spInf28='" . $jsonObj ["spInf28"] . "', ";
        $query .= "spInf29='" . $jsonObj ["spInf29"] . "', ";
        $query .= "spInf30='" . $jsonObj ["spInf30"] . "', ";
        $query .= "spInf31='" . $jsonObj ["spInf31"] . "', ";
        $query .= "spInf32='" . $jsonObj ["spInf32"] . "', ";
        $query .= "spInf33='" . $jsonObj ["spInf33"] . "', ";
        $query .= "spInf34='" . $jsonObj ["spInf34"] . "', ";
        $query .= "spInf35='" . $jsonObj ["spInf35"] . "', ";
        $query .= "spInf36='" . $jsonObj ["spInf36"] . "', ";
        $query .= "spInf37='" . $jsonObj ["spInf37"] . "', ";
        $query .= "spInf38='" . $jsonObj ["spInf38"] . "', ";
        $query .= "spInf39='" . $jsonObj ["spInf39"] . "', ";
        $query .= "spInf40='" . $jsonObj ["spInf40"] . "' ";

        $query .= "WHERE id=". $jsonObj ["id"];
        
        if ($database->query ( $query ) === TRUE) {

            $response ["status"] = 0;
            $response ["error"] = "";

        } else {

            $response ["status"] = 1;
            $response ["error"] = $database->error . " - Query: " . $query;

        }

        mysqli_close ( $database );

    }

} else {

    $response ["status"] = 1;
    $response ["error"] = "Error : isset";
}
?>