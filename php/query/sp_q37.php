<?php

require_once dirname(__DIR__, 1) . '/config.php';
$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
mysqli_set_charset ( $database, 'utf8' );
if (! $database) {
    $response ["status"] = 4;
    $response ["error"] = mysqli_connect_error ();
} else {

    // Next Sunday
    $timestamp = strtotime("Next Sunday");

    $day = date("j", $timestamp);
    $month = date("n", $timestamp);
    $year = date("o", $timestamp);
    $week = date("W", $timestamp);

    if ($month == 1)
        if ($day < 4)
            $year = $year - 1;

    // A year ago
    $year = $year - 1;    
    $weekcode = $year . $week;

    // Query
    $query = "DELETE FROM sp_week";
    $query .= " WHERE spInf1 < " . $weekcode;

    if ($database->query ( $query ) === TRUE) {

        $query = "DELETE FROM sp_week_cr";
        $query .= " WHERE spInf1 < " . $weekcode;

        if ($database->query ( $query ) === TRUE) {
            
            $query = "DELETE FROM sp_week_min";
            $query .= " WHERE spInf1 < " . $weekcode;    

            if ($database->query ( $query ) === TRUE) {
                $response ["status"] = 0;
            } else {
                $response ["status"] = 4;
                $response ["error"] = $database->error;
            }
            
        } else {
            $response ["status"] = 4;
            $response ["error"] = $database->error;
        }

    } else {
        $response ["status"] = 4;
        $response ["error"] = $database->error;
    }

    mysqli_close ( $database );
}

?>