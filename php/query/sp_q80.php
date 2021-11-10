<?php

require_once dirname(__DIR__, 1) . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $database, 'utf8' );

if (! $database) {

    $response ["status"] = 1;
    $response ["error"] = mysqli_connect_error ();

} else {

    $query = "SELECT * FROM sp_territory";
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
        $row ["spInf5"] = $resultRow ["spInf5"];
        $row ["spInf6"] = $resultRow ["spInf6"];
        $row ["spInf7"] = $resultRow ["spInf7"];
        $row ["spInf8"] = $resultRow ["spInf8"];
        $row ["spInf9"] = $resultRow ["spInf9"];
        $row ["spInf10"] = $resultRow ["spInf10"];
        $row ["spInf11"] = $resultRow ["spInf11"];
        $row ["spInf12"] = $resultRow ["spInf12"];
        $row ["spInf13"] = $resultRow ["spInf13"];
        $row ["spInf14"] = $resultRow ["spInf14"];
        $row ["spInf15"] = $resultRow ["spInf15"];
        $row ["spInf16"] = $resultRow ["spInf16"];
        $row ["spInf17"] = $resultRow ["spInf17"];
        $row ["spInf18"] = $resultRow ["spInf18"];
        $row ["spInf19"] = $resultRow ["spInf19"];
        $row ["spInf20"] = $resultRow ["spInf20"];
        $row ["spInf21"] = $resultRow ["spInf21"];
        $row ["spInf22"] = $resultRow ["spInf22"];
        $row ["spInf23"] = $resultRow ["spInf23"];
        $row ["spInf24"] = $resultRow ["spInf24"];
        $row ["spInf25"] = $resultRow ["spInf25"];
        $row ["spInf26"] = $resultRow ["spInf26"];
        $row ["spInf27"] = $resultRow ["spInf27"];
        $row ["spInf28"] = $resultRow ["spInf28"];
        $row ["spInf29"] = $resultRow ["spInf29"];
        $row ["spInf30"] = $resultRow ["spInf30"];
        $row ["spInf31"] = $resultRow ["spInf31"];
        $row ["spInf32"] = $resultRow ["spInf32"];
        $row ["spInf33"] = $resultRow ["spInf33"];
        $row ["spInf34"] = $resultRow ["spInf34"];
        $row ["spInf35"] = $resultRow ["spInf35"];
        $row ["spInf36"] = $resultRow ["spInf36"];
        $row ["spInf37"] = $resultRow ["spInf37"];
        $row ["spInf38"] = $resultRow ["spInf38"];
        $row ["spInf39"] = $resultRow ["spInf39"];
        $row ["spInf40"] = $resultRow ["spInf40"];

        $row ["spInf41"] = $resultRow ["spInf41"];
        $row ["spInf42"] = $resultRow ["spInf42"];
        $row ["spInf43"] = $resultRow ["spInf43"];
        $row ["spInf44"] = $resultRow ["spInf44"];
        $row ["spInf45"] = $resultRow ["spInf45"];
        $row ["spInf46"] = $resultRow ["spInf46"];
        $row ["spInf47"] = $resultRow ["spInf47"];
        $row ["spInf48"] = $resultRow ["spInf48"];

        array_push ( $response ["result"], $row );
        
    }

    $result->close ();
    
    mysqli_close ( $database );
}

?>